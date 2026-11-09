package com.example.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Verified
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.IslamicCard
import com.example.ui.components.IslamicGeometricDivider
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldDark
import com.example.ui.theme.IslamicGoldLight
import com.example.viewmodel.NoorlifeViewModel

@Composable
fun AboutScreen(
  viewModel: NoorlifeViewModel,
  modifier: Modifier = Modifier
) {
  val isBangla by viewModel.isBangla.collectAsState()

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("about_screen_content"),
    contentPadding = PaddingValues(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 90.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    item {
      // Noorlife Logo
      Box(
        modifier = Modifier
          .size(110.dp)
          .clip(CircleShape)
          .background(IslamicEmerald.copy(alpha = 0.12f))
          .padding(4.dp),
        contentAlignment = Alignment.Center
      ) {
        Image(
          painter = painterResource(id = R.drawable.ic_noorlife_logo),
          contentDescription = "Noorlife Logo",
          modifier = Modifier
            .fillMaxSize()
            .clip(CircleShape)
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      Text(
        text = "Noorlife",
        fontSize = 30.sp,
        fontWeight = FontWeight.ExtraBold,
        color = IslamicEmerald
      )

      Text(
        text = if (isBangla) "আপনার সার্বিক ইসলামিক সঙ্গী" else "Your Complete Islamic Companion",
        fontSize = 15.sp,
        color = IslamicGoldDark,
        fontWeight = FontWeight.Medium
      )

      Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.padding(top = 6.dp)
      ) {
        Text(
          text = if (isBangla) "সংস্করণ ১.০ (Version 1.0)" else "Version 1.0",
          fontSize = 11.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
      }

      Spacer(modifier = Modifier.height(20.dp))

      // CREATOR CARD
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = IslamicEmerald)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Verified,
              contentDescription = "Creator",
              tint = IslamicGold,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = if (isBangla) "পরিকল্পনা ও রূপায়ণে" else "CREATED BY",
              color = IslamicGoldLight,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.2.sp
            )
          }

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = "Junaid Al Arib Ansari",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold
          )

          Text(
            text = if (isBangla) "জুনাইদ আল আরিব আনসারী" else "Creator & Architect",
            color = IslamicGoldLight.copy(alpha = 0.9f),
            fontSize = 13.sp
          )
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Description Card
      IslamicCard(modifier = Modifier.fillMaxWidth()) {
        Column {
          Text(
            text = if (isBangla) "অ্যাপ সম্পর্কে" else "About Noorlife",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = IslamicEmerald
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = if (isBangla) {
              "নুরলাইফ (Noorlife) হলো একটি পূর্ণাঙ্গ, আধুনিক ও প্রিমিয়াম ইসলামিক লাইফস্টাইল অ্যাপ্লিকেশন। এটি একজন মুসলমানের প্রাত্যহিক ধর্মীয় জীবনের সকল অপরিহার্য অনুষঙ্গ—নামাজের সঠিক সময়সূচি ও নিষিদ্ধ সময়, পবিত্র কুরআনের ১১৪টি সূরার তিলাওয়াত ও অনুবাদ, ডিজিটাল তাসবিহ, পবিত্র মাহে রমজানের সেহরি-ইফতার সময়সূচি, কিবলা কম্পাস, আল্লাহর ৯৯টি গুণবাচক নাম, সহীহ হাদিস ও দোয়াসমূহ অফলাইনে সহজে ব্যবহারের জন্য তৈরি করা হয়েছে।"
            } else {
              "Noorlife is your complete Islamic companion for everyday life. Built with devotion to assist Muslims worldwide with accurate prayer times, offline Holy Quran, digital Tasbih, Ramadan tools, authentic Hadiths, and essential Islamic guides."
            },
            fontSize = 13.sp,
            lineHeight = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
          )
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Authentic Islamic References Card
      IslamicCard(modifier = Modifier.fillMaxWidth()) {
        Column {
          Text(
            text = if (isBangla) "বিশ্বস্ত তথ্যসূত্র ও মানদণ্ড" else "Authentic References",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = IslamicEmerald
          )
          Spacer(modifier = Modifier.height(8.dp))
          val sources = listOf(
            "ইসলামিক ফাউন্ডেশন বাংলাদেশ (Islamic Foundation Bangladesh)",
            "সহীহ আল-বুখারী ও সহীহ মুসলিম (Sahih al-Bukhari & Muslim)",
            "হিসনুল মুসলিম (Hisnul Muslim - দৈনন্দিন দোয়া)",
            "উম্মুল কুরা বিশ্ববিদ্যালয়, মক্কা (Umm Al-Qura University)"
          )
          sources.forEach { src ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Box(
                modifier = Modifier
                  .size(6.dp)
                  .clip(CircleShape)
                  .background(IslamicGold)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = src,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(24.dp))

      Text(
        text = "© 2026 Noorlife • All Rights Reserved",
        fontSize = 11.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
      )
    }
  }
}
