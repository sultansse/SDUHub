package com.softwareit.sduhub.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.annotation.Destination
import com.softwareit.sduhub.components.ColoredText

@Destination(start = true)
@Composable
fun HomeScreen() {
    ColoredText(text = "HomeScreen", color = Color(0xFF6AE66A))
}