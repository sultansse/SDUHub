package com.softwareit.sduhub.ui.screens.resources_screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import com.softwareit.sduhub.utils.common.presentation.LoadingMarioComponent
import com.squareup.moshi.JsonClass
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

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ResourcesScreen(navigator: NavigationContainer<StackState>) {

        val viewModel: ResourceScreenViewModel = koinViewModel()

        val uiState by viewModel.uiState.collectAsState()


        LaunchedEffect(key1 = true) {
            viewModel.setEvent(ResourceContract.Event.OnFetchInternships)
            viewModel.setEvent(ResourceContract.Event.OnFetchNews)
        }

        var selectedTabIndex by remember { mutableIntStateOf(ResourceTab.INTERNSHIPS.page) }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Recommended()
            }

            item {
                PagerToggle(
                    selectedTabIndex = selectedTabIndex,
                    onClick = { selectedTabIndex = it },
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
                                modifier = Modifier.animateItemPlacement(tween(500))
                            )
                        }
                    }

                    is ResourceContract.InternShipsState.Idle -> {
                        item { LoadingMarioComponent() }
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
                                currentNews,
                                onClick = {
                                    navigator.forward(NewsDetailsScreenClass(currentNews.id))
                                }
                            )
                        }
                    }

                    is ResourceContract.NewsState.Idle -> {
                        item { LoadingMarioComponent() }
                    }
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
