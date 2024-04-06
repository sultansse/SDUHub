package com.softwareit.sduhub.ui.screens.resources_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.data.network.backend.NewsItemDTO

class ResourceContract {

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
        data class Success(val internships: List<InternshipItemDTO>) : InternShipsState()
        data class Error(val throwable: Throwable) : InternShipsState()
    }

    sealed class NewsState {
        data object Loading : NewsState()
        data class Success(val news: List<NewsItemDTO>) : NewsState()
        data class Error(val throwable: Throwable) : NewsState()
    }
}