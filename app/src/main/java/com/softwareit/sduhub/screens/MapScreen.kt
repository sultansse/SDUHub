package com.softwareit.sduhub.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ramcosta.composedestinations.annotation.Destination
import com.softwareit.sduhub.components.ColoredText

@Destination
@Composable
fun MapScreen() {
    ColoredText(text = "MapScreen", color = Color(0xFFF1A061))
}