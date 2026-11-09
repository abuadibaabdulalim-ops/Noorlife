package com.example.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.IslamicGeometricDivider
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicEmeraldDark
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldLight
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
  isBangla: Boolean,
  onSplashFinished: () -> Unit
) {
  val scale = remember { Animatable(0.7f) }
  val alpha = remember { Animatable(0f) }

  LaunchedEffect(Unit) {
    alpha.animateTo(
      targetValue = 1f,
      animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )
    scale.animateTo(
      targetValue = 1f,
      animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
    )
    delay(1600)
    onSplashFinished()
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(
        Brush.verticalGradient(
          listOf(IslamicEmerald, IslamicEmeraldDark)
        )
      )
      .clickable { onSplashFinished() }
      .testTag("splash_screen_container"),
    contentAlignment = Alignment.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
      modifier = Modifier
        .padding(32.dp)
        .alpha(alpha.value)
        .scale(scale.value)
    ) {
      // Islamic Logo
      Box(
        modifier = Modifier
          .size(140.dp)
          .clip(CircleShape)
          .background(IslamicEmeraldDark)
          .padding(4.dp),
        contentAlignment = Alignment.Center
      ) {
        Image(
          painter = painterResource(id = R.drawable.ic_noorlife_logo),
          contentDescription = "Noorlife Islamic Logo",
          modifier = Modifier
            .fillMaxSize()
            .clip(CircleShape)
        )
      }

      Spacer(modifier = Modifier.height(24.dp))

      // Arabic Calligraphy Bismillah
      Text(
        text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
        color = IslamicGoldLight,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(12.dp))

      // App Title
      Text(
        text = "Noorlife",
        color = Color.White,
        fontSize = 38.sp,
        fontWeight = FontWeight.ExtraBold,
        letterSpacing = 1.sp
      )

      // Tagline
      Text(
        text = if (isBangla) "আপনার সার্বিক ইসলামিক সঙ্গী" else "Your Complete Islamic Companion",
        color = IslamicGold,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(16.dp))

      IslamicGeometricDivider()

      Spacer(modifier = Modifier.height(48.dp))

      // Creator Credit
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(Color.White.copy(alpha = 0.08f))
          .padding(horizontal = 16.dp, vertical = 8.dp)
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = if (isBangla) "পরিকল্পনা ও রূপায়ণে" else "CREATED BY",
            color = IslamicGoldLight.copy(alpha = 0.8f),
            fontSize = 11.sp,
            letterSpacing = 1.2.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "Junaid Al Arib Ansari",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
          )
        }
      }
    }
  }
}
