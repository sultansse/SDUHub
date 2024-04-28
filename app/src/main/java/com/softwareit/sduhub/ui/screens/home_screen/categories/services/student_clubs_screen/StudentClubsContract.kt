package com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.ui.model.FacultyDIO

class StudentClubsContract {

    sealed class Event : UiEvent {
        data object EmptyEffect : Event()
        data class OnStudentClubClick(val faculty: FacultyDIO) : Event()
    }

    sealed class Effect : UiEffect {
        data object Idle : Effect()
        data class StudentClubDialog(val faculty: FacultyDIO) : Effect()
    }

    data object State : UiState
}