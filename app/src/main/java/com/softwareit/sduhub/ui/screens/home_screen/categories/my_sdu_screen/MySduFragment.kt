package com.softwareit.sduhub.ui.screens.home_screen.categories.my_sdu_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.softwareit.sduhub.base.BaseFragment
import com.softwareit.sduhub.common.ui.WebViewComponent

class MySduFragment : BaseFragment() {

    @Composable
    override fun SetContent() {
        MySduScreen()
    }
}

@Composable
fun MySduScreen() {
    WebViewComponent(
        url = "https://oldmy.sdu.edu.kz/",
    )
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MySduScreenPreview() {
    MySduScreen()
}

