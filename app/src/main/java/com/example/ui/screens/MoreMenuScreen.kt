package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.SectionHeader
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldDark
import com.example.viewmodel.NoorlifeViewModel

data class MoreMenuItem(
  val id: String,
  val titleBn: String,
  val titleEn: String,
  val subtitleBn: String,
  val subtitleEn: String,
  val icon: ImageVector,
  val color: Color,
  val onClick: () -> Unit
)

@Composable
fun MoreMenuScreen(
  viewModel: NoorlifeViewModel,
  onNavigateToAllahNames: () -> Unit,
  onNavigateToDuas: () -> Unit,
  onNavigateToHadith: () -> Unit,
  onNavigateToRamadan: () -> Unit,
  onNavigateToQibla: () -> Unit,
  onNavigateToCalendar: () -> Unit,
  onNavigateToKnowledge: () -> Unit,
  onNavigateToNotes: () -> Unit,
  onNavigateToSettings: () -> Unit,
  onNavigateToAbout: () -> Unit,
  modifier: Modifier = Modifier
) {
  val isBangla by viewModel.isBangla.collectAsState()

  val items = listOf(
    MoreMenuItem(
      "names",
      "আল্লাহর ৯৯টি নাম", "99 Names of Allah",
      "অর্থ, ফজিলত ও তাৎপর্য", "Asmaul Husna with meanings",
      Icons.Default.Star, IslamicGoldDark, onNavigateToAllahNames
    ),
    MoreMenuItem(
      "duas",
      "দোয়া ও জিকির", "Duas & Adhkar",
      "দৈনন্দিন জীবনের মাসনূন দোয়াসমূহ", "Hisnul Muslim daily prayers",
      Icons.Default.Mosque, IslamicEmerald, onNavigateToDuas
    ),
    MoreMenuItem(
      "hadith",
      "আল-হাদিস সংগ্রহ", "Hadith Collection",
      "সহীহ বুখারী ও মুসলিম হতে সংকলিত", "Sahih Bukhari & Muslim",
      Icons.Default.Book, IslamicEmerald, onNavigateToHadith
    ),
    MoreMenuItem(
      "ramadan",
      "পবিত্র রমজানুল মুবারক", "Holy Ramadan",
      "সেহরি ও ইফতার সময়সূচি এবং সিয়াম আমল", "Sehri & Iftar timetable",
      Icons.Default.NightsStay, IslamicGoldDark, onNavigateToRamadan
    ),
    MoreMenuItem(
      "qibla",
      "কিবলা কম্পাস", "Qibla Compass",
      "সঠিক কিবলা দিক ও কাবা অভিমুখ", "Sensor-based Kaaba direction",
      Icons.Default.Explore, IslamicEmerald, onNavigateToQibla
    ),
    MoreMenuItem(
      "calendar",
      "ইসলামিক হিজরি ক্যালেন্ডার", "Islamic Calendar",
      "হিজরি, বাংলা ও ইংরেজি তারিখ", "Hijri & Bangla dates",
      Icons.Default.CalendarMonth, IslamicGoldDark, onNavigateToCalendar
    ),
    MoreMenuItem(
      "knowledge",
      "ইসলামিক জ্ঞান ও গাইড", "Islamic Guides",
      "অজু, গোসল, নামাজ শিক্ষা ও স্তম্ভ", "Wudu, Salah, Pillars guides",
      Icons.Default.MenuBook, IslamicEmerald, onNavigateToKnowledge
    ),
    MoreMenuItem(
      "notes",
      "ব্যক্তিগত ইসলামিক নোট", "Personal Notes",
      "তাদাব্বুর, চিন্তা ও সংকল্প ডায়েরি", "Spiritual reflections & diary",
      Icons.Default.NoteAlt, IslamicGoldDark, onNavigateToNotes
    ),
    MoreMenuItem(
      "settings",
      "সেটিংস ও নিয়ম", "Settings",
      "ভাষা, থিম, হরফ ও নামাজ হিসাব", "Preferences & Calculations",
      Icons.Default.Settings, MaterialTheme.colorScheme.onSurfaceVariant, onNavigateToSettings
    ),
    MoreMenuItem(
      "about",
      "নুরলাইফ সম্পর্কে", "About Noorlife",
      "Version 1.0 • Junaid Al Arib Ansari", "Creator attribution & credits",
      Icons.Default.Info, IslamicEmerald, onNavigateToAbout
    )
  )

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("more_menu_content"),
    contentPadding = PaddingValues(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 90.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    item {
      SectionHeader(title = if (isBangla) "সকল ইসলামিক সেবা ও অনুষঙ্গ" else "All Islamic Tools")
    }

    items(items) { item ->
      Surface(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { item.onClick() }
          .testTag("more_menu_${item.id}"),
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = item.color.copy(alpha = 0.12f),
            modifier = Modifier.size(42.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = item.color,
                modifier = Modifier.size(22.dp)
              )
            }
          }

          Spacer(modifier = Modifier.width(14.dp))

          Column {
            Text(
              text = if (isBangla) item.titleBn else item.titleEn,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = if (isBangla) item.subtitleBn else item.subtitleEn,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }
    }
  }
}
