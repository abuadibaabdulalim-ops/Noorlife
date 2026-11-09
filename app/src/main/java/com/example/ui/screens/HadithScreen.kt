package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.IslamicDataProvider
import com.example.model.HadithItem
import com.example.ui.components.IslamicCard
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldDark
import com.example.viewmodel.NoorlifeViewModel

@Composable
fun HadithScreen(
  viewModel: NoorlifeViewModel,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val isBangla by viewModel.isBangla.collectAsState()
  val favoriteHadiths by viewModel.favoriteHadiths.collectAsState()

  var searchQuery by remember { mutableStateOf("") }

  val filteredHadiths = IslamicDataProvider.hadiths.filter { hadith ->
    if (searchQuery.isBlank()) true else {
      hadith.titleBn.contains(searchQuery, ignoreCase = true) ||
        hadith.banglaText.contains(searchQuery, ignoreCase = true) ||
        hadith.arabic.contains(searchQuery) ||
        hadith.narrator.contains(searchQuery, ignoreCase = true)
    }
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("hadith_screen_content")
  ) {
    // Search Bar
    OutlinedTextField(
      value = searchQuery,
      onValueChange = { searchQuery = it },
      placeholder = {
        Text(
          text = if (isBangla) "সহীহ হাদিস খুঁজুন..." else "Search Authentic Hadith...",
          fontSize = 13.sp
        )
      },
      leadingIcon = {
        Icon(Icons.Default.Search, contentDescription = "Search", tint = IslamicEmerald)
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .testTag("hadith_search_input"),
      shape = RoundedCornerShape(14.dp),
      colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = IslamicEmerald,
        unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant
      ),
      singleLine = true
    )

    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 90.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      items(filteredHadiths) { hadith ->
        val isFavorite = favoriteHadiths.contains(hadith.id)
        HadithCardItem(
          hadith = hadith,
          isBangla = isBangla,
          isFavorite = isFavorite,
          onToggleFavorite = { viewModel.toggleFavoriteHadith(hadith.id) },
          onCopy = {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Hadith", "${hadith.arabic}\n\n${hadith.banglaText}\n\n[বর্ণনাকারী: ${hadith.narrator} • সূত্র: ${hadith.source}]")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, if (isBangla) "হাদিস কপি করা হয়েছে" else "Hadith copied to clipboard", Toast.LENGTH_SHORT).show()
          },
          onShare = {
            val sendIntent = Intent().apply {
              action = Intent.ACTION_SEND
              putExtra(Intent.EXTRA_TEXT, "${hadith.titleBn}\n\n${hadith.arabic}\n\n${hadith.banglaText}\n\n[বর্ণনাকারী: ${hadith.narrator} • সূত্র: ${hadith.source} • নুরলাইফ অ্যাপ]")
              type = "text/plain"
            }
            context.startActivity(Intent.createChooser(sendIntent, "Share Hadith"))
          }
        )
      }
    }
  }
}

@Composable
fun HadithCardItem(
  hadith: HadithItem,
  isBangla: Boolean,
  isFavorite: Boolean,
  onToggleFavorite: () -> Unit,
  onCopy: () -> Unit,
  onShare: () -> Unit
) {
  IslamicCard(modifier = Modifier.fillMaxWidth()) {
    Column {
      // Header: Narrator & Source
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = if (isBangla) hadith.titleBn else hadith.titleEn,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = IslamicEmerald
          )
          Text(
            text = "${hadith.narrator} • ${hadith.source}",
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Row {
          IconButton(onClick = onToggleFavorite, modifier = Modifier.size(32.dp)) {
            Icon(
              imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
              contentDescription = "Favorite",
              tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
          }

          IconButton(onClick = onCopy, modifier = Modifier.size(32.dp)) {
            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = MaterialTheme.colorScheme.onSurfaceVariant)
          }

          IconButton(onClick = onShare, modifier = Modifier.size(32.dp)) {
            Icon(Icons.Default.Share, contentDescription = "Share", tint = MaterialTheme.colorScheme.onSurfaceVariant)
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Arabic Hadith
      Text(
        text = hadith.arabic,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.End,
        lineHeight = 27.sp,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(8.dp))

      // Bangla Translation
      Text(
        text = if (isBangla) hadith.banglaText else hadith.englishText,
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.onSurface,
        lineHeight = 19.sp
      )
    }
  }
}
