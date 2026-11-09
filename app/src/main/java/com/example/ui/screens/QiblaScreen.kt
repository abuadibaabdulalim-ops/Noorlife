package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Navigation
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.IslamicCard
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicEmeraldDark
import com.example.ui.theme.IslamicEmeraldLight
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldDark
import com.example.ui.theme.IslamicGoldLight
import com.example.util.DateUtils
import com.example.util.QiblaSensorManager
import com.example.viewmodel.NoorlifeViewModel
import kotlin.math.abs

@Composable
fun QiblaScreen(
  viewModel: NoorlifeViewModel,
  modifier: Modifier = Modifier
) {
  val isBangla by viewModel.isBangla.collectAsState()
  val currentDistrict by viewModel.currentDistrict.collectAsState()
  val heading by viewModel.qiblaSensor.heading.collectAsState()
  val hasSensors by viewModel.qiblaSensor.hasSensors.collectAsState()

  val qiblaAngle = rememberQiblaAngle(currentDistrict.latitude, currentDistrict.longitude)
  val distanceKm = rememberDistanceKm(currentDistrict.latitude, currentDistrict.longitude)

  // Relative rotation angle: when phone points to Qibla, needle points straight UP (0 deg)
  val relativeAngle = (qiblaAngle - heading + 360f) % 360f
  val animatedRelativeAngle by animateFloatAsState(
    targetValue = relativeAngle,
    animationSpec = spring(stiffness = 150f),
    label = "qibla_rotation"
  )

  val isFacingQibla = abs(relativeAngle) < 4f || abs(relativeAngle - 360f) < 4f

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .padding(bottom = 90.dp)
      .testTag("qibla_screen_content"),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Location and Info Header
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
        .background(
          Brush.verticalGradient(listOf(IslamicEmerald, IslamicEmeraldDark))
        )
        .padding(20.dp)
    ) {
      Column {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = if (isBangla) "কিবলা কম্পাস" else "Qibla Direction",
              color = Color.White,
              fontSize = 22.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = if (isBangla) "মক্কা মুকাররমার পবিত্র কাবা শরিফ" else "Towards Holy Kaaba in Makkah",
              color = IslamicGoldLight,
              fontSize = 12.sp
            )
          }

          Surface(
            shape = RoundedCornerShape(12.dp),
            color = IslamicGold
          ) {
            Text(
              text = if (isBangla) "${DateUtils.toBanglaDigits(qiblaAngle.toInt().toString())}°" else "${qiblaAngle.toInt()}°",
              color = IslamicEmeraldDark,
              fontSize = 15.sp,
              fontWeight = FontWeight.Black,
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Location & Distance bar
        Surface(
          shape = RoundedCornerShape(12.dp),
          color = Color.White.copy(alpha = 0.12f),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = IslamicGold,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = if (isBangla) currentDistrict.nameBn else currentDistrict.nameEn,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
              )
            }

            Text(
              text = if (isBangla) "দূরত্ব: ${DateUtils.toBanglaDigits(distanceKm.toString())} কিমি" else "Distance: $distanceKm km",
              color = IslamicGoldLight,
              fontSize = 12.sp,
              fontWeight = FontWeight.Medium
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Facing Qibla Status indicator
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = if (isFacingQibla) IslamicEmerald.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant,
      modifier = Modifier.padding(horizontal = 24.dp)
    ) {
      Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(10.dp)
            .clip(CircleShape)
            .background(if (isFacingQibla) IslamicEmerald else Color.Gray)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = if (isFacingQibla) {
            if (isBangla) "আপনি কিবলার সঠিক দিকে মুখ করে আছেন!" else "You are perfectly facing the Qibla!"
          } else {
            if (isBangla) "ফোনের তীরচিহ্ন কাবার দিকে ঘোরান" else "Rotate phone until needle aligns with Kaaba"
          },
          fontSize = 13.sp,
          fontWeight = FontWeight.SemiBold,
          color = if (isFacingQibla) IslamicEmerald else MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Big Compass Dial
    Box(
      modifier = Modifier
        .size(280.dp)
        .testTag("qibla_compass_dial"),
      contentAlignment = Alignment.Center
    ) {
      // Outer Compass Ring with Degree Ticks
      Canvas(modifier = Modifier.fillMaxSize()) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.width / 2 - 8.dp.toPx()

        // Dial Background Circle
        drawCircle(
          color = Color.White,
          radius = radius,
          center = center
        )

        // Outer Border
        drawCircle(
          brush = Brush.sweepGradient(listOf(IslamicGold, IslamicEmerald, IslamicGold)),
          radius = radius,
          center = center,
          style = Stroke(width = 4.dp.toPx())
        )

        // Cardinal Ticks
        for (deg in 0 until 360 step 30) {
          rotate(deg.toFloat(), pivot = center) {
            val tickLength = if (deg % 90 == 0) 14.dp.toPx() else 8.dp.toPx()
            val stroke = if (deg % 90 == 0) 3.dp.toPx() else 1.5.dp.toPx()
            drawLine(
              color = if (deg == 0) Color.Red else Color.Gray.copy(alpha = 0.5f),
              start = Offset(center.x, center.y - radius),
              end = Offset(center.x, center.y - radius + tickLength),
              strokeWidth = stroke
            )
          }
        }
      }

      // Compass Needle Layer (Rotates to point to Kaaba)
      Box(
        modifier = Modifier
          .fillMaxSize()
          .rotate(animatedRelativeAngle),
        contentAlignment = Alignment.Center
      ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
          val center = Offset(size.width / 2, size.height / 2)
          val needleLen = size.width / 2 - 36.dp.toPx()

          // Kaaba pointer (Top golden/emerald needle)
          val topNeedle = Path().apply {
            moveTo(center.x, center.y - needleLen)
            lineTo(center.x + 12.dp.toPx(), center.y - 10.dp.toPx())
            lineTo(center.x, center.y)
            lineTo(center.x - 12.dp.toPx(), center.y - 10.dp.toPx())
            close()
          }
          drawPath(topNeedle, brush = Brush.verticalGradient(listOf(IslamicGold, IslamicGoldDark)))

          // Opposite pointer (Bottom subtle needle)
          val bottomNeedle = Path().apply {
            moveTo(center.x, center.y + needleLen)
            lineTo(center.x + 8.dp.toPx(), center.y + 10.dp.toPx())
            lineTo(center.x, center.y)
            lineTo(center.x - 8.dp.toPx(), center.y + 10.dp.toPx())
            close()
          }
          drawPath(bottomNeedle, color = Color.Gray.copy(alpha = 0.35f))
        }

        // Center Kaaba Cube Emblem
        Box(
          modifier = Modifier
            .size(54.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(IslamicEmeraldDark)
            .shadow(4.dp),
          contentAlignment = Alignment.Center
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
              modifier = Modifier
                .width(28.dp)
                .height(4.dp)
                .background(IslamicGold)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = "كعبة",
              color = Color.White,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Heading telemetry values
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 32.dp),
      horizontalArrangement = Arrangement.SpaceAround
    ) {
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
          text = if (isBangla) "কিবলা কোণ" else "Qibla Bearing",
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
          text = if (isBangla) "${DateUtils.toBanglaDigits(qiblaAngle.toInt().toString())}°" else "${qiblaAngle.toInt()}°",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = IslamicEmerald
        )
      }

      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
          text = if (isBangla) "ফোনের দিক" else "Current Heading",
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
          text = if (isBangla) "${DateUtils.toBanglaDigits(heading.toInt().toString())}°" else "${heading.toInt()}°",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Calibration note
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp),
      shape = RoundedCornerShape(12.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
      Row(
        modifier = Modifier.padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.Info,
          contentDescription = "Calibration Tip",
          tint = IslamicEmerald,
          modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
          text = if (isBangla) "কম্পাস সঠিক রাখতে ফোনকে ধাতব বস্তু থেকে দূরে রাখুন এবং প্রয়োজনমতো চিত্র-৮ (figure-8) আকারে বাতাসে ঘুরিয়ে ক্যালিব্রেট করুন।"
          else "For accuracy, keep phone away from metal objects and move in a figure-8 motion to calibrate sensors.",
          fontSize = 11.sp,
          lineHeight = 16.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }
  }
}

@Composable
fun rememberQiblaAngle(lat: Double, lng: Double): Float {
  return androidx.compose.runtime.remember(lat, lng) {
    QiblaSensorManager.calculateQiblaBearing(lat, lng)
  }
}

@Composable
fun rememberDistanceKm(lat: Double, lng: Double): Int {
  return androidx.compose.runtime.remember(lat, lng) {
    QiblaSensorManager.calculateDistanceKm(lat, lng)
  }
}
