package com.softwareit.sduhub.ui.screens.home_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.base.BaseViewModel
import com.softwareit.sduhub.data.local.room.notes.NoteDBO
import com.softwareit.sduhub.domain.important_info_usecase.GetImportantInfoUseCase
import com.softwareit.sduhub.domain.notes_usecase.DeleteNoteUseCase
import com.softwareit.sduhub.domain.notes_usecase.DeleteNotesUseCase
import com.softwareit.sduhub.domain.notes_usecase.GetNotesUseCase
import com.softwareit.sduhub.domain.notes_usecase.UpsertNoteUseCase
import com.softwareit.sduhub.utils.getFormattedTime
import kotlinx.coroutines.launch


class HomeScreenViewModel(
    private val getNotes: GetNotesUseCase,
    private val upsertNote: UpsertNoteUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val deleteNotes: DeleteNotesUseCase,
    private val getImportantInfo: GetImportantInfoUseCase,
) : BaseViewModel<HomeScreenContract.Event, HomeScreenContract.State, HomeScreenContract.Effect>() {

    private fun fetchImportantInfo() {
        viewModelScope.launch {
            getImportantInfo()?.let {
                setState { copy(importantInfoState = HomeScreenContract.ImportantInfoState.Success(it)) }
            }
        }
    }

    override fun setInitialState(): HomeScreenContract.State {
        return HomeScreenContract.State(
            importantInfoState = HomeScreenContract.ImportantInfoState.Idle,
            notesState = HomeScreenContract.NotesState.Idle,
        )
    }

    override fun handleEvent(event: HomeScreenContract.Event) {
        when (event) {
            is HomeScreenContract.Event.OnFetchImportantInfo -> {
                fetchImportantInfo()
            }

            is HomeScreenContract.Event.OnFetchNotes -> {
                fetchNotes()
            }

            is HomeScreenContract.Event.OnNoteClicked -> {
                setEffect {
                    HomeScreenContract.Effect.ShowError("note is clicked")
                }
            }

            is HomeScreenContract.Event.OnNoteAdded -> {
                addNoteUseCase(event.note)
                setEffect {
                    HomeScreenContract.Effect.ShowError("note is added")
                }
            }

            is HomeScreenContract.Event.OnNoteDeleted -> {
                deleteNote(event.noteId)
            }

            is HomeScreenContract.Event.OnNoteCopied -> {
               copyNote(event.note)
            }

            is HomeScreenContract.Event.OnNotesDeleted -> {
                viewModelScope.launch {
                    deleteNotes.invoke()
                    setState {
                        copy(notesState = HomeScreenContract.NotesState.Idle)
                    }
                }
            }
        }
    }

    private fun copyNote(note: NoteDBO) {
        viewModelScope.launch {
            upsertNote.invoke(note.copy(id = 0, title = "${note.title} (Copy)", updatedAt = getFormattedTime()))
        }
    }

    private fun addNoteUseCase(note: NoteDBO) {
        viewModelScope.launch {
            upsertNote.invoke(note)
        }
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            getNotes.invoke().collect() {
                if (it.isEmpty()) {
                    setState { copy(notesState = HomeScreenContract.NotesState.Idle) }
                } else {
                    setState { copy(notesState = HomeScreenContract.NotesState.Success(it)) }
                }
            }
        }
    }

    private fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            deleteNote.invoke(noteId)
        }
    }

}