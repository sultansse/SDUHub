package com.softwareit.sduhub.ui.model

import androidx.compose.runtime.Stable

@Stable
data class NewsDIO(
    val id: String,
    val imageUrl: String,
    val title: String,
    val announce: String,
    val date: String,
    val link: String,
)
