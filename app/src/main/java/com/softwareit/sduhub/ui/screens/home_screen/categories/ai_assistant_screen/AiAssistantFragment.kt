package com.softwareit.sduhub.ui.screens.home_screen.categories.ai_assistant_screen

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

class AiAssistantFragment : BaseFragment() {

    @Composable
    override fun SetContent() {
        AiAssistant()
    }
}

@Composable
fun AiAssistant() {
    val isLoading = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WebViewWithLoading(
            url = "https://sduhub.ru/",
            isLoading = isLoading
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AiAssistantPreview() {
    AiAssistant()
}
