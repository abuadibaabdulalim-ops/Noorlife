package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.IslamicDataProvider
import com.example.model.IslamicEvent
import com.example.ui.components.IslamicCard
import com.example.ui.components.SectionHeader
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicEmeraldDark
import com.example.ui.theme.IslamicEmeraldLight
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldDark
import com.example.ui.theme.IslamicGoldLight
import com.example.util.DateUtils
import com.example.viewmodel.NoorlifeViewModel
import java.util.Calendar

@Composable
fun CalendarScreen(
  viewModel: NoorlifeViewModel,
  modifier: Modifier = Modifier
) {
  val isBangla by viewModel.isBangla.collectAsState()
  val hijriAdjustment by viewModel.hijriAdjustment.collectAsState()

  val cal = Calendar.getInstance()
  val hijriDate = DateUtils.getHijriDate(cal, hijriAdjustment)
  val banglaDate = DateUtils.getBanglaDate(cal)
  val gregorianDate = DateUtils.getGregorianFormatted(cal, isBangla)

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("calendar_screen_content"),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // 1. Triple Calendar Hero Box
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
                text = if (isBangla) "ইসলামিক হিজরি ক্যালেন্ডার" else "Islamic Hijri Calendar",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = if (isBangla) "চাঁদ দেখার ভিত্তিতে দিন গণনা" else "Lunar Moon Sighting Calendar",
                color = IslamicGoldLight,
                fontSize = 12.sp
              )
            }

            Box(
              modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(IslamicGold.copy(alpha = 0.2f)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = "Calendar",
                tint = IslamicGold,
                modifier = Modifier.size(26.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Big Hijri Date Box
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White.copy(alpha = 0.12f),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(
              modifier = Modifier.padding(16.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = if (isBangla) hijriDate.formattedBn else hijriDate.formattedEn,
                color = IslamicGoldLight,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
              )

              Spacer(modifier = Modifier.height(6.dp))

              Text(
                text = "$banglaDate • $gregorianDate",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 13.sp
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Hijri Date Adjustment Stepper
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = IslamicEmeraldLight.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(
                  text = if (isBangla) "চাঁদ দেখার সমন্বয়" else "Moon Sighting Adjustment",
                  color = Color.White,
                  fontSize = 12.sp,
                  fontWeight = FontWeight.SemiBold
                )
                Text(
                  text = if (hijriAdjustment == 0) (if (isBangla) "স্বাভাবিক হিসাব" else "Standard calculation")
                  else (if (isBangla) "সমন্বয়: ${if (hijriAdjustment > 0) "+" else ""}${DateUtils.toBanglaDigits(hijriAdjustment.toString())} দিন"
                  else "Adjusted: ${if (hijriAdjustment > 0) "+" else ""}$hijriAdjustment day(s)"),
                  color = IslamicGoldLight,
                  fontSize = 11.sp
                )
              }

              Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                  onClick = { viewModel.adjustHijri(-1) },
                  modifier = Modifier.size(32.dp)
                ) {
                  Icon(Icons.Default.Remove, contentDescription = "Minus", tint = Color.White)
                }

                Text(
                  text = if (isBangla) DateUtils.toBanglaDigits(hijriAdjustment.toString()) else hijriAdjustment.toString(),
                  color = Color.White,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 4.dp)
                )

                IconButton(
                  onClick = { viewModel.adjustHijri(1) },
                  modifier = Modifier.size(32.dp)
                ) {
                  Icon(Icons.Default.Add, contentDescription = "Plus", tint = Color.White)
                }
              }
            }
          }
        }
      }
    }

    // 2. Important Islamic Days List
    item {
      Spacer(modifier = Modifier.height(16.dp))
      SectionHeader(
        title = if (isBangla) "গুরুত্বপূর্ণ ইসলামিক দিন ও রজনী" else "Important Islamic Dates"
      )
    }

    items(IslamicDataProvider.importantIslamicEvents) { event ->
      IslamicCard(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 4.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = if (isBangla) event.titleBn else event.titleEn,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = if (isBangla) event.descriptionBn else event.descriptionEn,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              lineHeight = 16.sp
            )
          }

          Spacer(modifier = Modifier.width(12.dp))

          Surface(
            shape = RoundedCornerShape(8.dp),
            color = IslamicEmerald.copy(alpha = 0.1f)
          ) {
            Text(
              text = if (isBangla) event.hijriDateBn else event.hijriDateEn,
              color = IslamicEmerald,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
        }
      }
    }
  }
}
