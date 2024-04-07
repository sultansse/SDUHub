package com.softwareit.sduhub.ui.screens.resources_screen

import com.google.common.collect.ImmutableList
import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.ui.model.InternshipDIO
import com.softwareit.sduhub.ui.model.NewsDIO

class ResourceScreenContract {

    sealed class Event : UiEvent {
        data object OnFetchInternships : Event()
        data object OnFetchNews : Event()
        data class OnChangeTabIndex(val index: Int) : Event()
    }

    data class State(
        val internshipsState: InternShipsState,
        val newsState: NewsState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowError(val message: String?) : Effect()
    }

    sealed class InternShipsState {
        data object Loading : InternShipsState()
        data class Success(val internships: ImmutableList<InternshipDIO>) : InternShipsState()
        data class Error(val exception: Throwable) : InternShipsState()
    }

    sealed class NewsState {
        data object Loading : NewsState()
        data class Success(val news: ImmutableList<NewsDIO>) : NewsState()
        data class Error(val exception: Throwable) : NewsState()
    }
}