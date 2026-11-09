package com.example.util

import com.example.model.ForbiddenPeriod
import com.example.model.PrayerTime
import java.util.Calendar
import kotlin.math.abs
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.sin
import kotlin.math.tan

object PrayerCalculator {

  enum class CalculationMethod(val fajrAngle: Double, val ishaAngle: Double) {
    IFB_KARACHI(18.0, 18.0), // Islamic Foundation Bangladesh & Karachi
    MWL(18.0, 17.0),         // Muslim World League
    EGYPTIAN(19.5, 17.5),
    UMM_AL_QURA(18.5, 19.0)
  }

  enum class AsrMethod(val shadowFactor: Double) {
    HANAFI(2.0),
    STANDARD(1.0) // Shafi'i, Maliki, Hanbali
  }

  data class CalculationResult(
    val prayers: List<PrayerTime>,
    val forbiddenPeriods: List<ForbiddenPeriod>,
    val nextPrayerIndex: Int,
    val timeToNextMillis: Long
  )

  fun calculate(
    calendar: Calendar,
    latitude: Double,
    longitude: Double,
    method: CalculationMethod = CalculationMethod.IFB_KARACHI,
    asrMethod: AsrMethod = AsrMethod.HANAFI,
    isBangla: Boolean = true
  ): CalculationResult {
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val timeZone = calendar.timeZone.rawOffset / (1000.0 * 3600.0)

    val julianDate = julianDate(year, month, day) - longitude / (15.0 * 24.0)

    // Sun coordinates
    val d = julianDate - 2451545.0
    val g = fixAngle(357.529 + 0.98560028 * d)
    val q = fixAngle(280.459 + 0.98564736 * d)
    val l = fixAngle(q + 1.915 * sin(degToRad(g)) + 0.020 * sin(degToRad(2 * g)))
    val e = 23.439 - 0.00000036 * d
    val declination = radToDeg(asin(sin(degToRad(e)) * sin(degToRad(l))))
    var ra = radToDeg(atan2(cos(degToRad(e)) * sin(degToRad(l)), cos(degToRad(l)))) / 15.0
    ra = fixHour(ra)

    val eqOfTime = q / 15.0 - ra
    val noon = fixHour(12.0 + timeZone - longitude / 15.0 - eqOfTime)

    // Calculations
    val fajrT = noon - hourAngle(latitude, declination, -method.fajrAngle) / 15.0
    val sunriseT = noon - hourAngle(latitude, declination, -0.8333) / 15.0
    val dhuhrT = noon + 0.02 // ~1 min after zenith
    val asrT = noon + asrHourAngle(latitude, declination, asrMethod.shadowFactor) / 15.0
    val sunsetT = noon + hourAngle(latitude, declination, -0.8333) / 15.0
    val maghribT = sunsetT + 0.03
    val ishaT = noon + hourAngle(latitude, declination, -method.ishaAngle) / 15.0

    val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
    val currentMinute = calendar.get(Calendar.MINUTE)
    val currentSecond = calendar.get(Calendar.SECOND)
    val currentTimeMinutes = currentHour * 60 + currentMinute + currentSecond / 60.0

    val rawTimes = listOf(
      Triple("fajr", fajrT, "Fajr"),
      Triple("sunrise", sunriseT, "Sunrise"),
      Triple("dhuhr", dhuhrT, "Dhuhr"),
      Triple("asr", asrT, "Asr"),
      Triple("maghrib", maghribT, "Maghrib"),
      Triple("isha", ishaT, "Isha")
    )

    val prayerList = mutableListOf<PrayerTime>()
    var nextIndex = 0
    var minDiff = Double.MAX_VALUE

    for ((index, item) in rawTimes.withIndex()) {
      val (id, rawHour, nameEn) = item
      val h = (floor(rawHour).toInt() % 24 + 24) % 24
      val m = (floor((rawHour - floor(rawHour)) * 60).toInt()) % 60
      val itemMinutes = h * 60.0 + m

      val diff = if (itemMinutes >= currentTimeMinutes) {
        itemMinutes - currentTimeMinutes
      } else {
        (24 * 60 - currentTimeMinutes) + itemMinutes
      }

      if (diff < minDiff) {
        minDiff = diff
        nextIndex = index
      }

      val nameBn = when (id) {
        "fajr" -> "ফজর"
        "sunrise" -> "সূর্যোদয়"
        "dhuhr" -> "যোহর"
        "asr" -> "আসর"
        "maghrib" -> "মাগরিব"
        "isha" -> "ইশা"
        else -> nameEn
      }

      val arabic = when (id) {
        "fajr" -> "الفجر"
        "sunrise" -> "الشروق"
        "dhuhr" -> "الظهر"
        "asr" -> "العصر"
        "maghrib" -> "المغرب"
        "isha" -> "العشاء"
        else -> ""
      }

      val formattedTime = formatTime(h, m, isBangla)
      prayerList.add(
        PrayerTime(
          id = id,
          nameEn = nameEn,
          nameBn = nameBn,
          arabicName = arabic,
          timeString = formattedTime,
          hour = h,
          minute = m,
          isCurrent = false,
          isNext = false
        )
      )
    }

    // Mark current and next
    val updatedList = prayerList.mapIndexed { idx, prayer ->
      prayer.copy(
        isNext = idx == nextIndex,
        isCurrent = idx == (if (nextIndex == 0) prayerList.size - 1 else nextIndex - 1)
      )
    }

    // Forbidden periods:
    // 1. Sunrise period: sunrise to +15 mins
    // 2. Solar Noon (Zawal): 12 mins before Dhuhr to Dhuhr
    // 3. Sunset period: 15 mins before Maghrib to Maghrib
    val sunriseP = prayerList.find { it.id == "sunrise" }
    val dhuhrP = prayerList.find { it.id == "dhuhr" }
    val maghribP = prayerList.find { it.id == "maghrib" }

    val forbidden = listOf(
      ForbiddenPeriod(
        nameEn = "Sunrise Period",
        nameBn = "সূর্যোদয়ের সময়",
        timeRange = "${sunriseP?.timeString ?: ""} - ${addMinutesFormatted(sunriseP?.hour ?: 6, sunriseP?.minute ?: 0, 15, isBangla)}",
        reasonBn = "সূর্য ওঠার সময় থেকে প্রায় ১৫ মিনিট পর্যন্ত সব ধরণের সালাত আদায় নিষিদ্ধ।",
        reasonEn = "Praying any voluntary or obligatory prayer is prohibited from sunrise until 15 minutes after."
      ),
      ForbiddenPeriod(
        nameEn = "Zawal (Solar Noon)",
        nameBn = "ঠিক দ্বিপ্রহরের সময় (যাওয়াল)",
        timeRange = "${addMinutesFormatted(dhuhrP?.hour ?: 12, dhuhrP?.minute ?: 0, -12, isBangla)} - ${dhuhrP?.timeString ?: ""}",
        reasonBn = "ঠিক মাথার উপরে সূর্য থাকা অবস্থায় সূর্য ঢলে যাওয়ার পূর্ব পর্যন্ত সালাত নিষিদ্ধ।",
        reasonEn = "Prohibited when the sun is at its zenith until it moves past the meridian into Dhuhr."
      ),
      ForbiddenPeriod(
        nameEn = "Sunset Period",
        nameBn = "সূর্যাস্তের সময়",
        timeRange = "${addMinutesFormatted(maghribP?.hour ?: 18, maghribP?.minute ?: 0, -15, isBangla)} - ${maghribP?.timeString ?: ""}",
        reasonBn = "সূর্য হলুদ বর্ণ ধারণ করার পর থেকে সূর্যাস্ত সম্পন্ন হওয়া পর্যন্ত সালাত নিষিদ্ধ (আসর কাজা ছাড়া)।",
        reasonEn = "Prohibited as the sun sets until it has completely vanished below the horizon."
      )
    )

    val timeToNextMillis = (minDiff * 60 * 1000).toLong()

    return CalculationResult(
      prayers = updatedList,
      forbiddenPeriods = forbidden,
      nextPrayerIndex = nextIndex,
      timeToNextMillis = timeToNextMillis
    )
  }

  fun formatTime(hour: Int, minute: Int, isBangla: Boolean): String {
    val h12 = if (hour % 12 == 0) 12 else hour % 12
    val amPmEn = if (hour < 12) "AM" else "PM"
    val amPmBn = when {
      hour < 5 -> "ভোর"
      hour < 12 -> "সকাল"
      hour < 15 -> "দুপুর"
      hour < 18 -> "বিকাল"
      hour < 20 -> "সন্ধ্যা"
      else -> "রাত"
    }

    val mStr = String.format("%02d", minute)
    return if (isBangla) {
      val banglaNumbers = mapOf(
        '0' to '০', '1' to '১', '2' to '২', '3' to '৩', '4' to '৪',
        '5' to '৫', '6' to '৬', '7' to '৭', '8' to '৮', '9' to '৯'
      )
      val timeDigits = "$h12:$mStr".map { banglaNumbers[it] ?: it }.joinToString("")
      "$amPmBn $timeDigits"
    } else {
      "$h12:$mStr $amPmEn"
    }
  }

  private fun addMinutesFormatted(hour: Int, minute: Int, deltaMin: Int, isBangla: Boolean): String {
    var totalMin = hour * 60 + minute + deltaMin
    if (totalMin < 0) totalMin += 24 * 60
    totalMin %= (24 * 60)
    return formatTime(totalMin / 60, totalMin % 60, isBangla)
  }

  private fun julianDate(year: Int, month: Int, day: Int): Double {
    var y = year
    var m = month
    if (m <= 2) {
      y -= 1
      m += 12
    }
    val a = floor(y / 100.0)
    val b = 2 - a + floor(a / 4.0)
    return floor(365.25 * (y + 4716)) + floor(30.6001 * (m + 1)) + day + b - 1524.5
  }

  private fun degToRad(deg: Double) = deg * Math.PI / 180.0
  private fun radToDeg(rad: Double) = rad * 180.0 / Math.PI
  private fun fixAngle(a: Double) = (a % 360.0 + 360.0) % 360.0
  private fun fixHour(a: Double) = (a % 24.0 + 24.0) % 24.0

  private fun hourAngle(lat: Double, dec: Double, alt: Double): Double {
    val cosVal = (sin(degToRad(alt)) - sin(degToRad(lat)) * sin(degToRad(dec))) /
      (cos(degToRad(lat)) * cos(degToRad(dec)))
    return if (cosVal > 1.0) 0.0 else if (cosVal < -1.0) 180.0 else radToDeg(acos(cosVal))
  }

  private fun asrHourAngle(lat: Double, dec: Double, shadowFactor: Double): Double {
    val alt = radToDeg(-atan(1.0 / (shadowFactor + tan(degToRad(abs(lat - dec))))))
    return hourAngle(lat, dec, 90.0 + alt)
  }
}
