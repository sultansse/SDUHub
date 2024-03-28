package com.softwareit.sduhub.ui.screens.resources_screen.news_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.softwareit.sduhub.R
import com.softwareit.sduhub.core.BaseFragment
import com.softwareit.sduhub.utils.common_presentation.WebViewComponent
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment(
    private val newsId: String,
    private val link: String,
) : BaseFragment() {

    private val viewModel: NewsScreenViewModel by viewModel()

    @Composable
    override fun SetContent() {

        Scaffold(
            topBar = {
                NewsTopBar(
                    newsId = newsId,
                )
            }
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                WebViewComponent(url = link)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun NewsTopBar(
        newsId: String,
    ) {
        // Top bar
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.news))
            },
            navigationIcon = {
                IconButton(
                    onClick = { viewModel.onBackPressed() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = "Go back"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(0.dp)),
        )

    }
}