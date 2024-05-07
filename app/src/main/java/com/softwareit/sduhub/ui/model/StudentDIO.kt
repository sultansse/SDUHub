package com.softwareit.sduhub.ui.model

import androidx.compose.runtime.Stable

@Stable
data class StudentDIO(
    val fullname: String,
    val studentId: String,
    val faculty: String,
    val photoUrl: String? = null,
)