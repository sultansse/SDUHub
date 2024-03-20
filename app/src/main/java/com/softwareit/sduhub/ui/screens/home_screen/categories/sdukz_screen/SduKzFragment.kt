package com.softwareit.sduhub.ui.screens.home_screen.categories.sdukz_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.softwareit.sduhub.core.BaseFragment
import com.softwareit.sduhub.utils.common_presentation.WebViewComponent

class SduKzFragment : BaseFragment() {

    @Composable
    override fun SetContent() {
        SduKzScreen()
    }
}

@Composable
fun SduKzScreen() {
    WebViewComponent(
        url = "https://sdu.edu.kz/language/ru/",
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SduKzScreenPreview() {
    SduKzScreen()
}
