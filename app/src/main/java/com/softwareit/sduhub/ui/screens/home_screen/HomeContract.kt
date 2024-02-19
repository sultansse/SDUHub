package com.softwareit.sduhub.ui.screens.home_screen

import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.ui.base.ViewEvent
import com.softwareit.sduhub.ui.base.ViewSideEffect
import com.softwareit.sduhub.ui.base.ViewState

class HomeContract {

    sealed class Event : ViewEvent {
        data object OnFetchImportantInfo : Event()
        data object OnFetchNotes : Event()
        data class OnNoteClicked(val note : NoteDTO) : Event()
        data class OnNoteAdded(val note: NoteDTO = NoteDTO(0,"some note title", "some desc for note")) : Event()
    }

    data class State(
        val notes: List<NoteDTO>,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data class ShowError(val message : String?) : Effect()
    }

//    sealed class ImportantInfoState {
//        data object Idle : ImportantInfoState()
//        data class Success(val data: ImportantInfoDTO) : ImportantInfoState()
//    }
//
//    sealed class NotesState {
//        data object Idle : NotesState()
//        data class Success(val data: List<NoteDTO>) : NotesState()
//    }
}