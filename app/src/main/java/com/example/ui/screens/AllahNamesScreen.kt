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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.IslamicDataProvider
import com.example.model.AllahName
import com.example.ui.components.IslamicCard
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldDark
import com.example.ui.theme.IslamicGoldLight
import com.example.util.DateUtils
import com.example.viewmodel.NoorlifeViewModel

@Composable
fun AllahNamesScreen(
  viewModel: NoorlifeViewModel,
  modifier: Modifier = Modifier
) {
  val isBangla by viewModel.isBangla.collectAsState()
  val favoriteNames by viewModel.favoriteNames.collectAsState()
  var searchQuery by remember { mutableStateOf("") }
  var showOnlyFavorites by remember { mutableStateOf(false) }

  val filteredNames = IslamicDataProvider.allahNames.filter { name ->
    val matchesSearch = if (searchQuery.isBlank()) true else {
      name.arabic.contains(searchQuery) ||
        name.transliterationBn.contains(searchQuery, ignoreCase = true) ||
        name.meaningBn.contains(searchQuery, ignoreCase = true) ||
        name.transliterationEn.contains(searchQuery, ignoreCase = true) ||
        name.meaningEn.contains(searchQuery, ignoreCase = true) ||
        name.number.toString() == searchQuery.trim()
    }
    val matchesFav = if (showOnlyFavorites) favoriteNames.contains(name.number) else true
    matchesSearch && matchesFav
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("allah_names_screen_content")
  ) {
    // Search Bar & Favorites Filter
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        placeholder = {
          Text(
            text = if (isBangla) "আল্লাহর নাম খুঁজুন..." else "Search Allah's 99 Names...",
            fontSize = 13.sp
          )
        },
        leadingIcon = {
          Icon(Icons.Default.Search, contentDescription = "Search", tint = IslamicEmerald)
        },
        modifier = Modifier
          .weight(1f)
          .testTag("allah_names_search_input"),
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = IslamicEmerald,
          unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        singleLine = true
      )

      Spacer(modifier = Modifier.width(8.dp))

      Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (showOnlyFavorites) IslamicEmerald else MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
          .clip(RoundedCornerShape(12.dp))
          .clickable { showOnlyFavorites = !showOnlyFavorites }
          .padding(4.dp)
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = if (showOnlyFavorites) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Favorites",
            tint = if (showOnlyFavorites) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }

    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 90.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      items(filteredNames) { name ->
        AllahNameCard(
          name = name,
          isBangla = isBangla,
          isFavorite = favoriteNames.contains(name.number),
          onToggleFavorite = { viewModel.toggleFavoriteName(name.number) }
        )
      }
    }
  }
}

@Composable
fun AllahNameCard(
  name: AllahName,
  isBangla: Boolean,
  isFavorite: Boolean,
  onToggleFavorite: () -> Unit
) {
  IslamicCard(modifier = Modifier.fillMaxWidth()) {
    Column {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Number badge
        Surface(
          shape = CircleShape,
          color = IslamicEmerald.copy(alpha = 0.1f)
        ) {
          Text(
            text = if (isBangla) DateUtils.toBanglaDigits(name.number.toString()) else name.number.toString(),
            color = IslamicEmerald,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }

        // Calligraphy Arabic
        Text(
          text = name.arabic,
          color = IslamicGoldDark,
          fontSize = 24.sp,
          fontWeight = FontWeight.Bold,
          textAlign = TextAlign.End
        )

        // Favorite Button
        IconButton(onClick = onToggleFavorite, modifier = Modifier.size(32.dp)) {
          Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Transliteration & Meaning
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = if (isBangla) name.transliterationBn else name.transliterationEn,
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )

        Text(
          text = if (isBangla) name.meaningBn else name.meaningEn,
          fontSize = 14.sp,
          fontWeight = FontWeight.SemiBold,
          color = IslamicEmerald
        )
      }

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = if (isBangla) name.explanationBn else name.meaningEn,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        lineHeight = 17.sp
      )
    }
  }
}
