package com.softwareit.sduhub.ui.screens.home_screen

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.softwareit.sduhub.ui.model.ElementDIO
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen.FacultiesScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.sdu_library_screen.SduLibraryScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen.StudentClubsScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.components.Categories
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfo
import com.softwareit.sduhub.ui.screens.home_screen.components.NoteItem
import com.softwareit.sduhub.ui.screens.home_screen.components.ServiceItem
import com.softwareit.sduhub.ui.screens.home_screen.components.Stories
import com.softwareit.sduhub.ui.screens.home_screen.note_details_screen.NoteDetailsScreenClass
import com.softwareit.sduhub.utils.Constants.Companion.NEW_NOTE_ID
import com.softwareit.sduhub.utils.common.openTelegramToUser
import com.softwareit.sduhub.utils.common.presentation.GenericLottieAnimationComponent
import kotlinx.parcelize.Parcelize
import okhttp3.internal.immutableListOf
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

        val viewModel: HomeScreenViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val uiEffect by viewModel.effect.collectAsState(initial = HomeScreenContract.Effect.Idle)

        Scaffold(
            topBar = { HomeTopAppBar() },
            floatingActionButton = { HomeAddNoteFAB(parentScreen) },
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                HomeScreen(
                    uiState = uiState,
                    uiEffect = uiEffect,
                    onUiEvent = viewModel::setEvent,
                    navigator = parentScreen,
                )
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

    @[Composable OptIn(ExperimentalMaterial3Api::class)]
    fun HomeScreen(
        uiState: HomeScreenContract.State,
        uiEffect: HomeScreenContract.Effect,
        onUiEvent: (HomeScreenContract.Event) -> Unit,
        navigator: NavigationContainer<StackState>,
    ) {

        when (val effect = uiEffect) {
            is HomeScreenContract.Effect.Idle -> {

            }

            is HomeScreenContract.Effect.ServicesBottomSheet -> {


                ModalBottomSheet(
                    onDismissRequest = { onUiEvent(HomeScreenContract.Event.EmptyEffect) }
                ) {
                    val context = LocalContext.current
                    val parent = LocalContainerScreen.current
                    val parentScreen = parent as StackScreen

                    Column(modifier = Modifier.padding(bottom = 16.dp)) {
                        services.forEach { service ->
                            ServiceItem(
                                icon = service.icon,
                                title = service.title,
                                onClick = {
                                    navigateToService(service.title, context, parentScreen)
                                }
                            )
                        }
                    }
                }
            }
        }


        LazyColumn {

            item { Stories() }

            item {
                Categories(
                    onUiEvent = onUiEvent,
                    navigator = navigator,
                )
            }

            item {
                when (val state = uiState.importantInfoState) {
                    is HomeScreenContract.ImportantInfoState.Idle -> {
                        LaunchedEffect(key1 = Unit) {
                            onUiEvent(HomeScreenContract.Event.OnFetchImportantInfo)
                        }
                    }

                    is HomeScreenContract.ImportantInfoState.Success -> {
                        ImportantInfo(state.data)
                    }

                    is HomeScreenContract.ImportantInfoState.Empty -> {
                        Text(
                            text = stringResource(R.string.welcome_back),
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.amiko_bold)),
                            modifier = Modifier.padding(20.dp)
                        )
                    }
                }
            }

            when (val state = uiState.notesState) {
                is HomeScreenContract.NotesState.Success -> {
                    items(state.notes.size, key = { state.notes[it].id }) {
                        val note = state.notes[it]
                        NoteItem(
                            note = note,
                            onUiEvent = onUiEvent,
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


val services = immutableListOf(
    ElementDIO(
        icon = R.drawable.img_library,
        title = "SDU Library" // todo stringres
    ),
    ElementDIO(
        icon = R.drawable.img_lost_and_found,
        title = "Lost & Found" // todo stringres
    ),
    ElementDIO(
        icon = R.drawable.img_faculties,
        title = "Faculties" // todo stringres
    ),
    ElementDIO(
        icon = R.drawable.img_student_clubs,
        title = "Student Clubs" // todo stringres
    ),
)

fun navigateToService(
    title: String,
    context: Context,
    navigator: NavigationContainer<StackState>
) {
    val serviceActions = mapOf(
        services[0].title to { navigator.forward(SduLibraryScreenClass()) },
        services[1].title to { openTelegramToUser(context, "SDU_Lost_AND_Found") },
        services[2].title to { navigator.forward(FacultiesScreenClass()) },
        services[3].title to { navigator.forward(StudentClubsScreenClass()) },
    )

    serviceActions[title]?.invoke()
}