package com.softwareit.sduhub.ui.screens.home_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.data.local.room.notes.NoteDBO
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfoDTO

class HomeScreenContract {

    sealed class Event : UiEvent {
        data object OnFetchImportantInfo : Event()
        data object OnFetchNotes : Event()
        data class OnNoteClicked(val note : NoteDBO) : Event()
        data class OnNoteAdded(val note: NoteDBO) : Event()
        data class OnNoteDeleted(val noteId: Int) : Event()
        data object OnNotesDeleted : Event()
        data class OnNoteCopied(val note: NoteDBO): Event()
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
        data class Success(val notes: List<NoteDBO>) : NotesState()
    }
}