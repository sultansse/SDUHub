package com.softwareit.sduhub.ui.screens.news_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.ui.model.NewsDIO

class NewsDetailsContract {

    sealed class Event : UiEvent {
        data object OnFetchNews : Event()
    }

    data class State(
        val newsState: NewsState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
    }

    sealed class NewsState {
        data object Idle : NewsState()
        data class Success(val data: NewsDIO?) : NewsState()
    }
}