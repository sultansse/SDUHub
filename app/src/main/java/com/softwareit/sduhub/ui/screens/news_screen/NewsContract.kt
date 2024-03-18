package com.softwareit.sduhub.ui.screens.news_screen

import com.softwareit.sduhub.base.UiEffect
import com.softwareit.sduhub.base.UiEvent
import com.softwareit.sduhub.base.UiState
import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfoDTO

class NewsContract {

    sealed class Event : UiEvent {
        data object OnFetchImportantInfo : Event()
        data object OnFetchNotes : Event()
        data class OnNoteClicked(val note : NoteDTO) : Event()
        data class OnNoteAdded(val note: NoteDTO) : Event()
        data class OnNoteDeleted(val noteId: Int) : Event()
        data object OnNotesDeleted : Event()
        data class OnNoteCopied(val note: NoteDTO): Event()
    }

    data class State(
        val importantInfoState: ImportantInfoState,
        val notesState: NotesState,
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowError(val message : String?) : Effect()
    }

    sealed class ImportantInfoState {
        data object Idle : ImportantInfoState()
        data class Success(val data: ImportantInfoDTO) : ImportantInfoState()
    }

    sealed class NotesState {
        data object Idle : NotesState()
        data class Success(val data: List<NoteDTO>) : NotesState()
    }
}