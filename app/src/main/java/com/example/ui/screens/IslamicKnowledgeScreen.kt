package com.example.ui.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.IslamicKnowledgeData
import com.example.model.IslamicGuideItem
import com.example.ui.components.IslamicCard
import com.example.ui.components.IslamicGeometricDivider
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldDark
import com.example.viewmodel.NoorlifeViewModel

@Composable
fun IslamicKnowledgeScreen(
  viewModel: NoorlifeViewModel,
  modifier: Modifier = Modifier
) {
  val isBangla by viewModel.isBangla.collectAsState()

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("islamic_knowledge_screen_content"),
    contentPadding = PaddingValues(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 90.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    items(IslamicKnowledgeData.guides) { guide ->
      ExpandableGuideCard(guide = guide, isBangla = isBangla)
    }
  }
}

@Composable
fun ExpandableGuideCard(
  guide: IslamicGuideItem,
  isBangla: Boolean
) {
  var isExpanded by remember { mutableStateOf(false) }

  IslamicCard(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { isExpanded = !isExpanded }
  ) {
    Column {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          modifier = Modifier.weight(1f),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = IslamicEmerald.copy(alpha = 0.12f),
            modifier = Modifier.size(40.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = Icons.Default.MenuBook,
                contentDescription = null,
                tint = IslamicEmerald,
                modifier = Modifier.size(20.dp)
              )
            }
          }

          Spacer(modifier = Modifier.width(12.dp))

          Column {
            Text(
              text = if (isBangla) guide.titleBn else guide.titleEn,
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = if (isBangla) guide.summaryBn else guide.summaryEn,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }

        Icon(
          imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
          contentDescription = "Expand",
          tint = IslamicEmerald
        )
      }

      AnimatedVisibility(visible = isExpanded) {
        Column(modifier = Modifier.padding(top = 16.dp)) {
          IslamicGeometricDivider()
          Spacer(modifier = Modifier.height(12.dp))

          guide.sections.forEach { section ->
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
              Text(
                text = if (isBangla) section.headingBn else section.headingEn,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = IslamicEmerald
              )

              if (!section.arabic.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                  text = section.arabic,
                  fontSize = 17.sp,
                  fontWeight = FontWeight.Medium,
                  textAlign = TextAlign.End,
                  color = MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier.fillMaxWidth()
                )
              }

              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = if (isBangla) section.contentBn else section.contentEn,
                fontSize = 13.sp,
                lineHeight = 19.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
            }
          }
        }
      }
    }
  }
}
