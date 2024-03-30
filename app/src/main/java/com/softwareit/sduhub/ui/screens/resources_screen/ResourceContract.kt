package com.softwareit.sduhub.ui.screens.resources_screen

import com.softwareit.sduhub.core.UiEffect
import com.softwareit.sduhub.core.UiEvent
import com.softwareit.sduhub.core.UiState
import com.softwareit.sduhub.data.network.backend.NewsItemDTO

class ResourceContract {

    sealed class Event : UiEvent {
        data object OnFetchInternships : Event()
        data object OnFetchNews : Event()
        data class OnSearch(val query: String) : Event()
    }

    data class State(
        val internshipsState: InternShipsState,
        val newsState: NewsState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
    }

    sealed class InternShipsState {
        data object Idle : InternShipsState()
        data class Success(val data: List<InternshipItemDTO>) : InternShipsState()
    }

    sealed class NewsState {
        data object Idle : NewsState()
        data class Success(val data: List<NewsItemDTO>) : NewsState()
    }
}