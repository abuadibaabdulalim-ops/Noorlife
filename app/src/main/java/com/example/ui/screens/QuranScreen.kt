package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.QuranSurahData
import com.example.model.Ayah
import com.example.model.Surah
import com.example.ui.components.IslamicCard
import com.example.ui.components.IslamicGeometricDivider
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicEmeraldDark
import com.example.ui.theme.IslamicEmeraldLight
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldDark
import com.example.ui.theme.IslamicGoldLight
import com.example.util.DateUtils
import com.example.viewmodel.NoorlifeViewModel

@Composable
fun QuranScreen(
  viewModel: NoorlifeViewModel,
  modifier: Modifier = Modifier
) {
  val isBangla by viewModel.isBangla.collectAsState()
  val bookmarkedSurahs by viewModel.bookmarkedSurahs.collectAsState()
  val lastReadSurahId by viewModel.lastReadSurah.collectAsState()

  var selectedSurah by remember { mutableStateOf<Surah?>(null) }
  var searchQuery by remember { mutableStateOf("") }
  var selectedTab by remember { mutableIntStateOf(0) } // 0=Surah, 1=Juz, 2=Bookmarks

  if (selectedSurah != null) {
    SurahDetailView(
      surah = selectedSurah!!,
      viewModel = viewModel,
      onBack = { selectedSurah = null }
    )
  } else {
    Column(
      modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .testTag("quran_screen_content")
    ) {
      // Search Bar
      OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        placeholder = {
          Text(
            text = if (isBangla) "সূরা খুঁজুন (নাম বা নম্বর)..." else "Search Surah (name or number)...",
            fontSize = 14.sp
          )
        },
        leadingIcon = {
          Icon(Icons.Default.Search, contentDescription = "Search", tint = IslamicEmerald)
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)
          .testTag("surah_search_input"),
        shape = RoundedCornerShape(14.dp),
        colors = OutlinedTextFieldDefaults.colors(
          focusedBorderColor = IslamicEmerald,
          unfocusedBorderColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        singleLine = true
      )

      // Tabs: All Surahs, Juz, Bookmarks
      TabRow(
        selectedTabIndex = selectedTab,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = IslamicEmerald
      ) {
        Tab(
          selected = selectedTab == 0,
          onClick = { selectedTab = 0 },
          text = { Text(if (isBangla) "সকল সূরা (১১৪)" else "All Surahs (114)", fontWeight = FontWeight.SemiBold) }
        )
        Tab(
          selected = selectedTab == 1,
          onClick = { selectedTab = 1 },
          text = { Text(if (isBangla) "পারা (৩০)" else "Juz (30)", fontWeight = FontWeight.SemiBold) }
        )
        Tab(
          selected = selectedTab == 2,
          onClick = { selectedTab = 2 },
          text = { Text(if (isBangla) "বুকমার্ক (${bookmarkedSurahs.size})" else "Bookmarks (${bookmarkedSurahs.size})", fontWeight = FontWeight.SemiBold) }
        )
      }

      // Last read resume banner
      val lastSurah = QuranSurahData.allSurahs.find { it.id == lastReadSurahId }
      if (lastSurah != null && searchQuery.isEmpty()) {
        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { selectedSurah = lastSurah },
          shape = RoundedCornerShape(12.dp),
          color = IslamicEmerald.copy(alpha = 0.12f)
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Column {
              Text(
                text = if (isBangla) "সর্বশেষ পঠিত সূরা" else "Last Read Surah",
                color = IslamicEmerald,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = if (isBangla) "${lastSurah.nameBangla} (${lastSurah.nameArabic})" else "${lastSurah.nameEnglish} (${lastSurah.nameArabic})",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
            }
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = IslamicEmerald
            ) {
              Text(
                text = if (isBangla) "পড়ুন" else "Continue",
                color = Color.White,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }
        }
      }

      // Filtered Surahs List
      val filteredSurahs = QuranSurahData.allSurahs.filter { surah ->
        val matchesSearch = if (searchQuery.isBlank()) true else {
          surah.nameBangla.contains(searchQuery, ignoreCase = true) ||
            surah.nameEnglish.contains(searchQuery, ignoreCase = true) ||
            surah.nameArabic.contains(searchQuery) ||
            surah.id.toString() == searchQuery.trim()
        }
        val matchesTab = when (selectedTab) {
          1 -> true // grouped in Juz
          2 -> bookmarkedSurahs.contains(surah.id)
          else -> true
        }
        matchesSearch && matchesTab
      }

      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 90.dp, top = 4.dp)
      ) {
        items(filteredSurahs) { surah ->
          SurahListItem(
            surah = surah,
            isBangla = isBangla,
            isBookmarked = bookmarkedSurahs.contains(surah.id),
            onToggleBookmark = { viewModel.toggleBookmarkSurah(surah.id) },
            onClick = {
              selectedSurah = surah
              viewModel.saveLastRead(surah.id, 1)
            }
          )
        }
      }
    }
  }
}

@Composable
fun SurahListItem(
  surah: Surah,
  isBangla: Boolean,
  isBookmarked: Boolean,
  onToggleBookmark: () -> Unit,
  onClick: () -> Unit
) {
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 4.dp)
      .clickable { onClick() }
      .testTag("surah_item_${surah.id}"),
    shape = RoundedCornerShape(12.dp),
    color = MaterialTheme.colorScheme.surface,
    tonalElevation = 1.dp
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        // Surah Number in Islamic star badge
        Box(
          modifier = Modifier
            .size(38.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(IslamicEmerald.copy(alpha = 0.1f)),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = if (isBangla) DateUtils.toBanglaDigits(surah.id.toString()) else surah.id.toString(),
            color = IslamicEmerald,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
          )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = if (isBangla) surah.nameBangla else surah.nameEnglish,
              color = MaterialTheme.colorScheme.onSurface,
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(6.dp))
            Surface(
              shape = RoundedCornerShape(4.dp),
              color = MaterialTheme.colorScheme.surfaceVariant
            ) {
              Text(
                text = surah.revelationType,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
              )
            }
          }

          Text(
            text = if (isBangla) "${surah.meaningBangla} • আয়াত: ${DateUtils.toBanglaDigits(surah.versesCount.toString())}"
            else "${surah.versesCount} Ayahs • Juz ${surah.juzNumber}",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp
          )
        }
      }

      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = surah.nameArabic,
          color = IslamicGoldDark,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = onToggleBookmark) {
          Icon(
            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
            contentDescription = "Bookmark",
            tint = if (isBookmarked) IslamicGoldDark else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurahDetailView(
  surah: Surah,
  viewModel: NoorlifeViewModel,
  onBack: () -> Unit
) {
  BackHandler { onBack() }
  val context = LocalContext.current
  val isBangla by viewModel.isBangla.collectAsState()
  val bookmarkedSurahs by viewModel.bookmarkedSurahs.collectAsState()
  val arabicFontSize by viewModel.arabicFontSize.collectAsState()
  val transFontSize by viewModel.translationFontSize.collectAsState()

  val isPlaying by viewModel.audioPlayer.isPlaying.collectAsState()
  val currentPlayingId by viewModel.audioPlayer.currentPlayingId.collectAsState()
  val isLoadingAudio by viewModel.audioPlayer.isLoading.collectAsState()

  var showFontSizeControls by remember { mutableStateOf(false) }

  val ayahs = QuranSurahData.getAyahsForSurah(surah.id)

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
  ) {
    // Detail Top Bar
    TopAppBar(
      title = {
        Column {
          Text(
            text = if (isBangla) surah.nameBangla else surah.nameEnglish,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = "${surah.nameArabic} • ${surah.revelationType} • ${surah.versesCount} ${if (isBangla) "আয়াত" else "Ayahs"}",
            fontSize = 11.sp,
            color = IslamicGoldLight
          )
        }
      },
      navigationIcon = {
        IconButton(onClick = onBack) {
          Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
        }
      },
      actions = {
        IconButton(onClick = { showFontSizeControls = !showFontSizeControls }) {
          Icon(Icons.Default.FormatSize, contentDescription = "Font size", tint = Color.White)
        }
        IconButton(onClick = { viewModel.toggleBookmarkSurah(surah.id) }) {
          Icon(
            imageVector = if (bookmarkedSurahs.contains(surah.id)) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
            contentDescription = "Bookmark",
            tint = IslamicGoldLight
          )
        }
      },
      colors = TopAppBarDefaults.topAppBarColors(
        containerColor = IslamicEmerald,
        titleContentColor = Color.White
      )
    )

    // Font size controls expandable
    AnimatedVisibility(visible = showFontSizeControls) {
      Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = if (isBangla) "আরবি হরফের আকার: ${arabicFontSize.toInt()} sp" else "Arabic Font Size: ${arabicFontSize.toInt()} sp",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
          )
          Slider(
            value = arabicFontSize,
            onValueChange = { viewModel.setFontSizes(it, transFontSize) },
            valueRange = 18f..38f,
            colors = SliderDefaults.colors(thumbColor = IslamicEmerald, activeTrackColor = IslamicEmerald)
          )

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = if (isBangla) "অনুবাদ হরফের আকার: ${transFontSize.toInt()} sp" else "Translation Font Size: ${transFontSize.toInt()} sp",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
          )
          Slider(
            value = transFontSize,
            onValueChange = { viewModel.setFontSizes(arabicFontSize, it) },
            valueRange = 12f..24f,
            colors = SliderDefaults.colors(thumbColor = IslamicEmerald, activeTrackColor = IslamicEmerald)
          )
        }
      }
    }

    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
    ) {
      // Bismillah Header (except At-Tawbah)
      if (surah.id != 9) {
        item {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
              color = IslamicEmerald,
              fontSize = 22.sp,
              fontWeight = FontWeight.Bold,
              textAlign = TextAlign.Center
            )
          }
          IslamicGeometricDivider()
          Spacer(modifier = Modifier.height(8.dp))
        }
      }

      items(ayahs) { ayah ->
        AyahCard(
          ayah = ayah,
          isBangla = isBangla,
          arabicFontSize = arabicFontSize,
          transFontSize = transFontSize,
          isPlaying = isPlaying && currentPlayingId == "ayah_${ayah.surahNumber}_${ayah.ayahNumber}",
          isLoading = isLoadingAudio && currentPlayingId == "ayah_${ayah.surahNumber}_${ayah.ayahNumber}",
          onPlayAudio = {
            if (ayah.audioUrl.isNotEmpty()) {
              viewModel.audioPlayer.playUrl("ayah_${ayah.surahNumber}_${ayah.ayahNumber}", ayah.audioUrl)
            } else {
              Toast.makeText(context, if (isBangla) "অডিও ডাউনলোডের জন্য প্রস্তুত" else "Audio streaming for verse", Toast.LENGTH_SHORT).show()
            }
          },
          onCopy = {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Ayah", "${ayah.arabicText}\n\n${ayah.banglaText}\n\n${ayah.englishText}")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, if (isBangla) "আয়াত কপি করা হয়েছে" else "Ayah copied to clipboard", Toast.LENGTH_SHORT).show()
          },
          onShare = {
            val sendIntent = Intent().apply {
              action = Intent.ACTION_SEND
              putExtra(Intent.EXTRA_TEXT, "${ayah.arabicText}\n\n${ayah.banglaText}\n\n${ayah.englishText}\n(সূরা ${surah.nameBangla}: আয়াত ${ayah.ayahNumber} • নুরলাইফ)")
              type = "text/plain"
            }
            context.startActivity(Intent.createChooser(sendIntent, "Share Ayah"))
          }
        )
        Spacer(modifier = Modifier.height(10.dp))
      }
    }
  }
}

@Composable
fun AyahCard(
  ayah: Ayah,
  isBangla: Boolean,
  arabicFontSize: Float,
  transFontSize: Float,
  isPlaying: Boolean,
  isLoading: Boolean,
  onPlayAudio: () -> Unit,
  onCopy: () -> Unit,
  onShare: () -> Unit
) {
  IslamicCard(modifier = Modifier.fillMaxWidth()) {
    Column {
      // Header: Ayah Number & action icons
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          shape = CircleShape,
          color = IslamicEmerald.copy(alpha = 0.1f)
        ) {
          Text(
            text = if (isBangla) DateUtils.toBanglaDigits("${ayah.surahNumber}:${ayah.ayahNumber}") else "${ayah.surahNumber}:${ayah.ayahNumber}",
            color = IslamicEmerald,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }

        Row {
          // Play Audio Button
          IconButton(onClick = onPlayAudio, modifier = Modifier.size(32.dp)) {
            if (isLoading) {
              CircularProgressIndicator(modifier = Modifier.size(16.dp), color = IslamicEmerald, strokeWidth = 2.dp)
            } else {
              Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = "Play Audio",
                tint = if (isPlaying) IslamicGoldDark else IslamicEmerald
              )
            }
          }

          IconButton(onClick = onCopy, modifier = Modifier.size(32.dp)) {
            Icon(
              imageVector = Icons.Default.ContentCopy,
              contentDescription = "Copy",
              tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }

          IconButton(onClick = onShare, modifier = Modifier.size(32.dp)) {
            Icon(
              imageVector = Icons.Default.Share,
              contentDescription = "Share",
              tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Arabic Uthmani Text
      Text(
        text = ayah.arabicText,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = arabicFontSize.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.End,
        lineHeight = (arabicFontSize * 1.6f).sp,
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Bangla Translation
      Text(
        text = ayah.banglaText,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = transFontSize.sp,
        lineHeight = (transFontSize * 1.4f).sp
      )

      Spacer(modifier = Modifier.height(4.dp))

      // English Translation
      Text(
        text = ayah.englishText,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontSize = (transFontSize - 2).sp,
        lineHeight = ((transFontSize - 2) * 1.4f).sp
      )
    }
  }
}
