package com.softwareit.sduhub.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.annotation.Destination
import com.softwareit.sduhub.components.ColoredText

@Destination
@Composable
fun NewsScreen() {
    ColoredText(text = "NewsScreen", color = Color(0xFFAE71EC))
}