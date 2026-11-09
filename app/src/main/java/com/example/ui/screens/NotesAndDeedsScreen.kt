package com.example.ui.screens

import android.content.Intent
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.IslamicNote
import com.example.ui.components.IslamicCard
import com.example.ui.theme.IslamicEmerald
import com.example.ui.theme.IslamicGold
import com.example.ui.theme.IslamicGoldLight
import com.example.viewmodel.NoorlifeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesAndDeedsScreen(
  viewModel: NoorlifeViewModel,
  onBack: () -> Unit,
  modifier: Modifier = Modifier
) {
  BackHandler { onBack() }
  val context = LocalContext.current
  val isBangla by viewModel.isBangla.collectAsState()
  val notes by viewModel.notes.collectAsState()

  var showAddDialog by remember { mutableStateOf(false) }
  var noteTitle by remember { mutableStateOf("") }
  var noteContent by remember { mutableStateOf("") }

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = if (isBangla) "ব্যক্তিগত ইসলামিক নোট" else "Personal Islamic Notes",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
          )
        },
        navigationIcon = {
          IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = IslamicEmerald,
          titleContentColor = Color.White
        )
      )
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = { showAddDialog = true },
        containerColor = IslamicEmerald,
        contentColor = Color.White,
        modifier = Modifier.testTag("add_note_fab")
      ) {
        Icon(Icons.Default.Add, contentDescription = "Add Note")
      }
    }
  ) { innerPadding ->
    LazyColumn(
      modifier = modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(MaterialTheme.colorScheme.background),
      contentPadding = PaddingValues(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 90.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      if (notes.isEmpty()) {
        item {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(40.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = if (isBangla) "কোনো নোট সংরক্ষিত নেই। নিচের + বাটনে ট্যাপ করে নতুন নোট লিখুন।"
              else "No saved notes yet. Tap + to write your first reflection.",
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              fontSize = 13.sp
            )
          }
        }
      }

      items(notes) { note ->
        IslamicCard(modifier = Modifier.fillMaxWidth()) {
          Column {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = note.title,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                  text = note.date,
                  fontSize = 11.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }

              Row {
                IconButton(
                  onClick = {
                    val shareText = "${note.title}\n\n${note.content}\n\n[নুরলাইফ নোটস • ${note.date}]"
                    val intent = Intent().apply {
                      action = Intent.ACTION_SEND
                      putExtra(Intent.EXTRA_TEXT, shareText)
                      type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(intent, "Share Note"))
                  },
                  modifier = Modifier.size(32.dp)
                ) {
                  Icon(Icons.Default.Share, contentDescription = "Share", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                IconButton(
                  onClick = { viewModel.deleteNote(note.id) },
                  modifier = Modifier.size(32.dp)
                ) {
                  Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red.copy(alpha = 0.7f))
                }
              }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
              text = note.content,
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurface,
              lineHeight = 19.sp
            )
          }
        }
      }
    }
  }

  if (showAddDialog) {
    AlertDialog(
      onDismissRequest = { showAddDialog = false },
      title = { Text(if (isBangla) "নতুন ইসলামিক নোট" else "New Islamic Note") },
      text = {
        Column {
          OutlinedTextField(
            value = noteTitle,
            onValueChange = { noteTitle = it },
            placeholder = { Text(if (isBangla) "নোটের শিরোনাম" else "Note Title") },
            modifier = Modifier.fillMaxWidth()
          )
          Spacer(modifier = Modifier.height(8.dp))
          OutlinedTextField(
            value = noteContent,
            onValueChange = { noteContent = it },
            placeholder = { Text(if (isBangla) "আপনার তাদাব্বুর বা সংকল্প লিখুন..." else "Write your reflections or goals...") },
            modifier = Modifier
              .fillMaxWidth()
              .height(140.dp)
          )
        }
      },
      confirmButton = {
        Button(
          onClick = {
            if (noteTitle.isNotBlank() && noteContent.isNotBlank()) {
              viewModel.addNote(noteTitle.trim(), noteContent.trim())
              noteTitle = ""
              noteContent = ""
              showAddDialog = false
            }
          },
          colors = ButtonDefaults.buttonColors(containerColor = IslamicEmerald)
        ) {
          Text(if (isBangla) "সংরক্ষণ" else "Save")
        }
      },
      dismissButton = {
        TextButton(onClick = { showAddDialog = false }) {
          Text(if (isBangla) "বাতিল" else "Cancel")
        }
      }
    )
  }
}
