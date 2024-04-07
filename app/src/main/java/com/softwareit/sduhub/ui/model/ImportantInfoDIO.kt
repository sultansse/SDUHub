package com.softwareit.sduhub.ui.model

import androidx.compose.runtime.Stable

@Stable
data class ImportantInfoDIO(
    val title: String,
    val description: String,
    val tags: List<String>,
)