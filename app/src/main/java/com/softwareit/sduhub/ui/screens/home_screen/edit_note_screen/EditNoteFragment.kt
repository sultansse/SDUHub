package com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import com.softwareit.sduhub.R
import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.ui.base.BaseFragment
import com.softwareit.sduhub.utils.getFormattedTime
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
                EditNoteScreen()
            }
        }
    }

    @Composable
    fun EditNoteScreen() {

        val uiState by viewModel.uiState.collectAsState()

        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }

        when (val state = uiState.noteState) {
            is EditNoteContract.NoteState.Idle -> {
                viewModel.setEvent(EditNoteContract.Event.OnFetchNote(noteId))
            }

            is EditNoteContract.NoteState.Empty -> {
            }

            is EditNoteContract.NoteState.Success -> {
                title = state.data.title
                description = state.data.description
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val note = NoteDTO(
                id = noteId,
                title = title,
                description = description,
                created_at = getFormattedTime(),
            )
            Button(
                onClick = { viewModel.setEvent(EditNoteContract.Event.OnSaveNote(note)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Save")
            }

            TextField(
                value = title,
                onValueChange = { title = it },
                placeholder = { Text(text = "Title") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                placeholder = { Text(text = "Description") },
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
                .shadow(4.dp, RoundedCornerShape(0.dp))
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