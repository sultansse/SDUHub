package com.softwareit.sduhub.ui.screens.resources_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
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
import com.softwareit.sduhub.ui.screens.resources_screen.components.InternshipsItem
import com.softwareit.sduhub.ui.screens.resources_screen.components.NewsItem
import com.softwareit.sduhub.ui.screens.resources_screen.components.PagerToggle
import com.softwareit.sduhub.ui.screens.resources_screen.components.Recommended
import com.softwareit.sduhub.ui.screens.resources_screen.components.ResourceTab
import com.softwareit.sduhub.ui.screens.resources_screen.internship_details_screen.InternshipDetailsScreenClass
import com.softwareit.sduhub.ui.screens.resources_screen.news_screen.NewsDetailsScreenClass
import com.softwareit.sduhub.utils.common.data.network.getLocalMessage
import com.softwareit.sduhub.utils.common.presentation.GenericLottieAnimationComponent
import com.softwareit.sduhub.utils.common.presentation.LoadingLottieComponent
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@Parcelize
class ResourcesStackScreen(
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
class ResourcesScreenClass(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val parent = LocalContainerScreen.current
        val parentScreen = parent as StackScreen

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.resources),
                            fontFamily = FontFamily(Font(R.font.amiko_bold)),
                        )
                    }
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                ResourcesScreen(parentScreen)
            }
        }
    }

    @Composable
    fun ResourcesScreen(navigator: NavigationContainer<StackState>) {

        val viewModel: ResourceScreenViewModel = koinViewModel()

        val uiState by viewModel.uiState.collectAsState()


        LaunchedEffect(key1 = true) {
            viewModel.setEvent(ResourceContract.Event.OnFetchInternships)
            viewModel.setEvent(ResourceContract.Event.OnFetchNews)
        }

        val selectedTabIndex by viewModel.selectedTab.collectAsState()

        val listState = rememberLazyListState()
        val scope = rememberCoroutineScope()
        val showButton by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize(),
            state = listState,
        ) {
            item {
                Recommended()
            }

            item {
                PagerToggle(
                    selectedTabIndex = selectedTabIndex,
                    onClick = {
                        viewModel.setEvent(ResourceContract.Event.OnChangeTabIndex(it))
                    },
                )
            }

            if (selectedTabIndex == ResourceTab.INTERNSHIPS.page)
                when (val state = uiState.internshipsState) {
                    is ResourceContract.InternShipsState.Success -> {

                        items(
                            count = state.internships.size,
                            key = { state.internships[it].id }
                        ) {
                            val internship = state.internships[it]

                            InternshipsItem(
                                internship,
                                onClick = {
                                    navigator.forward(InternshipDetailsScreenClass(internship.id))
                                },
                            )
                        }
                    }

                    is ResourceContract.InternShipsState.Loading -> {
                        item { LoadingLottieComponent() }
                    }

                    is ResourceContract.InternShipsState.Error -> {
                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = state.exception.getLocalMessage(LocalContext.current),
                                    fontFamily = FontFamily(Font(R.font.amiko_bold)),
                                )
                                GenericLottieAnimationComponent(R.raw.anim_error_occured)
                            }
                        }

                    }
                }

            if (selectedTabIndex == ResourceTab.NEWS.page)
                when (val state = uiState.newsState) {
                    is ResourceContract.NewsState.Success -> {

                        items(
                            state.news.size,
                            key = { state.news[it].id }
                        ) {
                            val currentNews = state.news[it]
                            NewsItem(
                                news = currentNews,
                                onClick = {
                                    navigator.forward(NewsDetailsScreenClass(currentNews.link))
                                },
                            )
                        }
                    }

                    is ResourceContract.NewsState.Loading -> {
                        item { LoadingLottieComponent() }
                    }

                    is ResourceContract.NewsState.Error -> {
                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(
                                    text = state.exception.getLocalMessage(LocalContext.current),
                                    fontFamily = FontFamily(Font(R.font.amiko_bold)),
                                )
                                GenericLottieAnimationComponent(R.raw.anim_error_occured)
                            }
                        }

                    }
                }
        }

        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            GoToTop { scope.launch { listState.animateScrollToItem(0) } }
        }
    }

    @Composable
    fun GoToTop(goToTop: () -> Unit) {
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .size(50.dp)
                    .align(Alignment.BottomEnd),
                onClick = goToTop,
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Scroll to top"
                )
            }
        }
    }
}

@JsonClass(generateAdapter = true)
data class InternshipItemDTO(
    val id: Int,
    val title: String,
    val salary: String,
    val timeFormat: String,
    val placeFormat: String,
    val duration: String,
    val company: String,
    val location: String,
    val applyDeadline: String,
    val description: String,
    val contacts: String,
)
