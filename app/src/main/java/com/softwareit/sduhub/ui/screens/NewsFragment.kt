package com.softwareit.sduhub.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.softwareit.sduhub.R
import com.softwareit.sduhub.base.BaseFragment

class NewsFragment : BaseFragment() {

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

    @Composable
    fun NewsScreen() {

    }

    @Preview(showSystemUi = true)
    @Composable
    fun NewsScreenPreview() {
        SetContent()
    }
}