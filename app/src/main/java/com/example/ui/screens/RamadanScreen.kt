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
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.ui.components.IslamicCard
import com.example.ui.components.IslamicGeometricDivider
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
fun RamadanScreen(
  viewModel: NoorlifeViewModel,
  modifier: Modifier = Modifier
) {
  val isBangla by viewModel.isBangla.collectAsState()
  val prayerResult by viewModel.prayerResult.collectAsState()
  val currentDistrict by viewModel.currentDistrict.collectAsState()
  val isFastingToday by viewModel.isFastingToday.collectAsState()
  val hijriAdjustment by viewModel.hijriAdjustment.collectAsState()

  var selectedTab by remember { mutableIntStateOf(0) } // 0=Timetable, 1=Duas & Rules

  val cal = Calendar.getInstance()
  val hijriDate = DateUtils.getHijriDate(cal, hijriAdjustment)
  val isRamadanActive = hijriDate.month == 9

  val fajr = prayerResult.prayers.find { it.id == "fajr" }
  val maghrib = prayerResult.prayers.find { it.id == "maghrib" }

  val schedule = remember(isBangla) { viewModel.getRamadanSchedule() }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("ramadan_screen_content"),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // 1. Ramadan Lantern & Moon Hero Banner
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
                text = if (isBangla) "পবিত্র মাহে রমজানুল মুবারক" else "Blessed Month of Ramadan",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = if (isBangla) "${currentDistrict.nameBn} জেলার সময়সূচি" else "Timetable for ${currentDistrict.nameEn}",
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
                imageVector = Icons.Default.NightsStay,
                contentDescription = "Ramadan Moon",
                tint = IslamicGold,
                modifier = Modifier.size(28.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Smart Status Card
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White.copy(alpha = 0.12f),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(16.dp)) {
              if (isRamadanActive) {
                Text(
                  text = if (isBangla) "রমজানের পবিত্র সিয়াম কাল চলমান" else "Ramadan Fasting In Progress",
                  color = IslamicGoldLight,
                  fontSize = 13.sp,
                  fontWeight = FontWeight.SemiBold
                )
              } else {
                Text(
                  text = if (isBangla) "আসন্ন রমজানুল মুবারক ১৪৪৮ হিজরি" else "Upcoming Ramadan 1448 AH",
                  color = IslamicGoldLight,
                  fontSize = 13.sp,
                  fontWeight = FontWeight.SemiBold
                )
                Text(
                  text = if (isBangla) "পবিত্র সিয়াম ও আত্মশুদ্ধির প্রস্তুতি গ্রহণ করুন" else "Prepare your heart for the month of mercy & forgiveness",
                  color = Color.White,
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Bold
                )
              }

              Spacer(modifier = Modifier.height(12.dp))

              // Today's Sehri & Iftar Times
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                // Sehri Card
                Surface(
                  shape = RoundedCornerShape(12.dp),
                  color = IslamicEmerald.copy(alpha = 0.5f),
                  modifier = Modifier.weight(1f)
                ) {
                  Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                    Icon(
                      imageVector = Icons.Default.WbSunny,
                      contentDescription = "Sehri",
                      tint = IslamicGold,
                      modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                      text = if (isBangla) "সেহরির শেষ সময়" else "Sehri Ends",
                      color = IslamicGoldLight,
                      fontSize = 11.sp
                    )
                    Text(
                      text = fajr?.timeString ?: "04:45 AM",
                      color = Color.White,
                      fontSize = 15.sp,
                      fontWeight = FontWeight.Bold
                    )
                  }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Iftar Card
                Surface(
                  shape = RoundedCornerShape(12.dp),
                  color = IslamicEmerald.copy(alpha = 0.5f),
                  modifier = Modifier.weight(1f)
                ) {
                  Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                    Icon(
                      imageVector = Icons.Default.NightsStay,
                      contentDescription = "Iftar",
                      tint = IslamicGold,
                      modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                      text = if (isBangla) "ইফতারের সময়" else "Iftar Time",
                      color = IslamicGoldLight,
                      fontSize = 11.sp
                    )
                    Text(
                      text = maghrib?.timeString ?: "05:40 PM",
                      color = Color.White,
                      fontSize = 15.sp,
                      fontWeight = FontWeight.Bold
                    )
                  }
                }
              }

              Spacer(modifier = Modifier.height(12.dp))

              // Fasting status toggle button
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(10.dp))
                  .background(Color.White.copy(alpha = 0.15f))
                  .clickable { viewModel.toggleFastingToday() }
                  .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(
                    imageVector = if (isFastingToday) Icons.Default.CheckCircle else Icons.Outlined.CheckCircleOutline,
                    contentDescription = "Fasting today",
                    tint = if (isFastingToday) IslamicGold else Color.White
                  )
                  Spacer(modifier = Modifier.width(8.dp))
                  Text(
                    text = if (isBangla) "আমি আজ রোজা রেখেছি" else "I am fasting today",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                  )
                }

                Text(
                  text = if (isFastingToday) (if (isBangla) "আলহামদুলিল্লাহ" else "Alhamdulillah") else (if (isBangla) "চিহ্নিত করুন" else "Check"),
                  color = IslamicGoldLight,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }
        }
      }
    }

    // Tabs: 30-Day Timetable vs Duas & Guidelines
    item {
      TabRow(
        selectedTabIndex = selectedTab,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = IslamicEmerald
      ) {
        Tab(
          selected = selectedTab == 0,
          onClick = { selectedTab = 0 },
          text = { Text(if (isBangla) "৩০ দিনের ক্যালেন্ডার" else "30-Day Timetable", fontWeight = FontWeight.SemiBold) }
        )
        Tab(
          selected = selectedTab == 1,
          onClick = { selectedTab = 1 },
          text = { Text(if (isBangla) "রোজার দোয়া ও আমল" else "Duas & Checklist", fontWeight = FontWeight.SemiBold) }
        )
      }
    }

    if (selectedTab == 0) {
      item {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(text = if (isBangla) "দিন" else "Day", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
          Text(text = if (isBangla) "সেহরি শেষ" else "Sehri Ends", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
          Text(text = if (isBangla) "ইফতার" else "Iftar", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
      }

      items(schedule) { day ->
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = if (day.day == 1) IslamicEmerald.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface,
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 3.dp)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(28.dp)
                  .clip(CircleShape)
                  .background(if (day.day == 1) IslamicEmerald else MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = if (isBangla) DateUtils.toBanglaDigits(day.day.toString()) else day.day.toString(),
                  color = if (day.day == 1) Color.White else MaterialTheme.colorScheme.onSurface,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold
                )
              }
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = day.dateString,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
              )
            }

            Text(
              text = day.sehriTime,
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurface
            )

            Text(
              text = day.iftarTime,
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = IslamicEmerald
            )
          }
        }
      }
    } else {
      // Duas & Rules Tab
      item {
        Spacer(modifier = Modifier.height(16.dp))

        // Sehri Niyyah Card
        IslamicCard(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        ) {
          Column {
            Text(
              text = if (isBangla) "রোজা রাখার নিয়ত (সাহরির পর)" else "Intention for Fasting (Niyyah)",
              color = IslamicEmerald,
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "نَوَيْتُ أَنْ أَصُومَ غَدًا مِنْ شَهْرِ رَمَضَانَ الْمُبَارَكِ فَرْضًا لَكَ يَا اللَّهُ فَتَقَبَّلْ مِنِّي إِنَّكَ أَنْتَ السَّمِيعُ الْعَلِيمُ",
              fontSize = 18.sp,
              fontWeight = FontWeight.Medium,
              textAlign = TextAlign.End,
              modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = if (isBangla) "উচ্চারণ: নাওয়াইতু আন আসূমা গাদাম মিন শাহরি রমাদানাল মুবারকি ফারদাল্লাকা ইয়া আল্লাহু, ফাতাক্বাব্বাল মিন্নী ইন্নাকা আনতাস সামীউল আলীম।"
              else "Transliteration: Nawaytu an asuma ghadan min shahri ramadan...",
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = if (isBangla) "অর্থ: হে আল্লাহ! আমি আগামীকালের পবিত্র রমজান মাসের ফরজ রোজা রাখার সংকল্প করলাম। অতএব আপনি আমার পক্ষ হতে তা কবুল করুন; নিশ্চয় আপনি সর্বশ্রোতা, সর্বজ্ঞাত।"
              else "Meaning: O Allah! I intend to fast tomorrow in this blessed month of Ramadan as an obligation to You...",
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Iftar Dua Card
        IslamicCard(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        ) {
          Column {
            Text(
              text = if (isBangla) "ইফতারের দোয়া" else "Iftar Supplication",
              color = IslamicEmerald,
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "اللَّهُمَّ لَكَ صُمْتُ وَعَلَىٰ رِزْقِكَ أَفْطَرْتُ ، ذَهَبَ الظَّمَأُ وَابْتَلَّتِ الْعُرُوقُ وَثَبَتَ الأَجْرُ إِنْ شَاءَ اللَّهُ",
              fontSize = 18.sp,
              fontWeight = FontWeight.Medium,
              textAlign = TextAlign.End,
              modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = if (isBangla) "উচ্চারণ: আল্লাহুম্মা লাকা সুমতু ওয়া আলা রিযক্বিকা আফতারতু। যাহাবায যমউ ওয়াবতাল্লাতিল উরূকু ওয়া সাবাতাল আজরু ইনশাআল্লাহ।"
              else "Transliteration: Allahumma laka sumtu wa 'ala rizqika aftartu...",
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = if (isBangla) "অর্থ: হে আল্লাহ! আমি আপনার সন্তুষ্টির জন্য রোজা রেখেছি এবং আপনারই রিযিক দ্বারা ইফতার করছি। পিপাসা নিবৃত্ত হয়েছে, শিরা সিক্ত হয়েছে এবং প্রতিদান সুনিশ্চিত হয়েছে ইনশাআল্লাহ।"
              else "Meaning: O Allah, for You I fasted and with Your provision I broke my fast...",
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Ramadan Checklist
        SectionHeader(title = if (isBangla) "রমজানের দৈনিক আমল চেকলিস্ট" else "Daily Ramadan Checklist")
        val ramadanDeeds = listOf(
          "সেহরি খাওয়া ও তাহাজ্জুদ আদায় করা",
          "৫ ওয়াক্ত নামাজ জামাতে আদায় করা",
          "তারাবীহর সালাত মনোযোগ সহকারে সম্পন্ন করা",
          "প্রতিদিন অন্তত ১ পারা বা অংশ কুরআন তিলাওয়াত",
          "ইফতারের পূর্ব মুহূর্তে দোয়ায় মশগুল হওয়া",
          "দরিদ্র ও অসহায়দের সাদাকাহ বা ইফতার করানো"
        )
        IslamicCard(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        ) {
          Column {
            ramadanDeeds.forEach { deed ->
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.CheckCircle,
                  contentDescription = "Item",
                  tint = IslamicEmerald,
                  modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                  text = deed,
                  fontSize = 13.sp,
                  color = MaterialTheme.colorScheme.onSurface
                )
              }
            }
          }
        }
      }
    }
  }
}
