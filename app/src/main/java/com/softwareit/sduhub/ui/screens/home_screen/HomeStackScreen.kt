package com.softwareit.sduhub.ui.screens.home_screen

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
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
import com.softwareit.sduhub.ui.SlideTransition
import com.softwareit.sduhub.ui.screens.home_screen.components.Categories
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfo
import com.softwareit.sduhub.ui.screens.home_screen.components.NotesComponent
import com.softwareit.sduhub.ui.screens.home_screen.components.Stories
import com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen.EditNoteScreenClass
import com.softwareit.sduhub.utils.Constants.Companion.NEW_NOTE_ID
import com.softwareit.sduhub.utils.isNotNull
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

        Scaffold(
            topBar = { HomeTopAppBar() },
            floatingActionButton = { HomeAddNoteFAB(parent as StackScreen) },
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                HomeScreen(parent as StackScreen)
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
                    fontWeight = FontWeight.Bold
                )
            }
        )
    }

    @Composable
    fun HomeAddNoteFAB(navigator: NavigationContainer<StackState>) {
        FloatingActionButton(
            shape = CircleShape,
            onClick = {
                navigator.forward(EditNoteScreenClass(NEW_NOTE_ID))
            }
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }

    @Composable
    fun HomeScreen(navigator: NavigationContainer<StackState>) {

        val viewModel: HomeScreenViewModel = koinViewModel()

        val uiState by viewModel.uiState.collectAsState()

        LazyColumn {

            item { Stories() }

            item { Categories() }

            item {
                LaunchedEffect(key1 = true) {
                    viewModel.setEvent(HomeContract.Event.OnFetchImportantInfo)
                }

                when (val state = uiState.importantInfoState) {
                    is HomeContract.ImportantInfoState.Success -> {
                        AnimatedVisibility(visible = (state.data.isNotNull())) {
                            ImportantInfo(data = state.data)
                        }
                    }

                    is HomeContract.ImportantInfoState.Idle -> {
                        Text(
                            text = stringResource(R.string.welcome_back),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                }
            }

            item {
                LaunchedEffect(key1 = true) {
                    viewModel.setEvent(HomeContract.Event.OnFetchNotes)
                }

                when (val state = uiState.notesState) {
                    is HomeContract.NotesState.Success -> {
                        NotesComponent(notes = state.data, navigator)
                    }

                    is HomeContract.NotesState.Idle -> {
                        val composition =
                            rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_not_found))
                        LottieAnimation(
                            composition = composition.value,
                            iterations = Int.MAX_VALUE,
                            alignment = Alignment.TopCenter
                        )
                    }
                }
            }

        }
    }
}