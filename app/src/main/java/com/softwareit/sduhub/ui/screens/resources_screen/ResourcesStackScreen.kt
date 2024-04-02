package com.softwareit.sduhub.ui.screens.resources_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.softwareit.sduhub.ui.screens.resources_screen.components.Recommended
import com.softwareit.sduhub.ui.screens.resources_screen.internship_details_screen.InternshipDetailsScreenClass
import com.softwareit.sduhub.ui.screens.resources_screen.news_screen.NewsDetailsScreenClass
import com.softwareit.sduhub.ui.theme.colorSduBlue
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

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ResourcesScreen(navigator: NavigationContainer<StackState>) {

        val viewModel: ResourceScreenViewModel = koinViewModel()

        val uiState by viewModel.uiState.collectAsState()
        val pagerState = rememberPagerState(pageCount = { 2 })

        LaunchedEffect(key1 = true) {
            viewModel.setEvent(ResourceContract.Event.OnFetchInternships)
            viewModel.setEvent(ResourceContract.Event.OnFetchNews)
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Recommended()
            }

            item {
                PagerToggle(pagerState)
            }

            item {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) {

                    if (pagerState.currentPage == ResourceTab.INTERNSHIPS.page)
                        when (val state = uiState.internshipsState) {
                            is ResourceContract.InternShipsState.Success -> {
//                                this@LazyColumn.items(
//                                    count = state.internships.size,
//                                    key = { state.internships[it].id }
//                                ) {
//                                val internship = state.internships[it]
                                Column {
                                    state.internships.forEach { internship ->
                                        InternshipsItem(
                                            internship,
                                            onClick = {
                                                navigator.forward(
                                                    InternshipDetailsScreenClass(
                                                        internship.id
                                                    )
                                                )
                                            }
                                        )
                                    }
                                }
                            }

                            is ResourceContract.InternShipsState.Idle -> {
//                                this@LazyColumn.item {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    CircularProgressIndicator()
                                }
//                                }
                            }
                        }

                    if (pagerState.currentPage == ResourceTab.NEWS.page)
                        when (val state = uiState.newsState) {
                            is ResourceContract.NewsState.Success -> {
//                                this@LazyColumn.items(
//                                    state.news.size,
//                                    key = { state.news[it].id }) {
//                                    val currentNews = state.news[it]
                                Column {
                                    state.news.forEach { currentNews ->
                                        NewsItem(
                                            currentNews,
                                            onClick = {
                                                navigator.forward(NewsDetailsScreenClass(currentNews.id))
                                            }
                                        )
                                    }
                                }
                            }

                            is ResourceContract.NewsState.Idle -> {
//                                this@LazyColumn.item {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    CircularProgressIndicator()
                                }
//                                }
                            }
                        }


                }
            }


        }
    }


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun PagerToggle(pagerState: PagerState) {
        val coroutineScope = rememberCoroutineScope()
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            ResourceTab.entries.forEach { tab ->
                Tab(
                    text = tab.title,
                    isSelected = pagerState.currentPage == tab.page,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(tab.page)
                        }
                    }
                )
            }
        }

    }

    @Composable
    private fun Tab(
        text: String,
        isSelected: Boolean,
        onClick: () -> Unit
    ) {
        val backgroundColor = if (isSelected) colorSduBlue else Color.Transparent
        val contentColor = if (isSelected) Color.White else Color.Gray

        Text(
            text = text,
            color = contentColor,
            fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
            modifier = Modifier
                .padding(vertical = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable { onClick() }
                .background(backgroundColor)
                .padding(vertical = 4.dp, horizontal = 16.dp)
        )
    }

}

enum class ResourceTab(val title: String, val page: Int) {
    INTERNSHIPS("Internships", 0),
    NEWS("News", 1)
}


@JsonClass(generateAdapter = true)
data class InternshipItemDTO(
    val id: Int,
    val title: String,
    val salary: String,
    val format: String,
    val duration: String,
    val company: String,
    val location: String,
    val applyDeadline: String,
    val description: String,
    val contacts: String,
)