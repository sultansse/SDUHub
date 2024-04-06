package com.softwareit.sduhub.ui.screens.resources_screen.internship_details_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.ui.screens.resources_screen.InternshipItemDTO

class InternshipDetailsContract {

    sealed class Event : UiEvent {
        data class OnFetchInternship(val id: Int) : Event()
    }

    data class State(
        val internshipState: InternShipState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
    }

    sealed class InternShipState {
        data object Idle : InternShipState()
        data class Success(val internship: InternshipItemDTO) : InternShipState()
    }

}