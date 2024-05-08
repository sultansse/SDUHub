package com.softwareit.sduhub.ui.model

import androidx.compose.runtime.Stable

@Stable
data class DeveloperDIO(
    val id: Int,
    val fullname: String,
    val position: String,
    val linkedin: String? = null,
    val email: String? = null,
    val description: String,
)