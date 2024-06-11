package com.softwareit.sduhub.ui.model

import androidx.compose.runtime.Stable

@Stable
data class FacultyDIO(
    val deanName: String,
    val deanImage: Int?,
    val facultyName: String,
    val facultyImage: Int,
    val facultyDescription: String,
    val facultySpecialities: List<String> = emptyList(),
)