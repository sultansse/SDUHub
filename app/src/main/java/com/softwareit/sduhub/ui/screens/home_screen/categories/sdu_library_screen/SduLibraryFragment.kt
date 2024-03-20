package com.softwareit.sduhub.ui.screens.home_screen.categories.sdu_library_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.softwareit.sduhub.core.BaseFragment
import com.softwareit.sduhub.utils.common_presentation.WebViewComponent

class SduLibraryFragment : BaseFragment() {

    @Composable
    override fun SetContent() {
        SduLibraryScreen()
    }
}

@Composable
fun SduLibraryScreen() {
    WebViewComponent(
        url = "https://library.sdu.edu.kz/",
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SduLibraryScreenPreview() {
    SduLibraryScreen()
}
