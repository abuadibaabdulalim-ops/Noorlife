package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.model.DuaItem
import com.example.ui.components.IslamicCard
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldDark
import com.example.viewmodel.NoorlifeViewModel

@Composable
fun DuaScreen(
  viewModel: NoorlifeViewModel,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val isBangla by viewModel.isBangla.collectAsState()
  val favoriteDuas by viewModel.favoriteDuas.collectAsState()

  var searchQuery by remember { mutableStateOf("") }
  var selectedCategory by remember { mutableStateOf("All") }

  val categories = listOf(
    "All" to (if (isBangla) "সকল" else "All"),
    "Morning" to (if (isBangla) "সকাল" else "Morning"),
    "Evening" to (if (isBangla) "সন্ধ্যা" else "Evening"),
    "Sleeping" to (if (isBangla) "ঘুম" else "Sleep"),
    "Waking Up" to (if (isBangla) "জাগরণ" else "Wakeup"),
    "Eating" to (if (isBangla) "খাবার" else "Eating"),
    "Travel" to (if (isBangla) "সফর" else "Travel"),
    "Prayer" to (if (isBangla) "নামাজ" else "Prayer"),
    "Daily" to (if (isBangla) "দৈনন্দিন" else "Daily")
  )

  val filteredDuas = IslamicDataProvider.duas.filter { dua ->
    val matchesCategory = if (selectedCategory == "All") true else dua.category == selectedCategory
    val matchesSearch = if (searchQuery.isBlank()) true else {
      dua.titleBn.contains(searchQuery, ignoreCase = true) ||
        dua.titleEn.contains(searchQuery, ignoreCase = true) ||
        dua.meaningBn.contains(searchQuery, ignoreCase = true) ||
        dua.arabic.contains(searchQuery)
    }
    matchesCategory && matchesSearch
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("dua_screen_content")
  ) {
    // Search Bar
    OutlinedTextField(
      value = searchQuery,
      onValueChange = { searchQuery = it },
      placeholder = {
        Text(
          text = if (isBangla) "দোয়া ও জিকির খুঁজুন..." else "Search Duas and Adhkar...",
          fontSize = 13.sp
        )
      },
      leadingIcon = {
        Icon(Icons.Default.Search, contentDescription = "Search", tint = IslamicEmerald)
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .testTag("dua_search_input"),
      shape = RoundedCornerShape(14.dp),
      colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = IslamicEmerald,
        unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant
      ),
      singleLine = true
    )

    // Category Tabs Row
    LazyRow(
      contentPadding = PaddingValues(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(categories) { (catKey, catLabel) ->
        val isSelected = selectedCategory == catKey
        Surface(
          shape = RoundedCornerShape(16.dp),
          color = if (isSelected) IslamicEmerald else MaterialTheme.colorScheme.surfaceVariant,
          modifier = Modifier.clickable { selectedCategory = catKey }
        ) {
          Text(
            text = catLabel,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Dua List
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 90.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      items(filteredDuas) { dua ->
        val isFavorite = favoriteDuas.contains(dua.id)
        DuaCardItem(
          dua = dua,
          isBangla = isBangla,
          isFavorite = isFavorite,
          onToggleFavorite = { viewModel.toggleFavoriteDua(dua.id) },
          onCopy = {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Dua", "${dua.arabic}\n\n${dua.pronunciationBn}\n\n${dua.meaningBn}\n(${dua.reference})")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, if (isBangla) "দোয়া কপি করা হয়েছে" else "Dua copied to clipboard", Toast.LENGTH_SHORT).show()
          },
          onShare = {
            val sendIntent = Intent().apply {
              action = Intent.ACTION_SEND
              putExtra(Intent.EXTRA_TEXT, "${dua.titleBn}\n\n${dua.arabic}\n\n${dua.pronunciationBn}\n\n${dua.meaningBn}\n[সূত্র: ${dua.reference} • নুরলাইফ অ্যাপ]")
              type = "text/plain"
            }
            context.startActivity(Intent.createChooser(sendIntent, "Share Dua"))
          }
        )
      }
    }
  }
}

@Composable
fun DuaCardItem(
  dua: DuaItem,
  isBangla: Boolean,
  isFavorite: Boolean,
  onToggleFavorite: () -> Unit,
  onCopy: () -> Unit,
  onShare: () -> Unit
) {
  IslamicCard(modifier = Modifier.fillMaxWidth()) {
    Column {
      // Header: Title & Reference
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = if (isBangla) dua.titleBn else dua.titleEn,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = IslamicEmerald
          )
          Text(
            text = dua.reference,
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

      // Arabic Calligraphy
      Text(
        text = dua.arabic,
        fontSize = 19.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.End,
        lineHeight = 28.sp,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(8.dp))

      // Pronunciation
      if (isBangla) {
        Text(
          text = "উচ্চারণ: ${dua.pronunciationBn}",
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          lineHeight = 17.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
      }

      // Meaning
      Text(
        text = "অর্থ: ${if (isBangla) dua.meaningBn else dua.meaningEn}",
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.onSurface,
        lineHeight = 19.sp
      )
    }
  }
}
