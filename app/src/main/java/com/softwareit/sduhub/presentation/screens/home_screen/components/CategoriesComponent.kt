package com.softwareit.sduhub.presentation.screens.home_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun Categories() {
    Column {
        Row {
            repeat(4) {
//                Category(
//
//                )
            }
        }
        Row {
            repeat(4) {
//                Category(
//
//                )
            }
        }

    }

}

@Composable
fun Category(
    icon: ImageVector,
    title: String,
) {
//    Image(
//        imageVector = ,
//        contentDescription = title
//    )
    Text(text = title)
}