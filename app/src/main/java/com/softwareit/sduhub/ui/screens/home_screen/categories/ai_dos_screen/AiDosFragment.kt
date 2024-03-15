package com.softwareit.sduhub.ui.screens.home_screen.categories.ai_dos_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.softwareit.sduhub.base.BaseFragment
import com.softwareit.sduhub.common.ui.WebViewComponent

class AiDosFragment : BaseFragment() {

    @Composable
    override fun SetContent() {
        AiDos()
    }
}

@Composable
fun AiDos() {
    WebViewComponent(
        url = "https://sduhub.ru/",
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AiDosPreview() {
    AiDos()
}
