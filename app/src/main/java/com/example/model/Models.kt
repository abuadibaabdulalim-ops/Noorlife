package com.example.model

data class Surah(
  val id: Int,
  val nameArabic: String,
  val nameBangla: String,
  val nameEnglish: String,
  val meaningBangla: String,
  val versesCount: Int,
  val revelationType: String, // "মক্কী" / "মাদানী" or "Meccan" / "Medinan"
  val juzNumber: Int
)

data class Ayah(
  val surahNumber: Int,
  val ayahNumber: Int,
  val arabicText: String,
  val banglaText: String,
  val englishText: String,
  val audioUrl: String = ""
)

data class PrayerTime(
  val id: String,
  val nameEn: String,
  val nameBn: String,
  val arabicName: String,
  val timeString: String,
  val hour: Int,
  val minute: Int,
  val isCurrent: Boolean = false,
  val isNext: Boolean = false
)

data class ForbiddenPeriod(
  val nameEn: String,
  val nameBn: String,
  val timeRange: String,
  val reasonBn: String,
  val reasonEn: String
)

data class AllahName(
  val number: Int,
  val arabic: String,
  val transliterationBn: String,
  val transliterationEn: String,
  val meaningBn: String,
  val meaningEn: String,
  val explanationBn: String,
  val isFavorite: Boolean = false
)

data class DuaItem(
  val id: String,
  val category: String,
  val categoryBn: String,
  val titleEn: String,
  val titleBn: String,
  val arabic: String,
  val pronunciationBn: String,
  val meaningBn: String,
  val meaningEn: String,
  val reference: String,
  val isFavorite: Boolean = false
)

data class HadithItem(
  val id: String,
  val category: String,
  val categoryBn: String,
  val titleEn: String,
  val titleBn: String,
  val arabic: String,
  val banglaText: String,
  val englishText: String,
  val narrator: String,
  val source: String,
  val isFavorite: Boolean = false
)

data class IslamicGuideItem(
  val id: String,
  val category: String,
  val titleBn: String,
  val titleEn: String,
  val summaryBn: String,
  val summaryEn: String,
  val sections: List<GuideSection>
)

data class GuideSection(
  val headingBn: String,
  val headingEn: String,
  val contentBn: String,
  val contentEn: String,
  val arabic: String? = null
)

data class IslamicEvent(
  val titleBn: String,
  val titleEn: String,
  val hijriDateBn: String,
  val hijriDateEn: String,
  val approximateDate: String,
  val descriptionBn: String,
  val descriptionEn: String
)

data class DistrictLocation(
  val id: String,
  val nameEn: String,
  val nameBn: String,
  val divisionEn: String,
  val divisionBn: String,
  val latitude: Double,
  val longitude: Double
)

data class RamadanDay(
  val day: Int,
  val dateString: String,
  val sehriTime: String,
  val iftarTime: String,
  val isFastingDone: Boolean = false
)

data class IslamicNote(
  val id: String = java.util.UUID.randomUUID().toString(),
  val title: String,
  val content: String,
  val date: String
)

data class GoodDeed(
  val id: String,
  val titleBn: String,
  val titleEn: String,
  val isDone: Boolean = false
)
