package com.softwareit.sduhub.ui.screens.home_screen.categories.moodle_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.softwareit.sduhub.base.BaseFragment
import com.softwareit.sduhub.common.ui.WebViewWithLoading

class MoodleFragment : BaseFragment() {

    @Composable
    override fun SetContent() {
        MoodleScreen()
    }
}

@Composable
fun MoodleScreen() {
    val isLoading = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WebViewWithLoading(
            url = "https://moodle.sdu.edu.kz/login/index.php/",
            isLoading = isLoading
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MoodleScreenPreview() {
    MoodleScreen()
}
