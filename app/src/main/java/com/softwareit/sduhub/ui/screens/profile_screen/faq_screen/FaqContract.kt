package com.softwareit.sduhub.ui.screens.profile_screen.faq_screen

import com.softwareit.sduhub.core.UiEffect
import com.softwareit.sduhub.core.UiEvent
import com.softwareit.sduhub.core.UiState
import com.softwareit.sduhub.data.local.notes.NoteDTO

class FaqContract {

    sealed class Event : UiEvent {
//        TODO remove
        data class OnFetchNote(val noteId: Int) : Event()
        data class OnDeleteNote(val note: NoteDTO) : Event()
        data class OnTitleChanged(val updatedNote: NoteDTO) : Event()
        data class OnDescriptionChanged(val updatedNote: NoteDTO) : Event()
    }

    data class State(
        val noteState: FaqState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
    }

    sealed class FaqState {
        data object Idle : FaqState()
        data class Fetched(val note: NoteDTO) : FaqState()
    }
}