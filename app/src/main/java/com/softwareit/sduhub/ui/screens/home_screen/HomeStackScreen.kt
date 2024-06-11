package com.softwareit.sduhub.ui.screens.home_screen

import android.content.Context
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import com.softwareit.sduhub.common.presentation.GenericLottieAnimationComponent
import com.softwareit.sduhub.common.presentation.openTelegramToUser
import com.softwareit.sduhub.ui.model.ElementDIO
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.alumni_screen.AlumniScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen.FacultiesScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.sdu_library_screen.SduLibraryScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen.StudentClubsScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.components.Categories
import com.softwareit.sduhub.ui.screens.home_screen.components.FoodBotItem
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfo
import com.softwareit.sduhub.ui.screens.home_screen.components.NoteItem
import com.softwareit.sduhub.ui.screens.home_screen.components.ServiceItem
import com.softwareit.sduhub.ui.screens.home_screen.components.Stories
import com.softwareit.sduhub.ui.screens.note_details_screen.NoteDetailsScreenClass
import com.softwareit.sduhub.utils.Constants.Companion.NEW_NOTE_ID
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

    @[Composable OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)]
    fun HomeScreen(
        uiState: HomeScreenContract.State,
        uiEffect: HomeScreenContract.Effect,
        onUiEvent: (HomeScreenContract.Event) -> Unit,
        navigator: NavigationContainer<StackState>,
    ) {

        when (val effect = uiEffect) {
            is HomeScreenContract.Effect.Idle -> {}

            is HomeScreenContract.Effect.ServicesBottomSheet -> {
                ModalBottomSheet(
                    onDismissRequest = { onUiEvent(HomeScreenContract.Event.EmptyEffect) },
                    modifier = Modifier.fillMaxSize()
                        .padding(top = 16.dp)
                ) {
                    services.forEach { service ->
                        ServiceItem(
                            ElementDIO(
                                id = service.id,
                                icon = service.icon,
                                title = service.title,
                            ),
                            navigator = navigator,
                        )
                    }
                }
            }

            is HomeScreenContract.Effect.OrderClickBottomSheet -> {
                ModalBottomSheet(
                    onDismissRequest = { onUiEvent(HomeScreenContract.Event.EmptyEffect) },
                    modifier = Modifier.fillMaxSize()
                        .padding(top = 16.dp)
                ) {
                    orderFoodBots.forEach { bot ->
                        FoodBotItem(
                            ElementDIO(
                                id = bot.id,
                                icon = bot.icon,
                                title = bot.title,
                            ),
                        )
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
                is HomeScreenContract.NotesState.Idle -> {
                    item {
                        LaunchedEffect(key1 = Unit) {
                            onUiEvent(HomeScreenContract.Event.OnFetchNotes)
                        }
                    }
                }

                is HomeScreenContract.NotesState.Success -> {
                    items(state.notes.size, key = { state.notes[it].id }) {
                        val note = state.notes[it]
                        NoteItem(
                            note = note,
                            onUiEvent = onUiEvent,
                            navigator = navigator,
                            modifier = Modifier.animateItemPlacement(tween(300)),
                        )
                    }
                }

                is HomeScreenContract.NotesState.Empty -> {
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
        id = 0,
        icon = R.drawable.img_library,
        title = "SDU Library" // todo stringres
    ),
    ElementDIO(
        id = 1,
        icon = R.drawable.img_lost_and_found,
        title = "Lost & Found" // todo stringres
    ),
    ElementDIO(
        id = 2,
        icon = R.drawable.img_faculties,
        title = "Faculties" // todo stringres
    ),
    ElementDIO(
        id = 3,
        icon = R.drawable.img_student_clubs,
        title = "Student Clubs" // todo stringres
    ),
    ElementDIO(
        id = 4,
        icon = R.drawable.ic_alumni,
        title = "Alumni" // todo stringres
    ),
)

fun navigateToService(
    serviceId: Int,
    context: Context,
    navigator: NavigationContainer<StackState>
) {
    when (serviceId) {
        0 -> navigator.forward(SduLibraryScreenClass())
        1 -> openTelegramToUser(context, "SDU_Lost_AND_Found")
        2 -> navigator.forward(FacultiesScreenClass())
        3 -> navigator.forward(StudentClubsScreenClass())
        4 -> navigator.forward(AlumniScreenClass())
    }
}

val orderFoodBots = immutableListOf(
    ElementDIO(
        id = 0,
        icon = R.drawable.img_urbo_coffee,
        title = "Urbo Coffee" // todo stringres
    ),
    ElementDIO(
        id = 1,
        icon = R.drawable.img_order_food,
        title = "Doner House" // todo stringres
    ),
    ElementDIO(
        id = 2,
        icon = R.drawable.img_eat_and_chat,
        title = "Eat & Chat" // todo stringres
    ),
    ElementDIO(
        id = 3,
        icon = R.drawable.img_red_coffee,
        title = "Red Coffee" // todo stringres
    ),
)

fun navigateToOrderFood(
    botId: Int,
    context: Context,
) {
    when (botId) {
        0 -> openTelegramToUser(context, "SDUUrbobot")
        1 -> openTelegramToUser(context, "SDUOrder_bot")
        2 -> openTelegramToUser(context, "SDUDonerkabot")
        3 -> openTelegramToUser(context, "RedCoffeeeBot")
    }
}