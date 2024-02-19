package com.softwareit.sduhub.ui.screens.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softwareit.sduhub.data.local.notes.NoteDTO


@Composable
fun Notes(
    notes: List<NoteDTO>
) {
    LazyColumn() {
        notes.forEach {
            item {
                NoteItem(note = it)
            }
        }
    }
}

@Composable
fun NoteItem(note: NoteDTO) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8))
            .padding(16.dp)
            .background(Color.Cyan)
    ) {
        Column {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
            Text(text = note.description)
        }
    }
}