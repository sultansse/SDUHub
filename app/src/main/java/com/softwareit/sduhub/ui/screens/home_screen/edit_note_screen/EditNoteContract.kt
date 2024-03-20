package com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen

import com.softwareit.sduhub.core.UiEffect
import com.softwareit.sduhub.core.UiEvent
import com.softwareit.sduhub.core.UiState
import com.softwareit.sduhub.data.local.notes.NoteDTO

class EditNoteContract {

    sealed class Event : UiEvent {
        data class OnFetchNote(val noteId: Int) : Event()
        data class OnDeleteNote(val note: NoteDTO) : Event()
        data object OnSaveNote : Event()
        data class OnTitleChanged(val updatedNote: NoteDTO) : Event()
        data class OnDescriptionChanged(val updatedNote: NoteDTO) : Event()
    }

    data class State(
        val noteState: NoteState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
    }

    sealed class NoteState {
        data object Idle : NoteState()
        data class Fetched(val note: NoteDTO) : NoteState()
    }
}