package com.softwareit.sduhub.ui.model

import androidx.compose.runtime.Stable

@Stable
data class InternshipDIO(
    val id: Int,
    val title: String,
    val salary: String? = null,
    val timeFormat: String? = null,
    val placeFormat: String? = null,
    val duration: String? = null,
    val company: String,
    val location: String? = null,
    val applyDeadline: String? = null,
    val description: String,
    val contacts: String,
    val contactsLink : String?
)
