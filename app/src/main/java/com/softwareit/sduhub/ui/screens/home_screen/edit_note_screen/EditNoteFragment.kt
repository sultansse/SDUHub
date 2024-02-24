package com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.base.BaseFragment
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

        Text(
            text = "Edit Note $noteId"
        )
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