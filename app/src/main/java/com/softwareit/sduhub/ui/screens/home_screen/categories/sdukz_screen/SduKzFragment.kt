package com.softwareit.sduhub.ui.screens.home_screen.categories.sdukz_screen

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.softwareit.sduhub.ui.base.BaseFragment

class SduKzFragment : BaseFragment() {

    @Composable
    override fun SetContent() {
        SduKzScreen()
    }
}

@Composable
fun SduKzScreen() {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                loadUrl("https://sdu.edu.kz/language/en/")
            }
        },
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SduKzScreenPreview() {
    SduKzScreen()
}
