package com.softwareit.sduhub.ui.screens.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import com.softwareit.sduhub.R
import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.ui.screens.home_screen.components.Categories
import com.softwareit.sduhub.ui.screens.home_screen.components.NoteItem
import com.softwareit.sduhub.ui.screens.home_screen.components.Stories
import com.softwareit.sduhub.ui.theme.SDUHubTheme
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val viewModel: HomeScreenViewModel by viewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {
        SDUHubTheme {

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = stringResource(R.string.app_name),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    )
                },
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    var counter by remember {
                        mutableIntStateOf(0)
                    }
                    FloatingActionButton(
//                        shape = CircleShape,
                        onClick = {
                            counter++
                            val data =
                                NoteDTO(title = "some $counter text", description = "some desc")
                            viewModel.setEvent(HomeContract.Event.OnNoteAdded(data))
                        }
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                },
            ) {
                Box(
                    modifier = Modifier.padding(it)
                ) {
                    HomeScreen(viewModel)
                }
            }
        }
    }

    @Composable
    fun HomeScreen(viewModel: HomeScreenViewModel) {

//        val importantInfo by viewModel.data.collectAsState()
        val state = viewModel.uiState.collectAsState().value

//        LaunchedEffect(SIDE_EFFECTS_KEY) {
//            effectFlow?.onEach { effect ->
//                when (effect) {
//                    is HomeContract.Effect.ShowError -> {
//                        Toast.makeText(requireContext(), effect.message, Toast.LENGTH_SHORT).show()
//                    }
//
//                }
//            }?.collect()
//
//        }

        LazyColumn {


            item { Stories() }
            item { Categories() }
//            AnimatedVisibility(visible = (importantInfo != null)) {
//                ImportantInfo(data = importantInfo!!)
//            }
            item {
                Button(
                    onClick = {
//                viewModel.goToCategory()
//                    viewModel.setData(ImportantInfoDTO("some title 1", "some description 2" ))
//                    onEventSent { HomeContract.Event.OnNotesDeleted}
                        viewModel.setEvent(HomeContract.Event.OnNotesDeleted)
                    }
                ) {
                    Text(text = "clear db")
                }
            }
            when (state.notesState) {

                is HomeContract.NotesState.Success -> {
                    items(state.notesState.data) {
                        NoteItem(note = it)
                    }
                }

                HomeContract.NotesState.Idle -> {
                    item { Text(text = "DB is empty") }
                }
            }
        }
    }
}