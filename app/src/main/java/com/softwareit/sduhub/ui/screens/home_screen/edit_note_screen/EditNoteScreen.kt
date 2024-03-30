package com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen

import android.content.Context
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat
import coil.compose.rememberAsyncImagePainter
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.NavigationContainer
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.StackState
import com.github.terrakok.modo.stack.back
import com.softwareit.sduhub.R
import com.softwareit.sduhub.data.local.notes.NoteDTO
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@Parcelize
class EditNoteScreenClass(
    private val noteId: Int,
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {

        val viewModel: EditNoteViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        when (val state = uiState.noteState) {
            is EditNoteContract.NoteState.Idle -> {
                LaunchedEffect(key1 = true) {
                    viewModel.setEvent(EditNoteContract.Event.OnFetchNote(noteId))
                }
            }

            is EditNoteContract.NoteState.Fetched -> {
                val parent = LocalContainerScreen.current
                Scaffold(
                    topBar = { EditNoteTopAppBar(parent as StackScreen, viewModel) },
                    bottomBar = {
                        EditNoteBottomBar(
                            state.note,
                            viewModel,
                            onDeleteClick = {
                                viewModel.setEvent(EditNoteContract.Event.OnDeleteNote(state.note))
                            },
                        )
                    },
                    content = {
                        Box(modifier = Modifier.padding(it)) {
                            EditNoteScreen(state.note, viewModel)
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun EditNoteScreen(note: NoteDTO, viewModel: EditNoteViewModel) {

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
    fun EditNoteTopAppBar(
        navigator: NavigationContainer<StackState>,
        viewModel: EditNoteViewModel
    ) {
        val context = LocalContext.current
        TopAppBar(
            navigationIcon = {
                IconButton(
                    onClick = { navigator.back() }
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
                        navigator.back()
                        Toast.makeText(context, "Saved successfully", Toast.LENGTH_SHORT).show()
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
        viewModel: EditNoteViewModel,
        onDeleteClick: () -> Unit,
    ) {
        val context = LocalContext.current
        BottomAppBar(
            modifier = Modifier.height(64.dp)
        ) {
            IconButton(
                onClick = {
//                    TODO add functionalty
                    Toast.makeText(
                        context,
                        "add tasks", Toast.LENGTH_SHORT
                    ).show()
                }
            ) {
                Icon(
                    painter = rememberAsyncImagePainter(R.drawable.ic_check_box),
                    contentDescription = "add tasks"
                )
            }
            IconButton(
                onClick = {
                    shareNote(context, note = viewModel.noteFlow.value)
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

fun shareNote(context: Context, note: NoteDTO) {
    val shareMsg =
        "Hey, I want to share this note with you:\n\nTitle: ${note.title}\n\nNote: ${note.description}"

    val intent = ShareCompat.IntentBuilder(context)
        .setType("text/plain")
        .setText(shareMsg)
        .intent
    context.startActivity(Intent.createChooser(intent, null))
}