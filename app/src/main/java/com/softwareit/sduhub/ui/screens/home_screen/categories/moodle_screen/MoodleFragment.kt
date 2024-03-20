package com.softwareit.sduhub.ui.screens.home_screen.categories.moodle_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.softwareit.sduhub.core.BaseFragment
import com.softwareit.sduhub.utils.common_presentation.WebViewComponent

class MoodleFragment : BaseFragment() {

    @Composable
    override fun SetContent() {
        MoodleScreen()
    }
}

@Composable
fun MoodleScreen() {
    WebViewComponent(
        url = "https://moodle.sdu.edu.kz/login/index.php/",
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MoodleScreenPreview() {
    MoodleScreen()
}
