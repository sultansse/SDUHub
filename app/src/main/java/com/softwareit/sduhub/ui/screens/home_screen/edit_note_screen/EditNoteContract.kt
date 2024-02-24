package com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen

import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.ui.base.UiEffect
import com.softwareit.sduhub.ui.base.UiEvent
import com.softwareit.sduhub.ui.base.UiState

class EditNoteContract {

    sealed class Event : UiEvent {
        data object OnFetchNote : Event()
        data class OnSaveNote(val note: NoteDTO) : Event()
        data class OnDeleteNote(val note: NoteDTO) : Event()
    }

    data class State(
        val noteState: NoteState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
    }

    sealed class NoteState {
        data object Idle : NoteState()
        data object Empty : NoteState()
        data class Success(val data: List<NoteDTO>) : NoteState()
        data class Error(val message: String) : NoteState()
    }
}