package com.softwareit.sduhub.ui.model

import androidx.compose.runtime.Stable

@Stable
data class FaqDIO(
    val id: Int,
    val question: String,
    val answer: String,
)