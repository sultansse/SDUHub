package com.softwareit.sduhub.ui.screens.home_screen.note_details_screen

import com.softwareit.sduhub.core.UiEffect
import com.softwareit.sduhub.core.UiEvent
import com.softwareit.sduhub.core.UiState
import com.softwareit.sduhub.data.local.notes.NoteDTO

class NoteDetailsContract {

    sealed class Event : UiEvent {
        data class OnFetchNote(val noteId: Int) : Event()
        data class OnDeleteNote(val note: NoteDTO) : Event()
        data object OnSaveNote : Event()
        data class OnTitleChanged(val title: String) : Event()
        data class OnDescriptionChanged(val description: String) : Event()
    }

    data class State(
        val noteState: NoteState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowInformToast(val message : String?) : Effect()
    }

    sealed class NoteState {
        data object NoteFound : NoteState()
        data class Fetched(val note: NoteDTO) : NoteState()
    }
}