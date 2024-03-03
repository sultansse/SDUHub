package com.softwareit.sduhub.ui.screens.home_screen.categories.sdu_library_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.softwareit.sduhub.ui.base.BaseFragment
import com.softwareit.sduhub.ui.common.WebViewWithLoading

class SduLibraryFragment : BaseFragment() {

    @Composable
    override fun SetContent() {
        SduLibraryScreen()
    }
}

@Composable
fun SduLibraryScreen() {
    val isLoading = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WebViewWithLoading(
            url = "https://library.sdu.edu.kz/",
            isLoading = isLoading
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SduLibraryScreenPreview() {
    SduLibraryScreen()
}
