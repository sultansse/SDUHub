package com.softwareit.sduhub.ui.screens.news_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softwareit.sduhub.R
import com.softwareit.sduhub.base.BaseFragment
import com.softwareit.sduhub.ui.screens.home_screen.components.StorylyViewComponent
import com.softwareit.sduhub.utils.Constants
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment() {

    private val viewModel: NewsScreenViewModel by viewModel()

    private companion object {
        const val internshipsPage = 0
        const val newsPage = 1
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun SetContent() {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = stringResource(R.string.app_name)) })
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
                ResourcePages(pagerState)
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
            )

            StorylyViewComponent(
                Constants.STORYLY_INSTANCE_TOKEN,
                groupIconHeight = 520,
                groupIconWidth = 460,
                groupIconCornerRadius = 2,
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
                color = if (pagerState.currentPage == internshipsPage) Color.White else Color.Black,
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(internshipsPage)
                        }
                    }
                    .background(if (pagerState.currentPage == internshipsPage) selectedColor else Color.Transparent)
                    .padding(vertical = 4.dp, horizontal = 12.dp)

            )

            Text(
                text = "News",
                color = if (pagerState.currentPage == newsPage) Color.White else Color.Black,
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(newsPage)
                        }
                    }
                    .background(if (pagerState.currentPage == newsPage) selectedColor else Color.Transparent)
                    .padding(vertical = 4.dp, horizontal = 12.dp)

            )
        }

    }


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun ResourcePages(pagerState: PagerState) {

        Column {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
            ) { currentPage ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                ) {
                    when (currentPage) {
                        internshipsPage -> {
                            Text(
                                text = "Internships",
                                fontSize = 24.sp,
                                color = Color.Black
                            )
                        }

                        newsPage -> {
                            Text(
                                text = "News",
                                fontSize = 24.sp,
                                color = Color.Black
                            )
                        }
                    }


                }
            }
        }
    }


    @Preview(showSystemUi = true)
    @Composable
    fun NewsScreenPreview() {
        SetContent()
    }
}