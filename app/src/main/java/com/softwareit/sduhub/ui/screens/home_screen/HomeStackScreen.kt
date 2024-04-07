package com.softwareit.sduhub.ui.screens.home_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.NavigationContainer
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.StackState
import com.github.terrakok.modo.stack.forward
import com.softwareit.sduhub.R
import com.softwareit.sduhub.application.SlideTransition
import com.softwareit.sduhub.ui.screens.home_screen.components.Categories
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfo
import com.softwareit.sduhub.ui.screens.home_screen.components.NoteItem
import com.softwareit.sduhub.ui.screens.home_screen.components.Stories
import com.softwareit.sduhub.ui.screens.home_screen.note_details_screen.NoteDetailsScreenClass
import com.softwareit.sduhub.utils.Constants.Companion.NEW_NOTE_ID
import com.softwareit.sduhub.utils.common.presentation.GenericLottieAnimationComponent
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@Parcelize
class HomeStackScreen(
    private val stackNavModel: StackNavModel
) : StackScreen(stackNavModel) {

    constructor(rootScreen: Screen) : this(StackNavModel(rootScreen))

    @Composable
    override fun Content() {
        Box(Modifier.windowInsetsPadding(WindowInsets.systemBars)) {
            TopScreenContent {
                SlideTransition()
            }
        }
    }
}

@Parcelize
class HomeScreenClass(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {
        val parent = LocalContainerScreen.current
        val parentScreen = parent as StackScreen

        Scaffold(
            topBar = { HomeTopAppBar() },
            floatingActionButton = { HomeAddNoteFAB(parentScreen) },
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                HomeScreen(parentScreen)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeTopAppBar() {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    fontFamily = FontFamily(Font(R.font.amiko_bold)),
                )
            }
        )
    }

    @Composable
    fun HomeAddNoteFAB(navigator: NavigationContainer<StackState>) {
        FloatingActionButton(
            shape = CircleShape,
            onClick = {
                navigator.forward(NoteDetailsScreenClass(NEW_NOTE_ID))
            }
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add Note")
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun HomeScreen(navigator: NavigationContainer<StackState>) {

        val viewModel: HomeScreenViewModel = koinViewModel()

        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(key1 = true) {
            viewModel.setEvent(HomeScreenContract.Event.OnFetchImportantInfo)
            viewModel.setEvent(HomeScreenContract.Event.OnFetchNotes)
        }

        LazyColumn {

            item { Stories() }

            item { Categories() }

            item {
                AnimatedVisibility(visible = true) {

                    when (val state = uiState.importantInfoState) {
                        is HomeScreenContract.ImportantInfoState.Success -> {
                            ImportantInfo(data = state.data)
                        }

                        is HomeScreenContract.ImportantInfoState.Error -> {
//                            todo add something interesting
                            Text(
                                text = stringResource(R.string.error_occurred),
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.amiko_bold)),
                                modifier = Modifier.padding(20.dp)
                            )
                        }

                        is HomeScreenContract.ImportantInfoState.Idle -> {
                            Text(
                                text = stringResource(R.string.welcome_back),
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.amiko_bold)),
                                modifier = Modifier.padding(20.dp)
                            )
                        }
                    }
                }
            }

            when (val state = uiState.notesState) {
                is HomeScreenContract.NotesState.Success -> {
                    items(state.notes.size, key = { state.notes[it].id }) {
                        val note = state.notes[it]
                        NoteItem(
                            note = note,
                            onNoteClick = {
                                navigator.forward(NoteDetailsScreenClass(note.id))
                            },
                            onDeleteClick = {
                                viewModel.setEvent(HomeScreenContract.Event.OnNoteDeleted(noteId = note.id))
                            },
                            onCopyClick = {
                                viewModel.setEvent(HomeScreenContract.Event.OnNoteCopied(note = note))
                            },
                            modifier = Modifier
                                .animateItemPlacement(tween(500))
                        )
                    }
                }

                is HomeScreenContract.NotesState.Idle -> {
                    item {
                        GenericLottieAnimationComponent(R.raw.anim_not_found)
                    }
                }
            }

        }
    }
}