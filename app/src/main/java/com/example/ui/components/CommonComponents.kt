package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.DistrictLocation
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicEmeraldLight
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldDark
import com.example.ui.theme.IslamicGoldLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoorlifeTopBar(
  title: String,
  subtitle: String? = null,
  isBangla: Boolean,
  isDarkMode: Boolean,
  onToggleLanguage: () -> Unit,
  onToggleTheme: () -> Unit,
  onLocationClick: (() -> Unit)? = null,
  currentLocationName: String? = null
) {
  Surface(
    color = MaterialTheme.colorScheme.primaryContainer,
    tonalElevation = 4.dp
  ) {
    TopAppBar(
      title = {
        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(IslamicGold),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "ن",
                color = IslamicEmerald,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
              )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = title,
              color = Color.White,
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold
            )
          }
          if (subtitle != null) {
            Text(
              text = subtitle,
              color = IslamicGoldLight,
              fontSize = 12.sp
            )
          }
        }
      },
      actions = {
        if (onLocationClick != null && currentLocationName != null) {
          Surface(
            modifier = Modifier
              .padding(end = 4.dp)
              .clip(RoundedCornerShape(16.dp))
              .clickable { onLocationClick() }
              .testTag("location_picker_button"),
            color = IslamicEmerald.copy(alpha = 0.5f)
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = IslamicGold,
                modifier = Modifier.size(14.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = currentLocationName,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
              )
            }
          }
        }

        IconButton(
          onClick = onToggleLanguage,
          modifier = Modifier.testTag("language_toggle_button")
        ) {
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(CircleShape)
              .background(IslamicEmerald.copy(alpha = 0.6f)),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = if (isBangla) "বাং" else "EN",
              color = IslamicGoldLight,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }

        IconButton(
          onClick = onToggleTheme,
          modifier = Modifier.testTag("theme_toggle_button")
        ) {
          Icon(
            imageVector = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
            contentDescription = "Toggle theme",
            tint = IslamicGoldLight
          )
        }
      },
      colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Transparent
      )
    )
  }
}

@Composable
fun IslamicCard(
  modifier: Modifier = Modifier,
  onClick: (() -> Unit)? = null,
  gradientBackground: Boolean = false,
  content: @Composable () -> Unit
) {
  val cardModifier = if (onClick != null) {
    modifier
      .clip(RoundedCornerShape(16.dp))
      .clickable { onClick() }
  } else {
    modifier
  }

  Card(
    modifier = cardModifier,
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (gradientBackground) Color.Transparent else MaterialTheme.colorScheme.surface
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    if (gradientBackground) {
      Box(
        modifier = Modifier
          .background(
            Brush.linearGradient(
              listOf(IslamicEmerald, IslamicEmeraldLight)
            )
          )
          .padding(16.dp)
      ) {
        content()
      }
    } else {
      Box(modifier = Modifier.padding(16.dp)) {
        content()
      }
    }
  }
}

@Composable
fun SectionHeader(
  title: String,
  actionText: String? = null,
  onActionClick: (() -> Unit)? = null,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Box(
        modifier = Modifier
          .size(6.dp, 18.dp)
          .clip(RoundedCornerShape(3.dp))
          .background(IslamicGold)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = title,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
      )
    }

    if (actionText != null && onActionClick != null) {
      Text(
        text = actionText,
        color = IslamicEmeraldLight,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
          .clickable { onActionClick() }
          .padding(4.dp)
      )
    }
  }
}

@Composable
fun IslamicGeometricDivider(modifier: Modifier = Modifier) {
  Canvas(
    modifier = modifier
      .fillMaxWidth()
      .height(16.dp)
      .padding(horizontal = 24.dp)
  ) {
    val midY = size.height / 2
    val centerX = size.width / 2

    // Left line
    drawLine(
      color = IslamicGold.copy(alpha = 0.4f),
      start = Offset(0f, midY),
      end = Offset(centerX - 16f, midY),
      strokeWidth = 1.5f
    )

    // Center 8-pointed star / diamond
    val path = Path().apply {
      moveTo(centerX, midY - 6f)
      lineTo(centerX + 6f, midY)
      lineTo(centerX, midY + 6f)
      lineTo(centerX - 6f, midY)
      close()
    }
    drawPath(path, color = IslamicGold)

    // Right line
    drawLine(
      color = IslamicGold.copy(alpha = 0.4f),
      start = Offset(centerX + 16f, midY),
      end = Offset(size.width, midY),
      strokeWidth = 1.5f
    )
  }
}
