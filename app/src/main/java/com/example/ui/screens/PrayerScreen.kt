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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.IslamicDataProvider
import com.example.model.DistrictLocation
import com.example.model.ForbiddenPeriod
import com.example.model.PrayerTime
import com.example.ui.components.IslamicCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.ForbiddenRed
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicEmeraldDark
import com.example.ui.theme.IslamicEmeraldLight
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldDark
import com.example.ui.theme.IslamicGoldLight
import com.example.ui.theme.PrayerActiveGreen
import com.example.util.DateUtils
import com.example.util.PrayerCalculator
import com.example.viewmodel.NoorlifeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrayerScreen(
  viewModel: NoorlifeViewModel,
  modifier: Modifier = Modifier
) {
  val isBangla by viewModel.isBangla.collectAsState()
  val prayerResult by viewModel.prayerResult.collectAsState()
  val currentDistrict by viewModel.currentDistrict.collectAsState()
  val calculationMethod by viewModel.calculationMethod.collectAsState()
  val asrMethod by viewModel.asrMethod.collectAsState()
  val prayerTracker by viewModel.prayerTracker.collectAsState()

  var showLocationSheet by remember { mutableStateOf(false) }
  var showSettingsSheet by remember { mutableStateOf(false) }
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

  val countdownStr = DateUtils.formatCountdown(prayerResult.timeToNextMillis, isBangla)
  val nextPrayer = prayerResult.prayers.getOrNull(prayerResult.nextPrayerIndex)

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("prayer_screen_content"),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // 1. Header Banner with Next Prayer & Countdown
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
          .background(
            Brush.verticalGradient(
              listOf(IslamicEmerald, IslamicEmeraldDark)
            )
          )
          .padding(20.dp)
      ) {
        Column {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = if (isBangla) "নামাজের সময়সূচি" else "Prayer Schedule",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = if (isBangla) "${currentDistrict.divisionBn} বিভাগ • ${currentDistrict.nameBn}" else "${currentDistrict.divisionEn} Division • ${currentDistrict.nameEn}",
                color = IslamicGoldLight,
                fontSize = 13.sp
              )
            }

            Row {
              Surface(
                shape = RoundedCornerShape(12.dp),
                color = IslamicEmeraldLight.copy(alpha = 0.6f),
                modifier = Modifier
                  .clickable { showLocationSheet = true }
                  .testTag("change_location_button")
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Change Location",
                    tint = IslamicGold,
                    modifier = Modifier.size(16.dp)
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = if (isBangla) "স্থান" else "Location",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              }

              Spacer(modifier = Modifier.width(8.dp))

              Surface(
                shape = RoundedCornerShape(12.dp),
                color = IslamicEmeraldLight.copy(alpha = 0.6f),
                modifier = Modifier
                  .clickable { showSettingsSheet = true }
                  .testTag("prayer_settings_button")
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Icon(
                    imageVector = Icons.Default.Tune,
                    contentDescription = "Method Settings",
                    tint = IslamicGold,
                    modifier = Modifier.size(16.dp)
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = if (isBangla) "নিয়ম" else "Settings",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Next Prayer Big Countdown Box
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White.copy(alpha = 0.12f),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(16.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(
                  text = if (isBangla) "পরবর্তী সালাতের বাকি" else "Time until next prayer",
                  color = IslamicGoldLight,
                  fontSize = 12.sp
                )
                Text(
                  text = if (isBangla) (nextPrayer?.nameBn ?: "") else (nextPrayer?.nameEn ?: ""),
                  color = Color.White,
                  fontSize = 24.sp,
                  fontWeight = FontWeight.ExtraBold
                )
                Text(
                  text = nextPrayer?.timeString ?: "",
                  color = Color.White.copy(alpha = 0.9f),
                  fontSize = 14.sp
                )
              }

              Surface(
                shape = RoundedCornerShape(12.dp),
                color = IslamicGold
              ) {
                Text(
                  text = countdownStr,
                  color = IslamicEmeraldDark,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Black,
                  modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                )
              }
            }
          }
        }
      }
    }

    // 2. Prayer Times List (Fajr, Sunrise, Dhuhr, Asr, Maghrib, Isha)
    item {
      Spacer(modifier = Modifier.height(16.dp))
      SectionHeader(title = if (isBangla) "দৈনিক ওয়াক্তসমূহ" else "Daily Prayers")
    }

    items(prayerResult.prayers) { prayer ->
      PrayerDetailRow(
        prayer = prayer,
        isBangla = isBangla,
        isCompleted = prayerTracker[prayer.id] ?: false,
        onToggleCompleted = { viewModel.togglePrayerCompleted(prayer.id) }
      )
    }

    // 3. Forbidden Prayer Times Section (Warning banner)
    item {
      Spacer(modifier = Modifier.height(20.dp))
      SectionHeader(
        title = if (isBangla) "নামাজের নিষিদ্ধ সময়" else "Forbidden Prayer Times"
      )

      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
          containerColor = ForbiddenRed.copy(alpha = 0.08f)
        )
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Warning,
              contentDescription = "Forbidden Prayer Times",
              tint = ForbiddenRed,
              modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = if (isBangla) "এই সময়গুলোতে সালাত আদায় করা নিষেধ" else "Prohibited Prayer Periods",
              color = ForbiddenRed,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold
            )
          }

          Spacer(modifier = Modifier.height(12.dp))

          prayerResult.forbiddenPeriods.forEach { period ->
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp)
            ) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Text(
                  text = if (isBangla) period.nameBn else period.nameEn,
                  color = MaterialTheme.colorScheme.onSurface,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold
                )
                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = ForbiddenRed.copy(alpha = 0.15f)
                ) {
                  Text(
                    text = period.timeRange,
                    color = ForbiddenRed,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                  )
                }
              }
              Spacer(modifier = Modifier.height(2.dp))
              Text(
                text = if (isBangla) period.reasonBn else period.reasonEn,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
              )
            }
          }
        }
      }
    }

    // 4. Daily Prayer Habit / Tracker Checklist
    item {
      Spacer(modifier = Modifier.height(20.dp))
      val doneCount = prayerTracker.values.count { it }
      SectionHeader(
        title = if (isBangla) "আজকের নামাজের ট্র্যাকার ($doneCount/৫)" else "Today's Prayer Tracker ($doneCount/5)"
      )

      IslamicCard(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
      ) {
        Column {
          val fardPrayers = listOf("fajr" to "ফজর", "dhuhr" to "যোহর", "asr" to "আসর", "maghrib" to "মাগরিব", "isha" to "ইশা")
          fardPrayers.forEach { (id, nameBn) ->
            val isDone = prayerTracker[id] ?: false
            val p = prayerResult.prayers.find { it.id == id }
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.togglePrayerCompleted(id) }
                .padding(vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                  imageVector = if (isDone) Icons.Default.CheckCircle else Icons.Outlined.CheckCircleOutline,
                  contentDescription = "Toggle $id",
                  tint = if (isDone) IslamicEmerald else MaterialTheme.colorScheme.onSurfaceVariant,
                  modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                  Text(
                    text = if (isBangla) nameBn else (p?.nameEn ?: id.capitalize()),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                  )
                  Text(
                    text = p?.timeString ?: "",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                  )
                }
              }

              Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (isDone) IslamicEmerald.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant
              ) {
                Text(
                  text = if (isDone) (if (isBangla) "আদায় হয়েছে" else "Completed") else (if (isBangla) "বাকি আছে" else "Pending"),
                  color = if (isDone) IslamicEmerald else MaterialTheme.colorScheme.onSurfaceVariant,
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Medium,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
              }
            }
          }
        }
      }
    }
  }

  // District Location Bottom Sheet
  if (showLocationSheet) {
    ModalBottomSheet(
      onDismissRequest = { showLocationSheet = false },
      sheetState = sheetState
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 12.dp)
      ) {
        Text(
          text = if (isBangla) "আপনার জেলা / অবস্থান নির্বাচন করুন" else "Select District / Location",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
          modifier = Modifier.height(400.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          items(IslamicDataProvider.bangladeshDistricts) { district ->
            val isSelected = currentDistrict.id == district.id
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = if (isSelected) IslamicEmerald.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
              modifier = Modifier
                .fillMaxWidth()
                .clickable {
                  viewModel.selectDistrict(district)
                  showLocationSheet = false
                }
            ) {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column {
                  Text(
                    text = if (isBangla) district.nameBn else district.nameEn,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) IslamicEmerald else MaterialTheme.colorScheme.onSurface
                  )
                  Text(
                    text = if (isBangla) "${district.divisionBn} বিভাগ" else "${district.divisionEn} Division",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }

                if (isSelected) {
                  Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Selected",
                    tint = IslamicEmerald
                  )
                }
              }
            }
          }
        }
        Spacer(modifier = Modifier.height(16.dp))
      }
    }
  }

  // Method Settings Bottom Sheet
  if (showSettingsSheet) {
    ModalBottomSheet(
      onDismissRequest = { showSettingsSheet = false },
      sheetState = sheetState
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 12.dp)
      ) {
        Text(
          text = if (isBangla) "নামাজ হিসাবের পদ্ধতি ও মাযহাব" else "Calculation Method & Juristic Settings",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = if (isBangla) "হিসাব পদ্ধতি (Calculation Method)" else "Calculation Method",
          fontWeight = FontWeight.Bold,
          fontSize = 14.sp
        )

        PrayerCalculator.CalculationMethod.values().forEach { method ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { viewModel.setCalculationMethod(method) }
              .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            RadioButton(
              selected = calculationMethod == method,
              onClick = { viewModel.setCalculationMethod(method) },
              colors = RadioButtonDefaults.colors(selectedColor = IslamicEmerald)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = when (method) {
                PrayerCalculator.CalculationMethod.IFB_KARACHI -> "ইসলামিক ফাউন্ডেশন বাংলাদেশ / করাচি (১৮°/১৮°)"
                PrayerCalculator.CalculationMethod.MWL -> "Muslim World League (MWL ১৮°/১৭°)"
                PrayerCalculator.CalculationMethod.EGYPTIAN -> "Egyptian General Authority of Survey"
                PrayerCalculator.CalculationMethod.UMM_AL_QURA -> "Umm Al-Qura University, Makkah"
              },
              fontSize = 13.sp
            )
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
          text = if (isBangla) "আসর নামাজের মাযহাব (Juristic Asr Method)" else "Asr Juristic Method",
          fontWeight = FontWeight.Bold,
          fontSize = 14.sp
        )

        PrayerCalculator.AsrMethod.values().forEach { method ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { viewModel.setAsrMethod(method) }
              .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            RadioButton(
              selected = asrMethod == method,
              onClick = { viewModel.setAsrMethod(method) },
              colors = RadioButtonDefaults.colors(selectedColor = IslamicEmerald)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = if (method == PrayerCalculator.AsrMethod.HANAFI) {
                "হানাফী (দ্বিগুণ ছায়া - Hanafi 2x shadow)"
              } else {
                "শাফেয়ী, মালিকী, হাম্বলী (একগুণ ছায়া - Standard 1x shadow)"
              },
              fontSize = 13.sp
            )
          }
        }

        Spacer(modifier = Modifier.height(24.dp))
      }
    }
  }
}

@Composable
fun PrayerDetailRow(
  prayer: PrayerTime,
  isBangla: Boolean,
  isCompleted: Boolean,
  onToggleCompleted: () -> Unit
) {
  val isNext = prayer.isNext
  val isCurrent = prayer.isCurrent
  val isSunrise = prayer.id == "sunrise"

  val containerColor = when {
    isNext -> IslamicEmerald.copy(alpha = 0.12f)
    isCurrent -> PrayerActiveGreen.copy(alpha = 0.08f)
    else -> MaterialTheme.colorScheme.surface
  }

  val borderColor = when {
    isNext -> IslamicEmerald
    isCurrent -> PrayerActiveGreen
    else -> Color.Transparent
  }

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 4.dp),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = containerColor),
    elevation = CardDefaults.cardElevation(defaultElevation = if (isNext) 2.dp else 0.5.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(
              if (isNext) IslamicEmerald else if (isCurrent) PrayerActiveGreen else IslamicEmerald.copy(alpha = 0.1f)
            ),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = prayer.arabicName.take(1),
            color = if (isNext || isCurrent) Color.White else IslamicEmerald,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
          )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = if (isBangla) prayer.nameBn else prayer.nameEn,
              color = MaterialTheme.colorScheme.onSurface,
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold
            )
            if (isNext) {
              Spacer(modifier = Modifier.width(6.dp))
              Surface(
                shape = RoundedCornerShape(4.dp),
                color = IslamicEmerald
              ) {
                Text(
                  text = if (isBangla) "পরবর্তী" else "Next",
                  color = Color.White,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            } else if (isCurrent) {
              Spacer(modifier = Modifier.width(6.dp))
              Surface(
                shape = RoundedCornerShape(4.dp),
                color = PrayerActiveGreen
              ) {
                Text(
                  text = if (isBangla) "বর্তমান" else "Current",
                  color = Color.White,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }
          }

          Text(
            text = prayer.arabicName,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp
          )
        }
      }

      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = prayer.timeString,
          color = if (isNext) IslamicEmerald else MaterialTheme.colorScheme.onSurface,
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold
        )

        if (!isSunrise) {
          Spacer(modifier = Modifier.width(12.dp))
          Icon(
            imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Outlined.CheckCircleOutline,
            contentDescription = "Prayer completed",
            tint = if (isCompleted) IslamicEmerald else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
            modifier = Modifier
              .size(24.dp)
              .clickable { onToggleCompleted() }
          )
        }
      }
    }
  }
}
