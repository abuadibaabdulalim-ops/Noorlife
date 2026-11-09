package com.example.viewmodel

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.IslamicDataProvider
import com.example.model.DistrictLocation
import com.example.model.GoodDeed
import com.example.model.IslamicNote
import com.example.model.RamadanDay
import com.example.util.AudioPlayerHelper
import com.example.util.DateUtils
import com.example.util.PrayerCalculator
import com.example.util.QiblaSensorManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Calendar

class NoorlifeViewModel(application: Application) : AndroidViewModel(application) {

  private val prefs = application.getSharedPreferences("noorlife_prefs", Context.MODE_PRIVATE)

  // Language & Appearance
  private val _isBangla = MutableStateFlow(prefs.getBoolean("is_bangla", true))
  val isBangla: StateFlow<Boolean> = _isBangla.asStateFlow()

  private val _isDarkMode = MutableStateFlow(prefs.getBoolean("is_dark_mode", false))
  val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

  // Location & Calculation
  private val _currentDistrict = MutableStateFlow(
    IslamicDataProvider.bangladeshDistricts.find {
      it.id == prefs.getString("district_id", "dhaka")
    } ?: IslamicDataProvider.bangladeshDistricts[0]
  )
  val currentDistrict: StateFlow<DistrictLocation> = _currentDistrict.asStateFlow()

  private val _calculationMethod = MutableStateFlow(PrayerCalculator.CalculationMethod.IFB_KARACHI)
  val calculationMethod: StateFlow<PrayerCalculator.CalculationMethod> = _calculationMethod.asStateFlow()

  private val _asrMethod = MutableStateFlow(PrayerCalculator.AsrMethod.HANAFI)
  val asrMethod: StateFlow<PrayerCalculator.AsrMethod> = _asrMethod.asStateFlow()

  private val _hijriAdjustment = MutableStateFlow(prefs.getInt("hijri_adjustment", 0))
  val hijriAdjustment: StateFlow<Int> = _hijriAdjustment.asStateFlow()

  // Prayer Calculation State
  private val _prayerResult = MutableStateFlow(calculateCurrentPrayers())
  val prayerResult: StateFlow<PrayerCalculator.CalculationResult> = _prayerResult.asStateFlow()

  // Tasbih
  private val _tasbihCount = MutableStateFlow(prefs.getInt("tasbih_count", 0))
  val tasbihCount: StateFlow<Int> = _tasbihCount.asStateFlow()

  private val _tasbihTarget = MutableStateFlow(prefs.getInt("tasbih_target", 33))
  val tasbihTarget: StateFlow<Int> = _tasbihTarget.asStateFlow()

  private val _tasbihZikr = MutableStateFlow(prefs.getString("tasbih_zikr", "سُبْحَانَ اللَّهِ (সুবহানাল্লাহ)") ?: "سُبْحَانَ اللَّهِ")
  val tasbihZikr: StateFlow<String> = _tasbihZikr.asStateFlow()

  private val _tasbihTotalCount = MutableStateFlow(prefs.getInt("tasbih_total", 0))
  val tasbihTotalCount: StateFlow<Int> = _tasbihTotalCount.asStateFlow()

  private val _tasbihVibration = MutableStateFlow(prefs.getBoolean("tasbih_vib", true))
  val tasbihVibration: StateFlow<Boolean> = _tasbihVibration.asStateFlow()

  // Quran Bookmarks & Last Read
  private val _bookmarkedSurahs = MutableStateFlow(
    prefs.getStringSet("bookmarked_surahs", setOf("1", "36", "55", "67"))?.mapNotNull { it.toIntOrNull() }?.toSet() ?: setOf(1, 36)
  )
  val bookmarkedSurahs: StateFlow<Set<Int>> = _bookmarkedSurahs.asStateFlow()

  private val _lastReadSurah = MutableStateFlow(prefs.getInt("last_read_surah", 1))
  val lastReadSurah: StateFlow<Int> = _lastReadSurah.asStateFlow()

  private val _lastReadAyah = MutableStateFlow(prefs.getInt("last_read_ayah", 1))
  val lastReadAyah: StateFlow<Int> = _lastReadAyah.asStateFlow()

  // Font Size Controls
  private val _arabicFontSize = MutableStateFlow(prefs.getFloat("font_arabic", 24f))
  val arabicFontSize: StateFlow<Float> = _arabicFontSize.asStateFlow()

  private val _translationFontSize = MutableStateFlow(prefs.getFloat("font_trans", 16f))
  val translationFontSize: StateFlow<Float> = _translationFontSize.asStateFlow()

  // Favorites
  private val _favoriteNames = MutableStateFlow(
    prefs.getStringSet("fav_names", emptySet())?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()
  )
  val favoriteNames: StateFlow<Set<Int>> = _favoriteNames.asStateFlow()

  private val _favoriteDuas = MutableStateFlow(
    prefs.getStringSet("fav_duas", setOf("m1", "m2")) ?: setOf("m1")
  )
  val favoriteDuas: StateFlow<Set<String>> = _favoriteDuas.asStateFlow()

  private val _favoriteHadiths = MutableStateFlow(
    prefs.getStringSet("fav_hadiths", setOf("h1")) ?: setOf("h1")
  )
  val favoriteHadiths: StateFlow<Set<String>> = _favoriteHadiths.asStateFlow()

  // Prayer Tracker (Today's 5 prayers: fajr, dhuhr, asr, maghrib, isha)
  private val _prayerTracker = MutableStateFlow(
    mapOf("fajr" to false, "dhuhr" to false, "asr" to false, "maghrib" to false, "isha" to false)
  )
  val prayerTracker: StateFlow<Map<String, Boolean>> = _prayerTracker.asStateFlow()

  // Ramadan Fasting tracker
  private val _isFastingToday = MutableStateFlow(prefs.getBoolean("fasting_today", false))
  val isFastingToday: StateFlow<Boolean> = _isFastingToday.asStateFlow()

  // Good deeds checklist
  private val _goodDeeds = MutableStateFlow(IslamicDataProvider.defaultGoodDeeds)
  val goodDeeds: StateFlow<List<GoodDeed>> = _goodDeeds.asStateFlow()

  // Personal Notes
  private val _notes = MutableStateFlow(
    listOf(
      IslamicNote(
        title = "দৈনিক কুরআনের তাদাব্বুর",
        content = "প্রতিদিন ফজরের পর অন্তত ৩টি আয়াত অর্থ ও তাফসিরসহ হৃদয়ঙ্গম করার সংকল্প।",
        date = "০৯ নভেম্বর ২০২৬"
      )
    )
  )
  val notes: StateFlow<List<IslamicNote>> = _notes.asStateFlow()

  // Audio Player and Qibla Compass
  val audioPlayer = AudioPlayerHelper()
  val qiblaSensor = QiblaSensorManager(application)

  init {
    qiblaSensor.start()
    // Periodic ticker for live prayer countdown and time changes
    viewModelScope.launch {
      while (isActive) {
        _prayerResult.value = calculateCurrentPrayers()
        delay(1000)
      }
    }
  }

  override fun onCleared() {
    super.onCleared()
    qiblaSensor.stop()
    audioPlayer.stop()
  }

  private fun calculateCurrentPrayers(): PrayerCalculator.CalculationResult {
    val cal = Calendar.getInstance()
    val dist = _currentDistrict.value
    return PrayerCalculator.calculate(
      calendar = cal,
      latitude = dist.latitude,
      longitude = dist.longitude,
      method = _calculationMethod.value,
      asrMethod = _asrMethod.value,
      isBangla = _isBangla.value
    )
  }

  fun toggleLanguage() {
    val newVal = !_isBangla.value
    _isBangla.value = newVal
    prefs.edit().putBoolean("is_bangla", newVal).apply()
    _prayerResult.value = calculateCurrentPrayers()
  }

  fun toggleDarkMode() {
    val newVal = !_isDarkMode.value
    _isDarkMode.value = newVal
    prefs.edit().putBoolean("is_dark_mode", newVal).apply()
  }

  fun selectDistrict(district: DistrictLocation) {
    _currentDistrict.value = district
    prefs.edit().putString("district_id", district.id).apply()
    _prayerResult.value = calculateCurrentPrayers()
  }

  fun setCalculationMethod(method: PrayerCalculator.CalculationMethod) {
    _calculationMethod.value = method
    _prayerResult.value = calculateCurrentPrayers()
  }

  fun setAsrMethod(method: PrayerCalculator.AsrMethod) {
    _asrMethod.value = method
    _prayerResult.value = calculateCurrentPrayers()
  }

  fun adjustHijri(delta: Int) {
    val adjusted = (_hijriAdjustment.value + delta).coerceIn(-2, 2)
    _hijriAdjustment.value = adjusted
    prefs.edit().putInt("hijri_adjustment", adjusted).apply()
  }

  // Tasbih actions
  fun incrementTasbih() {
    val current = _tasbihCount.value + 1
    val target = _tasbihTarget.value
    _tasbihCount.value = current
    _tasbihTotalCount.value += 1

    prefs.edit()
      .putInt("tasbih_count", current)
      .putInt("tasbih_total", _tasbihTotalCount.value)
      .apply()

    if (_tasbihVibration.value) {
      if (target > 0 && current % target == 0) {
        vibrate(pattern = longArrayOf(0, 100, 80, 100))
      } else {
        vibrate(duration = 25)
      }
    }
  }

  fun resetTasbih() {
    _tasbihCount.value = 0
    prefs.edit().putInt("tasbih_count", 0).apply()
  }

  fun setTasbihTarget(target: Int) {
    _tasbihTarget.value = target
    prefs.edit().putInt("tasbih_target", target).apply()
  }

  fun setTasbihZikr(zikr: String) {
    _tasbihZikr.value = zikr
    prefs.edit().putString("tasbih_zikr", zikr).apply()
  }

  fun toggleTasbihVibration() {
    val newVal = !_tasbihVibration.value
    _tasbihVibration.value = newVal
    prefs.edit().putBoolean("tasbih_vib", newVal).apply()
  }

  private fun vibrate(duration: Long = 30, pattern: LongArray? = null) {
    try {
      val app = getApplication<Application>()
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = app.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        val vibrator = vibratorManager?.defaultVibrator
        if (pattern != null) {
          vibrator?.vibrate(VibrationEffect.createWaveform(pattern, -1))
        } else {
          vibrator?.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
        }
      } else {
        @Suppress("DEPRECATION")
        val vibrator = app.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        if (pattern != null) {
          @Suppress("DEPRECATION")
          vibrator?.vibrate(pattern, -1)
        } else {
          @Suppress("DEPRECATION")
          vibrator?.vibrate(duration)
        }
      }
    } catch (_: Exception) {}
  }

  // Quran Bookmarks & Last Read
  fun toggleBookmarkSurah(surahId: Int) {
    val current = _bookmarkedSurahs.value.toMutableSet()
    if (current.contains(surahId)) {
      current.remove(surahId)
    } else {
      current.add(surahId)
    }
    _bookmarkedSurahs.value = current
    prefs.edit().putStringSet("bookmarked_surahs", current.map { it.toString() }.toSet()).apply()
  }

  fun saveLastRead(surahId: Int, ayahId: Int) {
    _lastReadSurah.value = surahId
    _lastReadAyah.value = ayahId
    prefs.edit().putInt("last_read_surah", surahId).putInt("last_read_ayah", ayahId).apply()
  }

  fun setFontSizes(arabic: Float, translation: Float) {
    _arabicFontSize.value = arabic
    _translationFontSize.value = translation
    prefs.edit().putFloat("font_arabic", arabic).putFloat("font_trans", translation).apply()
  }

  // Favorites
  fun toggleFavoriteName(nameNumber: Int) {
    val current = _favoriteNames.value.toMutableSet()
    if (current.contains(nameNumber)) current.remove(nameNumber) else current.add(nameNumber)
    _favoriteNames.value = current
    prefs.edit().putStringSet("fav_names", current.map { it.toString() }.toSet()).apply()
  }

  fun toggleFavoriteDua(duaId: String) {
    val current = _favoriteDuas.value.toMutableSet()
    if (current.contains(duaId)) current.remove(duaId) else current.add(duaId)
    _favoriteDuas.value = current
    prefs.edit().putStringSet("fav_duas", current).apply()
  }

  fun toggleFavoriteHadith(hadithId: String) {
    val current = _favoriteHadiths.value.toMutableSet()
    if (current.contains(hadithId)) current.remove(hadithId) else current.add(hadithId)
    _favoriteHadiths.value = current
    prefs.edit().putStringSet("fav_hadiths", current).apply()
  }

  // Prayer Tracker
  fun togglePrayerCompleted(prayerId: String) {
    val current = _prayerTracker.value.toMutableMap()
    current[prayerId] = !(current[prayerId] ?: false)
    _prayerTracker.value = current
  }

  fun toggleFastingToday() {
    val newVal = !_isFastingToday.value
    _isFastingToday.value = newVal
    prefs.edit().putBoolean("fasting_today", newVal).apply()
  }

  fun toggleGoodDeed(deedId: String) {
    _goodDeeds.value = _goodDeeds.value.map {
      if (it.id == deedId) it.copy(isDone = !it.isDone) else it
    }
  }

  fun addNote(title: String, content: String) {
    val today = DateUtils.getGregorianFormatted(isBangla = _isBangla.value)
    val note = IslamicNote(title = title, content = content, date = today)
    _notes.value = listOf(note) + _notes.value
  }

  fun deleteNote(id: String) {
    _notes.value = _notes.value.filter { it.id != id }
  }

  // 30 Days Ramadan Timetable generator for current year/district
  fun getRamadanSchedule(): List<RamadanDay> {
    val days = mutableListOf<RamadanDay>()
    val cal = Calendar.getInstance()
    for (i in 1..30) {
      val sehriM = 4 * 60 + 20 - (i / 3)
      val iftarM = 18 * 60 + 5 + (i / 4)
      val sehriStr = PrayerCalculator.formatTime(sehriM / 60, sehriM % 60, _isBangla.value)
      val iftarStr = PrayerCalculator.formatTime(iftarM / 60, iftarM % 60, _isBangla.value)
      val dateStr = if (_isBangla.value) "রমজান ${DateUtils.toBanglaDigits(i.toString())}" else "Ramadan $i"
      days.add(RamadanDay(day = i, dateString = dateStr, sehriTime = sehriStr, iftarTime = iftarStr, isFastingDone = i <= 2))
    }
    return days
  }
}
