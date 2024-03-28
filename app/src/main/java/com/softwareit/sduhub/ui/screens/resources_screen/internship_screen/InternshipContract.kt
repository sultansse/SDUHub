package com.softwareit.sduhub.ui.screens.resources_screen.internship_screen

import com.softwareit.sduhub.core.UiEffect
import com.softwareit.sduhub.core.UiEvent
import com.softwareit.sduhub.core.UiState
import com.softwareit.sduhub.ui.screens.resources_screen.InternshipItemDTO

class InternshipContract {

    sealed class Event : UiEvent {
        data object OnFetchInternship : Event()
    }

    data class State(
        val internshipState: InternShipState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
    }

    sealed class InternShipState {
        data object Idle : InternShipState()
        data class Success(val data: InternshipItemDTO) : InternShipState()
    }

}