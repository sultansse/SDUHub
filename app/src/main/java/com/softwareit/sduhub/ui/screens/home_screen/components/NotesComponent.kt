package com.softwareit.sduhub.ui.screens.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.R
import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.ui.screens.home_screen.HomeContract
import com.softwareit.sduhub.ui.screens.home_screen.HomeScreenViewModel

@Composable
fun NotesComponent(notes: List<NoteDTO>) {
    val viewModel: HomeScreenViewModel = viewModel()

    Column {
        notes.forEach {
            NoteItem(
                note = it,
                onNoteClick = {
                    viewModel.goToEditNote(noteId = it.id)
                },
                onDeleteClick = {
                    viewModel.setEvent(HomeContract.Event.OnNoteDeleted(noteId = it.id))
                },
                onCopyClick = {
                    viewModel.setEvent(HomeContract.Event.OnNoteCopied(note = it))
                }
            )
        }
    }
}


@Composable
fun NoteItem(
    note: NoteDTO,
    onNoteClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onCopyClick: () -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onNoteClick()
            }
    ) {

        Image(
            painter = rememberAsyncImagePainter(R.drawable.img_bg_note),
            contentDescription = "background image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
            colorFilter = if (isSystemInDarkTheme()) {
                ColorFilter.tint(
                    color = Color.Black.copy(alpha = 0.5f),
                    blendMode = BlendMode.Darken
                )
            } else {
                null
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = note.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    fontSize = 24.sp,
                )

                var menuExpanded by remember { mutableStateOf(false) }
                IconButton(
                    onClick = { menuExpanded = !menuExpanded }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options"
                    )
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = {
                            menuExpanded = false
                        },
                        content = {
                            DropdownMenuItem(
                                onClick = {
                                    menuExpanded = false
                                    onDeleteClick()
                                },
                                text = {
                                    Text(stringResource(R.string.delete))
                                }
                            )
                            DropdownMenuItem(
                                onClick = {
                                    menuExpanded = false
                                    onCopyClick()
                                },
                                text = {
                                    Text(stringResource(R.string.copy))
                                }
                            )
                        }
                    )
                }
            }

            Text(
                text = note.description,
                maxLines = 3,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                text = note.updatedAt,
            )
        }
    }
}