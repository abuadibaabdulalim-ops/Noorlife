package com.example

import com.example.data.IslamicDataProvider
import com.example.data.QuranSurahData
import com.example.util.DateUtils
import com.example.util.PrayerCalculator
import com.example.util.QiblaSensorManager
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class ExampleUnitTest {

  @Test
  fun testSurahsListNotEmpty() {
    assertEquals(114, QuranSurahData.allSurahs.size)
    val fatihah = QuranSurahData.allSurahs[0]
    assertEquals("Al-Fatihah", fatihah.nameEnglish)
    assertEquals("الفاتحة", fatihah.nameArabic)
    assertEquals(7, fatihah.versesCount)
  }

  @Test
  fun testAllahNamesCount() {
    assertEquals(99, IslamicDataProvider.allahNames.size)
    assertEquals("الرَّحْمَنُ", IslamicDataProvider.allahNames[0].arabic)
  }

  @Test
  fun testPrayerCalculation() {
    val cal = Calendar.getInstance()
    // Dhaka coordinates
    val result = PrayerCalculator.calculate(cal, 23.8103, 90.4125, isBangla = true)
    assertEquals(6, result.prayers.size)
    assertEquals(3, result.forbiddenPeriods.size)
    assertTrue(result.timeToNextMillis >= 0)
  }

  @Test
  fun testQiblaBearingDhaka() {
    // Dhaka is roughly 268-270 degrees west of north towards Makkah
    val bearing = QiblaSensorManager.calculateQiblaBearing(23.8103, 90.4125)
    assertTrue("Bearing should be near 268-272", bearing in 260f..280f)
  }

  @Test
  fun testDateUtils() {
    val cal = Calendar.getInstance()
    val hijri = DateUtils.getHijriDate(cal)
    assertNotNull(hijri.formattedBn)
    assertNotNull(hijri.formattedEn)
    val banglaDate = DateUtils.getBanglaDate(cal)
    assertTrue(banglaDate.contains("বঙ্গাব্দ"))
  }
}
