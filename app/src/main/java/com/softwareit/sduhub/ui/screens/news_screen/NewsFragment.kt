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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.softwareit.sduhub.ui.theme.SduBlue
import com.softwareit.sduhub.ui.theme.SduOrange
import com.softwareit.sduhub.utils.Constants
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment() {

    private val viewModel: NewsScreenViewModel by viewModel()

    private companion object {
        const val INTERNSHIPS_PAGE = 0
        const val NEWS_PAGE = 1
    }

    @Composable
    override fun SetContent() {

        var searchText by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text(text = "Search") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                NewsScreen()
            }
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
                ResourcePages(pagerState, listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)) //1, 2, 3, 4, 5, 6, 7, 8, 9, 10 are just placeholders for the actual data
            }
        }
    }


    @Composable
    private fun Trends() {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
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


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun <T> ResourcePages(pagerState: PagerState, items: List<T>) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
        ) { currentPage ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
            ) {
                when (currentPage) {
                    INTERNSHIPS_PAGE -> {
                        Text(
                            text = "Internships",
                            fontSize = 24.sp,
                            color = Color.Black
                        )
                        items.forEach { internship ->
                            InternshipItem(internship)
                        }
                    }

                    NEWS_PAGE -> {
                        Text(
                            text = "News",
                            fontSize = 24.sp,
                            color = Color.Black
                        )
                        items.forEach { new ->
                            NewsItem(new)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun InternshipItem(internship: Any?) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .border(1.dp, Color.Black)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.img_sdukz),
                    contentDescription = "Vacancy icon"
                )
                Column {
                    Text(
                        text = "Internship Title",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "Company name",
                        color = Color.Gray
                    )
                }
            }
        }

    }

    @Composable
    private fun NewsItem(news: Any?) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(SduBlue)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column {
                    Text(
                        text = "Internship Title",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = SduOrange,
                    )
                    Text(
                        text = "Internship Description",
                        color = Color.White,
                    )
                }
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.img_sdukz),
                    contentDescription = "Vacancy icon",
                    contentScale = ContentScale.Crop,
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

data class NewsItemDTO(
    val title: String,
    val description: String,
    val imageUrl: String
) : Any()

data class InternshipItemDTO(
    val title: String,
    val description: String,
    val icon: Int,
) : Any()