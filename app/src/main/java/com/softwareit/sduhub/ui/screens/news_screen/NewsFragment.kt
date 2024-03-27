package com.softwareit.sduhub.ui.screens.news_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.softwareit.sduhub.R
import com.softwareit.sduhub.core.BaseFragment
import com.softwareit.sduhub.ui.screens.home_screen.components.StorylyViewComponent
import com.softwareit.sduhub.ui.theme.colorSduBlue
import com.softwareit.sduhub.ui.theme.colorSduOrange
import com.softwareit.sduhub.utils.Constants
import kotlinx.coroutines.launch
import okhttp3.internal.immutableListOf
import org.koin.androidx.viewmodel.ext.android.viewModel


class NewsFragment : BaseFragment() {

    private val viewModel: NewsScreenViewModel by viewModel()

    private companion object {
        const val INTERNSHIPS_PAGE = 0
        const val NEWS_PAGE = 1
    }

    @Composable
    override fun SetContent() {

        Scaffold(
            topBar = { SearchTopBar() }
        ) {
            Box(modifier = Modifier.padding(it)) {
                NewsScreen()
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
    fun NewsScreen() {

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
        val selectedColor by remember { mutableStateOf(Color.Blue) }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            val coroutineScope = rememberCoroutineScope()

            Text(
                text = "Internships",
                color = if (pagerState.currentPage == INTERNSHIPS_PAGE) Color.White else Color.Black,
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(INTERNSHIPS_PAGE)
                        }
                    }
                    .background(if (pagerState.currentPage == INTERNSHIPS_PAGE) selectedColor else Color.Transparent)
                    .padding(vertical = 4.dp, horizontal = 16.dp)
            )

            Text(
                text = "News",
                color = if (pagerState.currentPage == NEWS_PAGE) Color.White else Color.Black,
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(NEWS_PAGE)
                        }
                    }
                    .background(if (pagerState.currentPage == NEWS_PAGE) selectedColor else Color.Transparent)
                    .padding(vertical = 4.dp, horizontal = 16.dp)
            )

        }

    }

    // TODO change to paging, so news/internships will load by pages
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun ResourcePages(pagerState: PagerState) {

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
                        Internships()
                    }

                    NEWS_PAGE -> {
                        News()
                    }
                }
            }
        }
    }

    @Composable
    private fun Internships() {
        val internships = immutableListOf(
            InternshipItemDTO("Internship 1", "Description 1"),
            InternshipItemDTO("Internship 2", "Description 2"),
            InternshipItemDTO("Internship 3", "Description 3"),
            InternshipItemDTO("Internship 4", "Description 4"),
            InternshipItemDTO("Internship 5", "Description 5"),
            InternshipItemDTO("Internship 1", "Description 1"),
            InternshipItemDTO("Internship 2", "Description 2"),
            InternshipItemDTO("Internship 3", "Description 3"),
            InternshipItemDTO("Internship 4", "Description 4"),
            InternshipItemDTO("Internship 5", "Description 5"),
            InternshipItemDTO("Internship 1", "Description 1"),
            InternshipItemDTO("Internship 2", "Description 2"),
            InternshipItemDTO("Internship 3", "Description 3"),
            InternshipItemDTO("Internship 4", "Description 4"),
            InternshipItemDTO("Internship 5", "Description 5"),
            InternshipItemDTO("Internship 1", "Description 1"),
            InternshipItemDTO("Internship 2", "Description 2"),
            InternshipItemDTO("Internship 3", "Description 3"),
            InternshipItemDTO("Internship 4", "Description 4"),
            InternshipItemDTO("Internship 5", "Description 5"),
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            internships.forEach {
                InternshipItem(it)
            }
        }
    }

    @Composable
    private fun News() {
        val news = immutableListOf(
            NewsItemDTO("News 1", "Description 1", ""),
            NewsItemDTO("News 2", "Description 2", ""),
            NewsItemDTO("News 3", "Description 3", ""),
            NewsItemDTO("News 4", "Description 4", ""),
            NewsItemDTO("News 5", "Description 5", ""),
            NewsItemDTO("News 1", "Description 1", ""),
            NewsItemDTO("News 2", "Description 2", ""),
            NewsItemDTO("News 3", "Description 3", ""),
            NewsItemDTO("News 4", "Description 4", ""),
            NewsItemDTO("News 5", "Description 5", ""),
            NewsItemDTO("News 1", "Description 1", ""),
            NewsItemDTO("News 2", "Description 2", ""),
            NewsItemDTO("News 3", "Description 3", ""),
            NewsItemDTO("News 4", "Description 4", ""),
            NewsItemDTO("News 5", "Description 5", ""),
            NewsItemDTO("News 1", "Description 1", ""),
            NewsItemDTO("News 2", "Description 2", ""),
            NewsItemDTO("News 3", "Description 3", ""),
            NewsItemDTO("News 4", "Description 4", ""),
            NewsItemDTO("News 5", "Description 5", ""),
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            news.forEach {
                NewsItem(it)
            }
        }
    }

    @Composable
    private fun InternshipItem(internship: InternshipItemDTO) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .border(1.dp, Color.Gray)
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
                        text = internship.description,
                        color = Color.Gray
                    )
                }
            }
        }

    }

    @Composable
    private fun NewsItem(news: NewsItemDTO) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(colorSduBlue)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = news.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = colorSduOrange,
                    )
                    Text(
                        text = news.description,
                        color = Color.White,
                    )
                }
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.img_sdukz),
                    contentDescription = news.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clipToBounds(),
                )
            }
        }
    }


    @Preview(showSystemUi = true)
    @Composable
    fun NewsScreenPreview() {
        SetContent()
    }
}

interface ResourceDTO

data class NewsItemDTO(
    val title: String,
    val description: String,
    val imageUrl: String
) : ResourceDTO

data class InternshipItemDTO(
    val title: String,
    val description: String,
) : ResourceDTO