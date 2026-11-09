package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.IslamicDataProvider
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
import com.example.viewmodel.NoorlifeViewModel
import java.util.Calendar

@Composable
fun HomeScreen(
  viewModel: NoorlifeViewModel,
  onNavigateToPrayer: () -> Unit,
  onNavigateToQuran: () -> Unit,
  onNavigateToTasbih: () -> Unit,
  onNavigateToRamadan: () -> Unit,
  onNavigateToQibla: () -> Unit,
  onNavigateToAllahNames: () -> Unit,
  onNavigateToDuas: () -> Unit,
  onNavigateToHadith: () -> Unit,
  onNavigateToCalendar: () -> Unit,
  onNavigateToKnowledge: () -> Unit,
  modifier: Modifier = Modifier
) {
  val isBangla by viewModel.isBangla.collectAsState()
  val prayerResult by viewModel.prayerResult.collectAsState()
  val currentDistrict by viewModel.currentDistrict.collectAsState()
  val hijriAdjustment by viewModel.hijriAdjustment.collectAsState()
  val goodDeeds by viewModel.goodDeeds.collectAsState()

  val cal = Calendar.getInstance()
  val hijriDate = DateUtils.getHijriDate(cal, hijriAdjustment)
  val banglaDate = DateUtils.getBanglaDate(cal)
  val gregorianDate = DateUtils.getGregorianFormatted(cal, isBangla)

  val nextPrayer = prayerResult.prayers.getOrNull(prayerResult.nextPrayerIndex)
  val countdownStr = DateUtils.formatCountdown(prayerResult.timeToNextMillis, isBangla)

  val dailyDua = IslamicDataProvider.duas.first()
  val dailyHadith = IslamicDataProvider.hadiths.first()

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("home_screen_content"),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // 1. Assalamu Alaikum & Islamic Header Card
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
          .background(
            Brush.verticalGradient(
              listOf(IslamicEmerald, IslamicEmeraldDark)
            )
          )
          .padding(horizontal = 20.dp, vertical = 20.dp)
      ) {
        Column {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = if (isBangla) "আসসালামু আলাইকুম" else "Assalamu Alaikum",
                color = IslamicGoldLight,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
              )
              Text(
                text = if (isBangla) "আজকের বরকতময় দিন" else "Blessed Islamic Day",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
              )
            }

            Surface(
              shape = RoundedCornerShape(12.dp),
              color = IslamicEmeraldLight.copy(alpha = 0.5f)
            ) {
              Text(
                text = if (isBangla) currentDistrict.nameBn else currentDistrict.nameEn,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Triple Date Display: Hijri, Bangla, English
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White.copy(alpha = 0.12f),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.NightsStay,
                  contentDescription = "Hijri",
                  tint = IslamicGold,
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = if (isBangla) hijriDate.formattedBn else hijriDate.formattedEn,
                  color = IslamicGoldLight,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold
                )
              }

              Spacer(modifier = Modifier.height(4.dp))

              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Text(
                  text = banglaDate,
                  color = Color.White.copy(alpha = 0.9f),
                  fontSize = 12.sp
                )
                Text(
                  text = gregorianDate,
                  color = Color.White.copy(alpha = 0.8f),
                  fontSize = 12.sp
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Next Prayer Countdown Banner
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = IslamicGold.copy(alpha = 0.15f),
            modifier = Modifier
              .fillMaxWidth()
              .clickable { onNavigateToPrayer() }
          ) {
            Row(
              modifier = Modifier.padding(14.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Column {
                Text(
                  text = if (isBangla) "পরবর্তী সালাত" else "Next Prayer",
                  color = IslamicGoldLight,
                  fontSize = 12.sp
                )
                Text(
                  text = if (isBangla) (nextPrayer?.nameBn ?: "") else (nextPrayer?.nameEn ?: ""),
                  color = Color.White,
                  fontSize = 20.sp,
                  fontWeight = FontWeight.Bold
                )
                Text(
                  text = nextPrayer?.timeString ?: "",
                  color = Color.White.copy(alpha = 0.9f),
                  fontSize = 13.sp
                )
              }

              Column(horizontalAlignment = Alignment.End) {
                Text(
                  text = if (isBangla) "বাকি আছে" else "Time Remaining",
                  color = IslamicGoldLight,
                  fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = IslamicGold
                ) {
                  Text(
                    text = countdownStr,
                    color = IslamicEmeraldDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                  )
                }
              }
            }
          }
        }
      }
    }

    // 2. Today's Prayer Time Cards Carousel/Row
    item {
      Spacer(modifier = Modifier.height(14.dp))
      SectionHeader(
        title = if (isBangla) "আজকের নামাজের সময়সূচি" else "Today's Prayer Schedule",
        actionText = if (isBangla) "বিস্তারিত" else "Details",
        onActionClick = onNavigateToPrayer
      )

      LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        items(prayerResult.prayers) { prayer ->
          PrayerQuickCard(
            prayer = prayer,
            isBangla = isBangla,
            onClick = onNavigateToPrayer
          )
        }
      }
    }

    // 3. Quick Feature Shortcuts Grid
    item {
      Spacer(modifier = Modifier.height(16.dp))
      SectionHeader(
        title = if (isBangla) "ইসলামিক ফিচারসমূহ" else "Islamic Features"
      )

      val shortcuts = listOf(
        ShortcutItem("quran", if (isBangla) "আল-কুরআন" else "Quran", Icons.Default.Book, IslamicEmerald, onNavigateToQuran),
        ShortcutItem("tasbih", if (isBangla) "ডিজিটাল তাসবিহ" else "Tasbih", Icons.Default.TouchApp, IslamicGoldDark, onNavigateToTasbih),
        ShortcutItem("ramadan", if (isBangla) "পবিত্র রমজান" else "Ramadan", Icons.Default.NightsStay, IslamicEmeraldLight, onNavigateToRamadan),
        ShortcutItem("qibla", if (isBangla) "কিবলা কম্পাস" else "Qibla", Icons.Default.Explore, IslamicEmerald, onNavigateToQibla),
        ShortcutItem("names", if (isBangla) "আল্লাহর নাম" else "99 Names", Icons.Default.Star, IslamicGoldDark, onNavigateToAllahNames),
        ShortcutItem("dua", if (isBangla) "দোয়া ও জিকির" else "Duas", Icons.Default.Mosque, IslamicEmeraldLight, onNavigateToDuas),
        ShortcutItem("hadith", if (isBangla) "আল-হাদিস" else "Hadith", Icons.Default.Book, IslamicEmerald, onNavigateToHadith),
        ShortcutItem("calendar", if (isBangla) "ক্যালেন্ডার" else "Calendar", Icons.Default.CalendarMonth, IslamicGoldDark, onNavigateToCalendar)
      )

      Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        for (i in shortcuts.indices step 4) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            for (j in 0 until 4) {
              val index = i + j
              if (index < shortcuts.size) {
                val item = shortcuts[index]
                QuickShortcutButton(item = item, modifier = Modifier.weight(1f))
              } else {
                Spacer(modifier = Modifier.weight(1f))
              }
            }
          }
        }
      }
    }

    // 4. Daily Dua Card
    item {
      Spacer(modifier = Modifier.height(16.dp))
      SectionHeader(
        title = if (isBangla) "আজকের নির্বাচিত দোয়া" else "Daily Dua",
        actionText = if (isBangla) "সব দোয়া" else "All Duas",
        onActionClick = onNavigateToDuas
      )

      IslamicCard(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        onClick = onNavigateToDuas
      ) {
        Column {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = if (isBangla) dailyDua.titleBn else dailyDua.titleEn,
              color = IslamicEmerald,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold
            )
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = IslamicEmerald.copy(alpha = 0.1f)
            ) {
              Text(
                text = dailyDua.reference,
                color = IslamicEmerald,
                fontSize = 11.sp,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = dailyDua.arabic,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
          )

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = if (isBangla) dailyDua.meaningBn else dailyDua.meaningEn,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 13.sp,
            lineHeight = 19.sp
          )
        }
      }
    }

    // 5. Daily Hadith Card
    item {
      Spacer(modifier = Modifier.height(16.dp))
      SectionHeader(
        title = if (isBangla) "আজকের হাদিস" else "Hadith of the Day",
        actionText = if (isBangla) "আরও হাদিস" else "More Hadith",
        onActionClick = onNavigateToHadith
      )

      IslamicCard(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        onClick = onNavigateToHadith
      ) {
        Column {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = if (isBangla) dailyHadith.narrator else dailyHadith.narrator,
              color = IslamicGoldDark,
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold
            )
            Text(
              text = dailyHadith.source,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 11.sp
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = dailyHadith.arabic,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 17.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
          )

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = if (isBangla) dailyHadith.banglaText else dailyHadith.englishText,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 13.sp,
            lineHeight = 19.sp
          )
        }
      }
    }

    // 6. Daily Good Deeds Checklist
    item {
      Spacer(modifier = Modifier.height(16.dp))
      val doneCount = goodDeeds.count { it.isDone }
      val totalCount = goodDeeds.size

      SectionHeader(
        title = if (isBangla) "আজকের নেক আমল ($doneCount/$totalCount)" else "Daily Good Deeds ($doneCount/$totalCount)"
      )

      IslamicCard(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
      ) {
        Column {
          LinearProgressIndicator(
            progress = { if (totalCount > 0) doneCount.toFloat() / totalCount else 0f },
            modifier = Modifier
              .fillMaxWidth()
              .height(6.dp)
              .clip(RoundedCornerShape(3.dp)),
            color = IslamicEmerald,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
          )

          Spacer(modifier = Modifier.height(12.dp))

          goodDeeds.take(4).forEach { deed ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.toggleGoodDeed(deed.id) }
                .padding(vertical = 6.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = if (deed.isDone) Icons.Default.CheckCircle else Icons.Outlined.CheckCircleOutline,
                contentDescription = "Toggle",
                tint = if (deed.isDone) IslamicEmerald else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
              )
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = if (isBangla) deed.titleBn else deed.titleEn,
                color = if (deed.isDone) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface,
                fontSize = 13.sp
              )
            }
          }
        }
      }
    }
  }
}

data class ShortcutItem(
  val id: String,
  val label: String,
  val icon: ImageVector,
  val color: Color,
  val onClick: () -> Unit
)

@Composable
fun QuickShortcutButton(item: ShortcutItem, modifier: Modifier = Modifier) {
  Column(
    modifier = modifier
      .clickable { item.onClick() }
      .padding(4.dp)
      .testTag("shortcut_${item.id}"),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box(
      modifier = Modifier
        .size(52.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = item.icon,
        contentDescription = item.label,
        tint = item.color,
        modifier = Modifier.size(26.dp)
      )
    }
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = item.label,
      color = MaterialTheme.colorScheme.onBackground,
      fontSize = 11.sp,
      fontWeight = FontWeight.Medium,
      textAlign = TextAlign.Center,
      maxLines = 1
    )
  }
}

@Composable
fun PrayerQuickCard(
  prayer: PrayerTime,
  isBangla: Boolean,
  onClick: () -> Unit
) {
  val isCurrent = prayer.isCurrent
  val isNext = prayer.isNext

  val bgColor = when {
    isNext -> IslamicEmerald
    isCurrent -> PrayerActiveGreen
    else -> MaterialTheme.colorScheme.surface
  }

  val textColor = if (isNext || isCurrent) Color.White else MaterialTheme.colorScheme.onSurface
  val subTextColor = if (isNext || isCurrent) IslamicGoldLight else MaterialTheme.colorScheme.onSurfaceVariant

  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = bgColor),
    elevation = CardDefaults.cardElevation(defaultElevation = if (isNext) 4.dp else 1.dp),
    modifier = Modifier
      .width(100.dp)
      .clickable { onClick() }
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 12.dp, horizontal = 8.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = if (isBangla) prayer.nameBn else prayer.nameEn,
        color = textColor,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
      )
      Text(
        text = prayer.arabicName,
        color = subTextColor,
        fontSize = 11.sp
      )
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = prayer.timeString,
        color = textColor,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
      )
      if (isNext) {
        Spacer(modifier = Modifier.height(4.dp))
        Surface(
          shape = RoundedCornerShape(4.dp),
          color = IslamicGold
        ) {
          Text(
            text = if (isBangla) "পরবর্তী" else "Next",
            color = IslamicEmeraldDark,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
          )
        }
      }
    }
  }
}
