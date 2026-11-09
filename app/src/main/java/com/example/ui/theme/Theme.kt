package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
  primary = IslamicGold,
  onPrimary = IslamicEmeraldDark,
  primaryContainer = IslamicEmerald,
  onPrimaryContainer = IslamicGoldLight,
  secondary = IslamicEmeraldLight,
  onSecondary = Color.White,
  secondaryContainer = DarkSurfaceVariant,
  onSecondaryContainer = IslamicGoldLight,
  tertiary = IslamicGoldLight,
  onTertiary = IslamicEmeraldDark,
  background = DarkBackground,
  onBackground = DarkOnBackground,
  surface = DarkSurface,
  onSurface = DarkOnSurface,
  surfaceVariant = DarkSurfaceVariant,
  onSurfaceVariant = DarkOnSurfaceVariant,
  error = ForbiddenRed
)

private val LightColorScheme = lightColorScheme(
  primary = IslamicEmerald,
  onPrimary = Color.White,
  primaryContainer = IslamicEmeraldLight,
  onPrimaryContainer = Color.White,
  secondary = IslamicGoldDark,
  onSecondary = Color.White,
  secondaryContainer = LightSurfaceVariant,
  onSecondaryContainer = IslamicEmeraldDark,
  tertiary = IslamicGold,
  onTertiary = IslamicEmeraldDark,
  background = LightBackground,
  onBackground = LightOnBackground,
  surface = LightSurface,
  onSurface = LightOnSurface,
  surfaceVariant = LightSurfaceVariant,
  onSurfaceVariant = LightOnSurfaceVariant,
  error = ForbiddenRed
)

@Composable
fun NoorlifeTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
