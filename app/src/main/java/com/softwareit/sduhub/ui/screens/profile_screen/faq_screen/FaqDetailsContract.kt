package com.softwareit.sduhub.ui.screens.profile_screen.faq_screen

import com.google.common.collect.ImmutableList
import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.ui.model.FaqDIO

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
        data object Loading : FaqState()
        data class Success(val faqItems: ImmutableList<FaqDIO>) : FaqState()
        data class Error(val exception: Throwable) : FaqState()
    }
}