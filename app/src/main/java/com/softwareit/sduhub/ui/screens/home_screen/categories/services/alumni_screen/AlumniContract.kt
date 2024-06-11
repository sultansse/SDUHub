package com.softwareit.sduhub.ui.screens.home_screen.categories.services.alumni_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState

class AlumniContract {

    sealed class Event : UiEvent {
        data object EmptyEffect : Event()
        data class OnAlumniDetailsClick(val alumni: AlumniDTO) : Event()
    }

    sealed class Effect : UiEffect {
        data object Idle : Effect()
        data class AlumniBottomSheet(val alumni: AlumniDTO) : Effect()
    }

    data object State : UiState
}