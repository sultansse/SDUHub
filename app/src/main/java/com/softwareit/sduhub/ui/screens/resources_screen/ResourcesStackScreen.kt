package com.softwareit.sduhub.ui.screens.resources_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
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
import com.softwareit.sduhub.data.network.backend.NewsItemDTO
import com.softwareit.sduhub.ui.SlideTransition
import com.softwareit.sduhub.ui.screens.home_screen.components.StorylyViewComponent
import com.softwareit.sduhub.ui.screens.resources_screen.internship_screen.InternshipDetailsScreenClass
import com.softwareit.sduhub.ui.screens.resources_screen.news_screen.NewsDetailsScreenClass
import com.softwareit.sduhub.ui.theme.colorSduBlue
import com.softwareit.sduhub.ui.theme.colorSduOrange
import com.softwareit.sduhub.utils.Constants
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import okhttp3.internal.immutableListOf
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

    companion object {
        const val INTERNSHIPS_PAGE = 0
        const val NEWS_PAGE = 1
    }

    @Composable
    override fun Content() {
        val parent = LocalContainerScreen.current

        Scaffold(
            topBar = { SearchTopBar() }
        ) {
            Box(modifier = Modifier.padding(it)) {
                NewsScreen(parent as StackScreen)
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun SearchTopBar() {
        var query by remember { mutableStateOf("") }

        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = {
                if (it.isNotEmpty()) {
//                            onSearch(it)
//                            keyboard?.hide()
                }
            },
            active = false,
            onActiveChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            placeholder = {
                Text(text = "Search here")
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
//                    TODO
//                    content of found items
        }
    }


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun NewsScreen(navigator: NavigationContainer<StackState>) {

        val viewModel: ResourceScreenViewModel = koinViewModel()

        val uiState by viewModel.uiState.collectAsState()

        val pagerState = rememberPagerState(pageCount = { 2 })

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            item {
                Trends()
            }

            item {
                PagerToggle(pagerState)
            }

            item {
                ResourcePages(pagerState)
            }
        }
    }


    @Composable
    private fun Trends() {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(
                text = "Trends",
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            StorylyViewComponent(
                Constants.STORYLY_TRENDS_TOKEN,
                groupIconHeight = 520,
                groupIconWidth = 460,
                groupIconCornerRadius = 2,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun PagerToggle(pagerState: PagerState) {
        val tabs = listOf("Internships" to INTERNSHIPS_PAGE, "News" to NEWS_PAGE)

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            val coroutineScope = rememberCoroutineScope()

            tabs.forEach { (title, page) ->
                val isSelected = pagerState.currentPage == page

                Tab(
                    text = title,
                    isSelected = isSelected,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(page) } }
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
        val backgroundColor = if (isSelected) Color.Blue else Color.Transparent
        val contentColor = if (isSelected) Color.White else Color.Gray

        Text(
            text = text,
            color = contentColor,
            modifier = Modifier
                .padding(vertical = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable { onClick() }
                .background(backgroundColor)
                .padding(vertical = 4.dp, horizontal = 16.dp)
        )
    }


    // TODO change to paging, so news/internships will load by pages
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun ResourcePages(pagerState: PagerState) {

        val parent = LocalContainerScreen.current
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { currentPage ->

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
            ) {
                when (currentPage) {
                    INTERNSHIPS_PAGE -> {
                        Internships(parent as StackScreen)
                    }

                    NEWS_PAGE -> {
                        News(parent as StackScreen)
                    }
                }
            }
        }
    }

    @Composable
    private fun Internships(navigator: NavigationContainer<StackState>) {

        val viewModel: ResourceScreenViewModel = koinViewModel()

        LaunchedEffect(key1 = true) {
            viewModel.setEvent(ResourceContract.Event.OnFetchInternships)
        }

        val uiState by viewModel.uiState.collectAsState()

        when (val internshipsState = uiState.internshipsState) {
            is ResourceContract.InternShipsState.Idle -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is ResourceContract.InternShipsState.Success -> {
                val internships = internshipsState.data
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    internships.forEach {
                        InternshipItem(it) {
                            navigator.forward(InternshipDetailsScreenClass(it.id))
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun News(navigator: NavigationContainer<StackState>) {

        val viewModel: ResourceScreenViewModel = koinViewModel()

        LaunchedEffect(key1 = true) {
            viewModel.setEvent(ResourceContract.Event.OnFetchNews)
        }

        val uiState by viewModel.uiState.collectAsState()

        when (val newsState = uiState.newsState) {
            is ResourceContract.NewsState.Success -> {
                val news = newsState.data
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    news.forEach {
                        NewsItem(it) {
                            navigator.forward(NewsDetailsScreenClass(it.link))
                        }
                    }
                }
            }

            is ResourceContract.NewsState.Idle -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    @Composable
    private fun InternshipItem(internship: InternshipItemDTO, onClick: () -> Unit) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .border(1.dp, Color.Gray)
                .clickable { onClick() }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                val iconList = immutableListOf(
                    R.drawable.img_abstract_rocket,
                    R.drawable.img_abstract_bank,
                    R.drawable.img_abstract_flower,
                    R.drawable.img_abstract_ice,
                    R.drawable.img_abstract_light,
                )

                Image(
                    painter = rememberAsyncImagePainter(iconList.random()),
                    contentDescription = internship.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(56.dp),
                )
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = internship.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    Text(
                        text = internship.company,
                        color = Color.Gray
                    )
                }
            }
        }

    }

    @Composable
    private fun NewsItem(news: NewsItemDTO, onClick: () -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(colorSduBlue)
                .clickable { onClick() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(news.imageUrl)
                            .setHeader("User-Agent", "Mozilla/5.0")
                            .build(),
                        placeholder = rememberAsyncImagePainter(R.drawable.img_sduhub),
                        contentDescription = news.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clipToBounds(),
                    )
                    Text(
                        text = news.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = colorSduOrange,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
                Text(
                    text = news.announce,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

interface ResourceDTO

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
) : ResourceDTO