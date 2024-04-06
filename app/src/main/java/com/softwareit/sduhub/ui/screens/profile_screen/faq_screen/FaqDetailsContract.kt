package com.softwareit.sduhub.ui.screens.profile_screen.faq_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.domain.faq_usecase.FaqDTO

class FaqDetailsContract {

    sealed class Event : UiEvent {
        data object OnFetchFaqItems : Event()
    }

    data class State(
        val faqState: FaqState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
    }

    sealed class FaqState {
        data object Idle : FaqState()
        data class Fetched(val faqItems: List<FaqDTO>) : FaqState()
    }
}