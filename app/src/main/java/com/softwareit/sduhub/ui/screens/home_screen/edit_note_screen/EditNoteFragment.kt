package com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.app.ShareCompat
import com.softwareit.sduhub.R
import com.softwareit.sduhub.base.BaseFragment
import com.softwareit.sduhub.data.local.notes.NoteDTO
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditNoteFragment(
    private val noteId: Int,
) : BaseFragment() {

    private val viewModel: EditNoteViewModel by viewModel()

    @Composable
    override fun SetContent() {
        Scaffold(
            topBar = { EditNoteTopAppBar() },
            bottomBar = { EditNoteBottomBar() },
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                val uiState by viewModel.uiState.collectAsState()

                when (val state = uiState.noteState) {
                    is EditNoteContract.NoteState.Idle -> {
                        viewModel.setEvent(EditNoteContract.Event.OnFetchNote(noteId))
                    }

                    is EditNoteContract.NoteState.Fetched -> {
                        EditNoteScreen(state.note)
                    }
                }
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
                    imeAction = ImeAction.Done,
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
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Go back"
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    fontWeight = FontWeight.Bold
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(0.dp)),
            actions = {
                IconButton(
                    onClick = {
//                        shareNote(requireActivity(), note = viewModel.note.value) TODO fix this
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share this note"
                    )
                }
            }
        )
    }

    @Composable
    fun EditNoteBottomBar() {
        BottomAppBar {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Sharp.Add,
                    contentDescription = "add elements"
                )
            }
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "select notify date"
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