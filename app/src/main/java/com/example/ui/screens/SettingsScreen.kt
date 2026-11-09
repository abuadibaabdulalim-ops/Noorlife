package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.IslamicCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicGold
import com.example.viewmodel.NoorlifeViewModel

@Composable
fun SettingsScreen(
  viewModel: NoorlifeViewModel,
  onNavigateToAbout: () -> Unit,
  onNavigateToNotes: () -> Unit,
  onOpenLocationSheet: () -> Unit,
  modifier: Modifier = Modifier
) {
  val isBangla by viewModel.isBangla.collectAsState()
  val isDarkMode by viewModel.isDarkMode.collectAsState()
  val currentDistrict by viewModel.currentDistrict.collectAsState()
  val vibrationEnabled by viewModel.tasbihVibration.collectAsState()
  val arabicFontSize by viewModel.arabicFontSize.collectAsState()
  val transFontSize by viewModel.translationFontSize.collectAsState()

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("settings_screen_content"),
    contentPadding = PaddingValues(top = 12.dp, start = 0.dp, end = 0.dp, bottom = 90.dp)
  ) {
    item {
      SectionHeader(title = if (isBangla) "অ্যাপের সাধারণ সেটিংস" else "General Settings")

      // Language Switcher
      SettingsToggleRow(
        title = if (isBangla) "ভাষা (Language)" else "Language (ভাষা)",
        subtitle = if (isBangla) "বাংলা ভাষা সক্রিয়" else "English is active",
        icon = Icons.Default.Language,
        checked = isBangla,
        onCheckedChange = { viewModel.toggleLanguage() }
      )

      // Dark Mode Switcher
      SettingsToggleRow(
        title = if (isBangla) "ডার্ক মোড (Dark Theme)" else "Dark Theme",
        subtitle = if (isDarkMode) (if (isBangla) "ডার্ক থিম সক্রিয়" else "Dark mode enabled")
        else (if (isBangla) "লাইট থিম সক্রিয়" else "Light mode enabled"),
        icon = Icons.Default.DarkMode,
        checked = isDarkMode,
        onCheckedChange = { viewModel.toggleDarkMode() }
      )

      // Vibration Toggle
      SettingsToggleRow(
        title = if (isBangla) "তাসবিহ ভাইব্রেশন" else "Haptic Vibration",
        subtitle = if (isBangla) "প্রতি গণনায় মৃদু স্পর্শ অনুভূতি" else "Tactile feedback on counting",
        icon = Icons.Default.Vibration,
        checked = vibrationEnabled,
        onCheckedChange = { viewModel.toggleTasbihVibration() }
      )

      Spacer(modifier = Modifier.height(16.dp))
      SectionHeader(title = if (isBangla) "অবস্থান ও নামাজ হিসাব" else "Location & Prayer Calculation")

      // Current Location setting
      SettingsClickableRow(
        title = if (isBangla) "নির্বাচিত জেলা / অবস্থান" else "Selected District",
        subtitle = if (isBangla) "${currentDistrict.divisionBn} বিভাগ • ${currentDistrict.nameBn}" else "${currentDistrict.divisionEn} • ${currentDistrict.nameEn}",
        icon = Icons.Default.LocationOn,
        onClick = onOpenLocationSheet
      )

      Spacer(modifier = Modifier.height(16.dp))
      SectionHeader(title = if (isBangla) "কুরআন হরফের আকার" else "Quran Font Size")

      // Quran Font Size Sliders
      IslamicCard(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
      ) {
        Column {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = if (isBangla) "আরবি হরফের সাইজ" else "Arabic Font Size",
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold
            )
            Text(text = "${arabicFontSize.toInt()} sp", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = IslamicEmerald)
          }
          Slider(
            value = arabicFontSize,
            onValueChange = { viewModel.setFontSizes(it, transFontSize) },
            valueRange = 18f..38f,
            colors = SliderDefaults.colors(thumbColor = IslamicEmerald, activeTrackColor = IslamicEmerald)
          )

          Spacer(modifier = Modifier.height(6.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = if (isBangla) "অনুবাদ হরফের সাইজ" else "Translation Font Size",
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold
            )
            Text(text = "${transFontSize.toInt()} sp", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = IslamicEmerald)
          }
          Slider(
            value = transFontSize,
            onValueChange = { viewModel.setFontSizes(arabicFontSize, it) },
            valueRange = 12f..24f,
            colors = SliderDefaults.colors(thumbColor = IslamicEmerald, activeTrackColor = IslamicEmerald)
          )
        }
      }

      Spacer(modifier = Modifier.height(16.dp))
      SectionHeader(title = if (isBangla) "ব্যক্তিগত ও পরিচিতি" else "Personal & About")

      // Notes & Good deeds
      SettingsClickableRow(
        title = if (isBangla) "ব্যক্তিগত ইসলামিক নোট" else "Personal Islamic Notes",
        subtitle = if (isBangla) "দৈনিক তাদাব্বুর ও আমলের নোটসমূহ" else "Manage personal reflections",
        icon = Icons.Default.NoteAlt,
        onClick = onNavigateToNotes
      )

      // About Noorlife
      SettingsClickableRow(
        title = if (isBangla) "নুরলাইফ সম্পর্কে (About Noorlife)" else "About Noorlife",
        subtitle = "Version 1.0 • Created by Junaid Al Arib Ansari",
        icon = Icons.Default.Info,
        onClick = onNavigateToAbout
      )
    }
  }
}

@Composable
fun SettingsToggleRow(
  title: String,
  subtitle: String,
  icon: ImageVector,
  checked: Boolean,
  onCheckedChange: (Boolean) -> Unit
) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 4.dp),
    shape = RoundedCornerShape(12.dp),
    color = MaterialTheme.colorScheme.surface
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        modifier = Modifier.weight(1f),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = IslamicEmerald.copy(alpha = 0.1f),
          modifier = Modifier.size(36.dp)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Icon(imageVector = icon, contentDescription = null, tint = IslamicEmerald, modifier = Modifier.size(18.dp))
          }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
          Text(text = subtitle, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
      }

      Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
          checkedThumbColor = Color.White,
          checkedTrackColor = IslamicEmerald
        )
      )
    }
  }
}

@Composable
fun SettingsClickableRow(
  title: String,
  subtitle: String,
  icon: ImageVector,
  onClick: () -> Unit
) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 4.dp)
      .clickable { onClick() },
    shape = RoundedCornerShape(12.dp),
    color = MaterialTheme.colorScheme.surface
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Surface(
        shape = RoundedCornerShape(8.dp),
        color = IslamicEmerald.copy(alpha = 0.1f),
        modifier = Modifier.size(36.dp)
      ) {
        Box(contentAlignment = Alignment.Center) {
          Icon(imageVector = icon, contentDescription = null, tint = IslamicEmerald, modifier = Modifier.size(18.dp))
        }
      }
      Spacer(modifier = Modifier.width(12.dp))
      Column {
        Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface)
        Text(text = subtitle, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
      }
    }
  }
}
