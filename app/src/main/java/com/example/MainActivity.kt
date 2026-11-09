package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.TouchApp
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.IslamicDataProvider
import com.example.model.DistrictLocation
import com.example.ui.components.NoorlifeTopBar
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.AllahNamesScreen
import com.example.ui.screens.CalendarScreen
import com.example.ui.screens.DuaScreen
import com.example.ui.screens.HadithScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.IslamicKnowledgeScreen
import com.example.ui.screens.MoreMenuScreen
import com.example.ui.screens.NotesAndDeedsScreen
import com.example.ui.screens.PrayerScreen
import com.example.ui.screens.QiblaScreen
import com.example.ui.screens.QuranScreen
import com.example.ui.screens.RamadanScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.screens.TasbihScreen
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldLight
import com.example.ui.theme.NoorlifeTheme
import com.example.viewmodel.NoorlifeViewModel

enum class MainTab(val titleBn: String, val titleEn: String, val activeIcon: ImageVector, val inactiveIcon: ImageVector) {
  HOME("হোম", "Home", Icons.Filled.Home, Icons.Outlined.Home),
  PRAYER("নামাজ", "Prayer", Icons.Filled.AccessTime, Icons.Outlined.AccessTime),
  QURAN("কুরআন", "Quran", Icons.Filled.MenuBook, Icons.Outlined.MenuBook),
  TASBIH("তাসবিহ", "Tasbih", Icons.Filled.TouchApp, Icons.Outlined.TouchApp),
  MORE("আরও", "More", Icons.Filled.Widgets, Icons.Outlined.Widgets)
}

enum class SubScreen {
  ALLAH_NAMES,
  DUAS,
  HADITH,
  RAMADAN,
  QIBLA,
  CALENDAR,
  KNOWLEDGE,
  NOTES,
  SETTINGS,
  ABOUT
}

class MainActivity : ComponentActivity() {
  private val viewModel: NoorlifeViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val isDarkMode by viewModel.isDarkMode.collectAsState()

      NoorlifeTheme(darkTheme = isDarkMode) {
        NoorlifeMainApp(viewModel = viewModel)
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoorlifeMainApp(viewModel: NoorlifeViewModel) {
  var showSplash by remember { mutableStateOf(true) }
  var currentTab by remember { mutableStateOf(MainTab.HOME) }
  var activeSubScreen by remember { mutableStateOf<SubScreen?>(null) }
  var showLocationPicker by remember { mutableStateOf(false) }

  val isBangla by viewModel.isBangla.collectAsState()
  val isDarkMode by viewModel.isDarkMode.collectAsState()
  val currentDistrict by viewModel.currentDistrict.collectAsState()

  val locationSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

  // Handle system back button gracefully
  BackHandler(enabled = !showSplash) {
    if (activeSubScreen != null) {
      activeSubScreen = null
    } else if (currentTab != MainTab.HOME) {
      currentTab = MainTab.HOME
    }
  }

  if (showSplash) {
    SplashScreen(
      isBangla = isBangla,
      onSplashFinished = { showSplash = false }
    )
  } else {
    Scaffold(
      modifier = Modifier.fillMaxSize(),
      topBar = {
        val title = when (activeSubScreen) {
          SubScreen.ALLAH_NAMES -> if (isBangla) "আল্লাহর ৯৯টি নাম" else "99 Names of Allah"
          SubScreen.DUAS -> if (isBangla) "দোয়া ও জিকির" else "Duas & Adhkar"
          SubScreen.HADITH -> if (isBangla) "আল-হাদিস সংগ্রহ" else "Hadith Collection"
          SubScreen.RAMADAN -> if (isBangla) "পবিত্র মাহে রমজান" else "Holy Ramadan"
          SubScreen.QIBLA -> if (isBangla) "কিবলা কম্পাস" else "Qibla Compass"
          SubScreen.CALENDAR -> if (isBangla) "ইসলামিক ক্যালেন্ডার" else "Islamic Calendar"
          SubScreen.KNOWLEDGE -> if (isBangla) "ইসলামিক জ্ঞান ও গাইড" else "Islamic Knowledge"
          SubScreen.NOTES -> if (isBangla) "ব্যক্তিগত নোট" else "Personal Notes"
          SubScreen.SETTINGS -> if (isBangla) "সেটিংস ও নিয়ম" else "Settings"
          SubScreen.ABOUT -> if (isBangla) "নুরলাইফ সম্পর্কে" else "About Noorlife"
          null -> when (currentTab) {
            MainTab.HOME -> "Noorlife"
            MainTab.PRAYER -> if (isBangla) "নামাজের সময়সূচি" else "Prayer Times"
            MainTab.QURAN -> if (isBangla) "আল-কুরআনুল কারীম" else "Holy Quran"
            MainTab.TASBIH -> if (isBangla) "ডিজিটাল তাসবিহ" else "Digital Tasbih"
            MainTab.MORE -> if (isBangla) "আরও ফিচারসমূহ" else "More Features"
          }
        }

        val subtitle = if (currentTab == MainTab.HOME && activeSubScreen == null) {
          if (isBangla) "আপনার সার্বিক ইসলামিক সঙ্গী" else "Your Complete Islamic Companion"
        } else null

        NoorlifeTopBar(
          title = title,
          subtitle = subtitle,
          isBangla = isBangla,
          isDarkMode = isDarkMode,
          onToggleLanguage = { viewModel.toggleLanguage() },
          onToggleTheme = { viewModel.toggleDarkMode() },
          onLocationClick = { showLocationPicker = true },
          currentLocationName = if (isBangla) currentDistrict.nameBn else currentDistrict.nameEn
        )
      },
      bottomBar = {
        NavigationBar(
          containerColor = MaterialTheme.colorScheme.surface,
          tonalElevation = 8.dp,
          modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
        ) {
          MainTab.values().forEach { tab ->
            val isSelected = activeSubScreen == null && currentTab == tab
            NavigationBarItem(
              selected = isSelected,
              onClick = {
                activeSubScreen = null
                currentTab = tab
              },
              icon = {
                Icon(
                  imageVector = if (isSelected) tab.activeIcon else tab.inactiveIcon,
                  contentDescription = tab.titleEn
                )
              },
              label = {
                Text(
                  text = if (isBangla) tab.titleBn else tab.titleEn,
                  fontSize = 11.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
              },
              colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                selectedTextColor = IslamicEmerald,
                indicatorColor = IslamicEmerald,
                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
              ),
              modifier = Modifier.testTag("nav_item_${tab.name.lowercase()}")
            )
          }
        }
      }
    ) { innerPadding ->
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding)
      ) {
        Crossfade(
          targetState = activeSubScreen ?: currentTab,
          label = "screen_crossfade"
        ) { screen ->
          when (screen) {
            // Main Tabs
            MainTab.HOME -> HomeScreen(
              viewModel = viewModel,
              onNavigateToPrayer = { currentTab = MainTab.PRAYER },
              onNavigateToQuran = { currentTab = MainTab.QURAN },
              onNavigateToTasbih = { currentTab = MainTab.TASBIH },
              onNavigateToRamadan = { activeSubScreen = SubScreen.RAMADAN },
              onNavigateToQibla = { activeSubScreen = SubScreen.QIBLA },
              onNavigateToAllahNames = { activeSubScreen = SubScreen.ALLAH_NAMES },
              onNavigateToDuas = { activeSubScreen = SubScreen.DUAS },
              onNavigateToHadith = { activeSubScreen = SubScreen.HADITH },
              onNavigateToCalendar = { activeSubScreen = SubScreen.CALENDAR },
              onNavigateToKnowledge = { activeSubScreen = SubScreen.KNOWLEDGE }
            )
            MainTab.PRAYER -> PrayerScreen(viewModel = viewModel)
            MainTab.QURAN -> QuranScreen(viewModel = viewModel)
            MainTab.TASBIH -> TasbihScreen(viewModel = viewModel)
            MainTab.MORE -> MoreMenuScreen(
              viewModel = viewModel,
              onNavigateToAllahNames = { activeSubScreen = SubScreen.ALLAH_NAMES },
              onNavigateToDuas = { activeSubScreen = SubScreen.DUAS },
              onNavigateToHadith = { activeSubScreen = SubScreen.HADITH },
              onNavigateToRamadan = { activeSubScreen = SubScreen.RAMADAN },
              onNavigateToQibla = { activeSubScreen = SubScreen.QIBLA },
              onNavigateToCalendar = { activeSubScreen = SubScreen.CALENDAR },
              onNavigateToKnowledge = { activeSubScreen = SubScreen.KNOWLEDGE },
              onNavigateToNotes = { activeSubScreen = SubScreen.NOTES },
              onNavigateToSettings = { activeSubScreen = SubScreen.SETTINGS },
              onNavigateToAbout = { activeSubScreen = SubScreen.ABOUT }
            )

            // Sub Screens
            SubScreen.ALLAH_NAMES -> AllahNamesScreen(viewModel = viewModel)
            SubScreen.DUAS -> DuaScreen(viewModel = viewModel)
            SubScreen.HADITH -> HadithScreen(viewModel = viewModel)
            SubScreen.RAMADAN -> RamadanScreen(viewModel = viewModel)
            SubScreen.QIBLA -> QiblaScreen(viewModel = viewModel)
            SubScreen.CALENDAR -> CalendarScreen(viewModel = viewModel)
            SubScreen.KNOWLEDGE -> IslamicKnowledgeScreen(viewModel = viewModel)
            SubScreen.NOTES -> NotesAndDeedsScreen(viewModel = viewModel, onBack = { activeSubScreen = null })
            SubScreen.SETTINGS -> SettingsScreen(
              viewModel = viewModel,
              onNavigateToAbout = { activeSubScreen = SubScreen.ABOUT },
              onNavigateToNotes = { activeSubScreen = SubScreen.NOTES },
              onOpenLocationSheet = { showLocationPicker = true }
            )
            SubScreen.ABOUT -> AboutScreen(viewModel = viewModel)
            else -> {}
          }
        }
      }
    }

    // Global District Selection Modal
    if (showLocationPicker) {
      DistrictSelectionModal(
        isBangla = isBangla,
        currentDistrict = currentDistrict,
        sheetState = locationSheetState,
        onSelectDistrict = { dist ->
          viewModel.selectDistrict(dist)
          showLocationPicker = false
        },
        onDismiss = { showLocationPicker = false }
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DistrictSelectionModal(
  isBangla: Boolean,
  currentDistrict: DistrictLocation,
  sheetState: SheetState,
  onSelectDistrict: (DistrictLocation) -> Unit,
  onDismiss: () -> Unit
) {
  ModalBottomSheet(
    onDismissRequest = onDismiss,
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
              .clickable { onSelectDistrict(district) }
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
