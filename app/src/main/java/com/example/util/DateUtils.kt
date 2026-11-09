package com.example.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.floor

object DateUtils {

  // Hijri Month Names
  private val hijriMonthsBn = listOf(
    "মুহররম", "সফর", "রবিউল আউয়াল", "রবিউস সানি", "জমাদিউল আউয়াল",
    "জমাদিউস সানি", "রজব", "শাবান", "রমজান", "শাওয়াল", "জিলকদ", "জিলহজ্জ"
  )

  private val hijriMonthsEn = listOf(
    "Muharram", "Safar", "Rabi' al-Awwal", "Rabi' al-Thani", "Jumada al-Awwal",
    "Jumada al-Thani", "Rajab", "Sha'ban", "Ramadan", "Shawwal", "Dhu al-Qi'dah", "Dhu al-Hijjah"
  )

  private val banglaMonths = listOf(
    "বৈশাখ", "জ্যৈষ্ঠ", "আষাঢ়", "শ্রাবণ", "ভাদ্র", "আশ্বিন",
    "কার্তিক", "অগ্রহায়ণ", "পৌষ", "মাঘ", "ফাল্গুন", "চৈত্র"
  )

  private val banglaDayNames = listOf(
    "রবিবার", "সোমবার", "মঙ্গলবার", "বুধবার", "বৃহস্পতিবার", "শুক্রবার", "শনিবার"
  )

  fun toBanglaDigits(input: String): String {
    val map = mapOf(
      '0' to '০', '1' to '১', '2' to '২', '3' to '৩', '4' to '৪',
      '5' to '৫', '6' to '৬', '7' to '৭', '8' to '৮', '9' to '৯'
    )
    return input.map { map[it] ?: it }.joinToString("")
  }

  data class HijriDate(
    val day: Int,
    val month: Int,
    val year: Int,
    val formattedBn: String,
    val formattedEn: String
  )

  fun getHijriDate(calendar: Calendar = Calendar.getInstance(), dayAdjustment: Int = 0): HijriDate {
    val cal = calendar.clone() as Calendar
    cal.add(Calendar.DAY_OF_YEAR, dayAdjustment)

    val y = cal.get(Calendar.YEAR)
    val m = cal.get(Calendar.MONTH) + 1
    val d = cal.get(Calendar.DAY_OF_MONTH)

    // Kuwaiti Algorithm approximation
    var year = y
    var month = m
    if (month < 3) {
      year -= 1
      month += 12
    }
    val a = floor(year / 100.0)
    val b = 2 - a + floor(a / 4.0)
    val jd = floor(365.25 * (year + 4716)) + floor(30.6001 * (month + 1)) + d + b - 1524

    val z = jd - 1948440 + 10632
    val n = floor((z - 1) / 10631.0)
    val zMod = z - 10631 * n + 354
    val j = (floor((10985 - zMod) / 5316.0)) * (floor((50 * zMod) / 17719.0)) +
      (floor(zMod / 5670.0)) * (floor((43 * zMod) / 15238.0))
    val z2 = zMod - (floor((30 - j) / 15.0)) * (floor((17719 * j) / 50.0)) -
      (floor(j / 16.0)) * (floor((15238 * j) / 43.0)) + 29
    val mHijri = floor((24 * z2) / 709.0).toInt()
    val dHijri = (z2 - floor((709 * mHijri) / 24.0)).toInt()
    val yHijri = (30 * n + j - 30).toInt()

    val monthIndex = ((mHijri - 1) % 12 + 12) % 12
    val monthNameBn = hijriMonthsBn[monthIndex]
    val monthNameEn = hijriMonthsEn[monthIndex]

    val dayBn = toBanglaDigits(dHijri.toString())
    val yearBn = toBanglaDigits(yHijri.toString())

    val formattedBn = "$dayBn $monthNameBn $yearBn হিজরি"
    val formattedEn = "$dHijri $monthNameEn $yHijri AH"

    return HijriDate(
      day = dHijri,
      month = monthIndex + 1,
      year = yHijri,
      formattedBn = formattedBn,
      formattedEn = formattedEn
    )
  }

  fun getBanglaDate(calendar: Calendar = Calendar.getInstance()): String {
    val month = calendar.get(Calendar.MONTH) + 1 // 1-12
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val year = calendar.get(Calendar.YEAR)

    // Bangla calendar conversion (14 April starts Boishakh)
    val bYear = if (month > 4 || (month == 4 && day >= 14)) year - 593 else year - 594

    // Approximate days into Bengali month
    val daysInGregorian = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val banglaStarts = listOf(
      Pair(4, 14), // Boishakh
      Pair(5, 15), // Jaishtha
      Pair(6, 15), // Asharh
      Pair(7, 16), // Srabon
      Pair(8, 16), // Bhadra
      Pair(9, 16), // Ashwin
      Pair(10, 16), // Kartik
      Pair(11, 15), // Ograhayon
      Pair(12, 15), // Poush
      Pair(1, 14), // Magh
      Pair(2, 13), // Falgun
      Pair(3, 15)  // Chaitra
    )

    var bMonthIndex = 0
    var bDay = 1

    for (i in banglaStarts.indices) {
      val (sm, sd) = banglaStarts[i]
      if (month == sm && day >= sd) {
        bMonthIndex = i
        bDay = day - sd + 1
        break
      } else if (month == sm + 1 && day < (banglaStarts[(i + 1) % 12].second)) {
        bMonthIndex = i
        val prevMonthDays = daysInGregorian[sm - 1]
        bDay = (prevMonthDays - sd + 1) + day
        break
      }
    }

    val dayBn = toBanglaDigits(bDay.toString())
    val monthBn = banglaMonths[bMonthIndex]
    val yearBn = toBanglaDigits(bYear.toString())

    return "$dayBn $monthBn $yearBn বঙ্গাব্দ"
  }

  fun getGregorianFormatted(calendar: Calendar = Calendar.getInstance(), isBangla: Boolean = true): String {
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1 // 0=Sunday
    val date = calendar.time

    return if (isBangla) {
      val dayNameBn = banglaDayNames[dayOfWeek]
      val sdfDay = SimpleDateFormat("dd", Locale.getDefault()).format(date)
      val sdfMonth = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(date)
      "$dayNameBn, ${toBanglaDigits(sdfDay)} $sdfMonth"
    } else {
      SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.ENGLISH).format(date)
    }
  }

  fun formatCountdown(millis: Long, isBangla: Boolean): String {
    if (millis <= 0) return if (isBangla) "০ সে" else "0s"
    val hours = millis / (1000 * 3600)
    val minutes = (millis % (1000 * 3600)) / (1000 * 60)
    val seconds = (millis % (1000 * 60)) / 1000

    val str = when {
      hours > 0 -> "${hours}h ${minutes}m ${seconds}s"
      minutes > 0 -> "${minutes}m ${seconds}s"
      else -> "${seconds}s"
    }

    return if (isBangla) {
      toBanglaDigits(
        str.replace("h", " ঘন্টা ")
          .replace("m", " মিনিট ")
          .replace("s", " সেকেন্ড")
      )
    } else str
  }
}
