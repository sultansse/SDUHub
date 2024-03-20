package com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.R
import com.softwareit.sduhub.core.BaseFragment
import com.softwareit.sduhub.data.local.notes.NoteDTO
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditNoteFragment(
    private val noteId: Int,
) : BaseFragment() {

    private val viewModel: EditNoteViewModel by viewModel()

    @Composable
    override fun SetContent() {
        val uiState by viewModel.uiState.collectAsState()

        when (val state = uiState.noteState) {
            is EditNoteContract.NoteState.Idle -> {
                viewModel.setEvent(EditNoteContract.Event.OnFetchNote(noteId))
            }

            is EditNoteContract.NoteState.Fetched -> {
                Scaffold(
                    topBar = { EditNoteTopAppBar() },
                    bottomBar = {
                        EditNoteBottomBar(
                            state.note,
                            onDeleteClick = {
                                viewModel.setEvent(
                                    EditNoteContract.Event.OnDeleteNote(
                                        state.note
                                    )
                                )
                            },
                        )
                    },
                    content = {
                        Box(modifier = Modifier.padding(it)) {
                            EditNoteScreen(state.note)
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun EditNoteScreen(note: NoteDTO) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            TextField(
                value = note.title,
                textStyle = MaterialTheme.typography.labelLarge,
                onValueChange = {
                    viewModel.setEvent(
                        EditNoteContract.Event.OnTitleChanged(note.copy(title = it))
                    )
                },
                placeholder = { Text(text = "Title") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = note.description,
                textStyle = MaterialTheme.typography.labelLarge,
                maxLines = 3,
                onValueChange = {
                    viewModel.setEvent(
                        EditNoteContract.Event.OnDescriptionChanged(note.copy(description = it))
                    )
                },
                placeholder = { Text(text = "Description") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrect = true,
                    imeAction = ImeAction.Default,
                ),
                modifier = Modifier.fillMaxSize()
            )
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EditNoteTopAppBar() {
        TopAppBar(
            navigationIcon = {
                IconButton(
                    onClick = { viewModel.onBackPressed() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = "Go back"
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.note),
                    fontWeight = FontWeight.Bold
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(0.dp)),
            actions = {
                OutlinedButton(
                    onClick = {
                        viewModel.setEvent(EditNoteContract.Event.OnSaveNote)
                        Toast.makeText(requireContext(),
                            getString(R.string.saved_successfully), Toast.LENGTH_SHORT)
                            .show()
                    }
                ) {
                    Icon(
                        painter = rememberAsyncImagePainter(R.drawable.ic_save),
                        contentDescription = "Save note"
                    )
                }
            }
        )
    }

    @Composable
    fun EditNoteBottomBar(
        note: NoteDTO,
        onDeleteClick: () -> Unit,
    ) {
        BottomAppBar(
            modifier = Modifier.height(64.dp)
        ) {
            IconButton(
                onClick = {
//                    TODO add functionalty
                    Toast.makeText(requireContext(),
                        getString(R.string.coming_soon), Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    painter = rememberAsyncImagePainter(R.drawable.ic_check_box),
                    contentDescription = "add tasks"
                )
            }
            IconButton(
                onClick = {
                    shareNote(requireActivity(), note = viewModel.noteFlow.value)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "select notify date"
                )
            }
            Text(
                text = if (note.updatedAt.isBlank()) {
                    stringResource(R.string.not_modified_yet)
                } else {
                    stringResource(R.string.last_modified, note.updatedAt)
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
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
//                        TODO add functionality
//                        DropdownMenuItem(
//                            onClick = {
//                                menuExpanded = false
////                                onDeleteClick()
//                            },
//                            text = {
//                                Text("Delete")
//                            }
//                        )
                        DropdownMenuItem(
                            onClick = {
                                menuExpanded = false
                            },
                            text = {
                                Text(stringResource(R.string.coming_soon))
//                                TODO add functionality
                            }
                        )
                    }
                )
            }

        }
    }
}

fun shareNote(activity: Activity, note: NoteDTO) {
    val shareMsg =
        "Hey, I want to share this note with you:\n\nTitle: ${note.title}\n\nNote: ${note.description}"

    val intent = ShareCompat.IntentBuilder(activity)
        .setType("text/plain")
        .setText(shareMsg)
        .intent
    activity.startActivity(Intent.createChooser(intent, null))
}