package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun TasbihScreen(
  viewModel: NoorlifeViewModel,
  modifier: Modifier = Modifier
) {
  val isBangla by viewModel.isBangla.collectAsState()
  val count by viewModel.tasbihCount.collectAsState()
  val target by viewModel.tasbihTarget.collectAsState()
  val zikr by viewModel.tasbihZikr.collectAsState()
  val totalCount by viewModel.tasbihTotalCount.collectAsState()
  val vibrationEnabled by viewModel.tasbihVibration.collectAsState()

  var showResetDialog by remember { mutableStateOf(false) }
  var showCustomZikrDialog by remember { mutableStateOf(false) }
  var customZikrInput by remember { mutableStateOf("") }

  val presetZikrs = listOf(
    "سُبْحَانَ اللَّهِ" to (if (isBangla) "সুবহানাল্লাহ" else "SubhanAllah"),
    "الْحَمْدُ لِلَّهِ" to (if (isBangla) "আলহামদুলিল্লাহ" else "Alhamdulillah"),
    "اللَّهُ أَكْبَرُ" to (if (isBangla) "আল্লাহু আকবার" else "Allahu Akbar"),
    "أَسْتَغْفِرُ اللَّهَ" to (if (isBangla) "আস্তাগফিরুল্লাহ" else "Astaghfirullah"),
    "لَا إِلَٰهَ إِلَّا اللَّهُ" to (if (isBangla) "লা ইলাহা ইল্লাল্লাহ" else "La ilaha illallah"),
    "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ" to (if (isBangla) "সুবহানাল্লাহি ওয়া বিহামদিহি" else "Subhanallahi wa bihamdihi")
  )

  val presetTargets = listOf(33, 100, 1000, 0) // 0 = unlimited

  val progress = if (target > 0) (count % target).toFloat() / target else 0.5f
  val animatedProgress by animateFloatAsState(targetValue = progress, label = "tasbih_progress")

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .padding(bottom = 90.dp)
      .testTag("tasbih_screen_content"),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Top Bar Actions
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Total count badge
      Surface(
        shape = RoundedCornerShape(12.dp),
        color = IslamicEmerald.copy(alpha = 0.1f)
      ) {
        Text(
          text = if (isBangla) "মোট জিকির: ${DateUtils.toBanglaDigits(totalCount.toString())}" else "Total: $totalCount",
          color = IslamicEmerald,
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
        )
      }

      Row {
        // Vibration toggle
        IconButton(
          onClick = { viewModel.toggleTasbihVibration() },
          modifier = Modifier.testTag("tasbih_vibrate_toggle")
        ) {
          Icon(
            imageVector = Icons.Default.Vibration,
            contentDescription = "Toggle Vibration",
            tint = if (vibrationEnabled) IslamicEmerald else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
          )
        }

        // Reset button
        IconButton(
          onClick = { showResetDialog = true },
          modifier = Modifier.testTag("tasbih_reset_button")
        ) {
          Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Reset",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }

    // Preset Zikr Carousel
    LazyRow(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp),
      contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(presetZikrs) { (arabic, trans) ->
        val isSelected = zikr.contains(arabic)
        Surface(
          shape = RoundedCornerShape(16.dp),
          color = if (isSelected) IslamicEmerald else MaterialTheme.colorScheme.surfaceVariant,
          modifier = Modifier
            .clickable { viewModel.setTasbihZikr("$arabic ($trans)") }
            .testTag("preset_zikr_$arabic")
        ) {
          Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(
              text = arabic,
              color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = trans,
              color = if (isSelected) IslamicGoldLight else MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 11.sp
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Active Zikr Display Card
    IslamicCard(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp),
      onClick = { showCustomZikrDialog = true }
    ) {
      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = zikr,
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold,
          color = IslamicEmerald,
          textAlign = TextAlign.Center
        )
        Text(
          text = if (isBangla) "ট্যাপ করে নিজস্ব জিকির লিখুন" else "Tap to set custom Zikr",
          fontSize = 11.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Big Circular Digital Counter with Progress Gauge
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier
        .size(240.dp)
        .clickable(
          interactionSource = remember { MutableInteractionSource() },
          indication = null
        ) { viewModel.incrementTasbih() }
        .testTag("tasbih_counter_button")
    ) {
      // Progress Gauge Ring
      Canvas(modifier = Modifier.fillMaxSize()) {
        val strokeWidth = 14.dp.toPx()
        // Background track
        drawCircle(
          color = Color.LightGray.copy(alpha = 0.25f),
          style = Stroke(width = strokeWidth)
        )
        // Active arc
        if (target > 0) {
          drawArc(
            brush = Brush.sweepGradient(listOf(IslamicGold, IslamicEmerald, IslamicEmeraldLight)),
            startAngle = -90f,
            sweepAngle = 360f * animatedProgress,
            useCenter = false,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
          )
        }
      }

      // Circular Inner Tap Button
      Box(
        modifier = Modifier
          .size(190.dp)
          .clip(CircleShape)
          .background(
            Brush.radialGradient(
              listOf(IslamicEmerald, IslamicEmeraldDark)
            )
          )
          .shadow(8.dp, CircleShape),
        contentAlignment = Alignment.Center
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = if (isBangla) DateUtils.toBanglaDigits(count.toString()) else count.toString(),
            color = Color.White,
            fontSize = 54.sp,
            fontWeight = FontWeight.Black
          )

          Text(
            text = if (target > 0) {
              if (isBangla) "টার্গেট: ${DateUtils.toBanglaDigits(target.toString())}" else "Target: $target"
            } else {
              if (isBangla) "সীমাহীন" else "Unlimited"
            },
            color = IslamicGoldLight,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
          )

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = if (isBangla) "ট্যাপ করুন" else "TAP TO COUNT",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 11.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Target Selection Chips: 33, 100, 1000, ∞
    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = if (isBangla) "টার্গেট:" else "Target:",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )

      presetTargets.forEach { t ->
        val isSelected = target == t
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = if (isSelected) IslamicEmerald else MaterialTheme.colorScheme.surfaceVariant,
          modifier = Modifier
            .clickable { viewModel.setTasbihTarget(t) }
            .testTag("target_button_$t")
        ) {
          Text(
            text = if (t == 0) "∞" else if (isBangla) DateUtils.toBanglaDigits(t.toString()) else t.toString(),
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
          )
        }
      }
    }
  }

  // Reset Confirmation Dialog
  if (showResetDialog) {
    AlertDialog(
      onDismissRequest = { showResetDialog = false },
      title = { Text(if (isBangla) "গণনা রিসেট করবেন?" else "Reset Counter?") },
      text = { Text(if (isBangla) "বর্তমান তাসবিহ গণনা ০ তে ফিরিয়ে আনা হবে। তবে মোট জিকির সংরক্ষিত থাকবে।" else "Current count will be reset to 0. Total count remains saved.") },
      confirmButton = {
        Button(
          onClick = {
            viewModel.resetTasbih()
            showResetDialog = false
          },
          colors = ButtonDefaults.buttonColors(containerColor = IslamicEmerald)
        ) {
          Text(if (isBangla) "হ্যাঁ, রিসেট করুন" else "Yes, Reset")
        }
      },
      dismissButton = {
        TextButton(onClick = { showResetDialog = false }) {
          Text(if (isBangla) "বাতিল" else "Cancel")
        }
      }
    )
  }

  // Custom Zikr Dialog
  if (showCustomZikrDialog) {
    AlertDialog(
      onDismissRequest = { showCustomZikrDialog = false },
      title = { Text(if (isBangla) "কাস্টম জিকির যোগ করুন" else "Add Custom Zikr") },
      text = {
        OutlinedTextField(
          value = customZikrInput,
          onValueChange = { customZikrInput = it },
          placeholder = { Text(if (isBangla) "যেমন: লা হাওলা ওয়ালা কুওয়াতা..." else "e.g., La hawla wa la quwwata...") },
          modifier = Modifier.fillMaxWidth()
        )
      },
      confirmButton = {
        Button(
          onClick = {
            if (customZikrInput.isNotBlank()) {
              viewModel.setTasbihZikr(customZikrInput.trim())
            }
            showCustomZikrDialog = false
          },
          colors = ButtonDefaults.buttonColors(containerColor = IslamicEmerald)
        ) {
          Text(if (isBangla) "সংরক্ষণ" else "Save")
        }
      },
      dismissButton = {
        TextButton(onClick = { showCustomZikrDialog = false }) {
          Text(if (isBangla) "বাতিল" else "Cancel")
        }
      }
    )
  }
}
