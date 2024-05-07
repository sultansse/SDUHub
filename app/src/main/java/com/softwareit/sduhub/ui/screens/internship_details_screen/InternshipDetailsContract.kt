package com.softwareit.sduhub.ui.screens.internship_details_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.ui.model.InternshipDIO

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
        data object Loading : InternShipState()
        data class Success(val internship: InternshipDIO) : InternShipState()
        data class Error(val exception: Throwable) : InternShipState()
    }

}