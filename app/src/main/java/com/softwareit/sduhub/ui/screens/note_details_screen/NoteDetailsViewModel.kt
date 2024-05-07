package com.softwareit.sduhub.ui.screens.note_details_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.base.BaseViewModel
import com.softwareit.sduhub.data.local.room.notes.NoteDBO
import com.softwareit.sduhub.data.repository.NotesRepository
import com.softwareit.sduhub.utils.Constants.Companion.NEW_NOTE_ID
import com.softwareit.sduhub.utils.getFormattedTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteDetailsViewModel(
    private val notesRepository: NotesRepository,
) : BaseViewModel<NoteDetailsContract.Event, NoteDetailsContract.State, NoteDetailsContract.Effect>() {

    private var note = NoteDBO(title = "", description = "", updatedAt = getFormattedTime())

    override fun setInitialState(): NoteDetailsContract.State {
        return NoteDetailsContract.State(
            noteState = NoteDetailsContract.NoteState.Idle,
        )
    }

    override fun handleEvent(event: NoteDetailsContract.Event) {
        when (event) {
            is NoteDetailsContract.Event.OnFetchNote -> {
                fetchNote(event.noteId)
            }

            is NoteDetailsContract.Event.OnDeleteNote -> {
                deleteNote(event.note.id)
            }

            is NoteDetailsContract.Event.OnSaveNote -> {
                saveNote()
            }

            is NoteDetailsContract.Event.OnTitleChanged -> {
                note = note.copy(title = event.title)
            }

            is NoteDetailsContract.Event.OnDescriptionChanged -> {
                note = note.copy(description = event.description)
            }
        }
    }

    private fun fetchNote(noteId: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            when (noteId) {
                null -> {
                    setState { copy(noteState = NoteDetailsContract.NoteState.NoteFound) }
                }

                NEW_NOTE_ID -> {
                    setState {
                        copy(noteState = NoteDetailsContract.NoteState.Fetched(note))
                    }
                }

                else -> {
                    notesRepository.getNoteById(noteId).let {
                        note = it
                        setState { copy(noteState = NoteDetailsContract.NoteState.Fetched(it)) }
                    }
                }
            }

        }
    }

    private fun deleteNote(noteId: Int) {

        if (noteId != NEW_NOTE_ID) {
            viewModelScope.launch(Dispatchers.IO) {
                notesRepository.deleteNote(noteId)
            }
        }
    }

    private fun saveNote() {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.upsertNote(note)
        }
    }

}