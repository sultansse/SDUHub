package com.softwareit.sduhub.ui.screens.home_screen.categories.services.faculties_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.ui.model.FacultyDIO

class FacultiesContract {

    sealed class Event : UiEvent {
        data object EmptyEffect : Event()
        data class OnFacultyClick(val faculty: FacultyDIO) : Event()
    }

    sealed class Effect : UiEffect {
        data object Idle : Effect()
        data class FacultyDialog(val faculty: FacultyDIO) : Effect()
    }

    data object State : UiState
}