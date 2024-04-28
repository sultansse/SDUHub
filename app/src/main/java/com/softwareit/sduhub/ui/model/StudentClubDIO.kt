package com.softwareit.sduhub.ui.model

import androidx.compose.runtime.Stable

@Stable
data class StudentClubDIO(
    val name: String,
    val shortDescription : String,
    val longDescription: String,
    val imageResId: Int,
)