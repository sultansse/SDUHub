package com.softwareit.sduhub.ui.model

import androidx.compose.runtime.Stable

@Stable
data class StudentDIO(
    val fullname: String,
    val studentId: Int,
    val faculty: String,
)