package com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

        var title by remember {
            mutableStateOf("")
        }

        var description by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Title")
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = description,
                onValueChange = {
                    description = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Description")
                }
            )
            Spacer(modifier = Modifier.height(18.dp))
            Button(
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        val data = NoteDTO(
                            title = title,
                            description = description,
                            created_at = getFormattedTime()
                        )
                        viewModel.setEvent(EditNoteContract.Event.OnSaveNote(data))
                    }
                },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(text = "Submit", color = Color.White)
            }
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
            }
        )
    }

}