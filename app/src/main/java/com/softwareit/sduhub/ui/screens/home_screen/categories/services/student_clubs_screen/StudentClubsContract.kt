package com.softwareit.sduhub.ui.screens.home_screen.categories.services.student_clubs_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.ui.model.StudentClubDIO

class StudentClubsContract {

    sealed class Event : UiEvent {
        data object EmptyEffect : Event()
        data class OnApplyToClub(val clubName: String) : Event()
        data class OnStudentClubClick(val studentClub: StudentClubDIO) : Event()
    }

    sealed class Effect : UiEffect {
        data object Idle : Effect()
        data class ApplyGoogleForms(val clubName: String) : Effect()
        data class StudentClubDialog(val studentClub: StudentClubDIO) : Effect()
    }

    data object State : UiState
}