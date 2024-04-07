package com.softwareit.sduhub.ui.model

import androidx.compose.runtime.Stable

@Stable
data class InternshipDIO(
    val id: Int,
    val title: String,
    val salary: String,
    val timeFormat: String,
    val placeFormat: String,
    val duration: String,
    val company: String,
    val location: String,
    val applyDeadline: String,
    val description: String,
    val contacts: String,
)
