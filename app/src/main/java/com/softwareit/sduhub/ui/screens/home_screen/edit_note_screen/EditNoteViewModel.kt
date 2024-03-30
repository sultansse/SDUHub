package com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.BaseViewModel
import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.domain.notes_usecase.DeleteNoteUseCase
import com.softwareit.sduhub.domain.notes_usecase.GetNoteUseCase
import com.softwareit.sduhub.domain.notes_usecase.UpsertNoteUseCase
import com.softwareit.sduhub.utils.Constants.Companion.NEW_NOTE_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class EditNoteViewModel(

    private val getNote: GetNoteUseCase,
    private val upsertNote: UpsertNoteUseCase,
    private val deleteNote: DeleteNoteUseCase,
) : BaseViewModel<EditNoteContract.Event, EditNoteContract.State, EditNoteContract.Effect>() {


    // TODO fix and do single state
    private val _noteFlow = MutableStateFlow(NoteDTO(title = "", description = "", updatedAt = ""))
    val noteFlow: StateFlow<NoteDTO> = _noteFlow

    init {
        viewModelScope.launch {
            _noteFlow
                .filter { it.title.isNotBlank() || it.description.isNotBlank() }
                .debounce(800)
                .distinctUntilChanged()
                .collect {
                    upsertNote(it)
                }
        }
    }

    override fun setInitialState(): EditNoteContract.State {
        return EditNoteContract.State(
            noteState = EditNoteContract.NoteState.Idle,
        )
    }

    override fun handleEvent(event: EditNoteContract.Event) {
        when (event) {
            is EditNoteContract.Event.OnFetchNote -> {
                fetchNote(noteId = event.noteId)
            }

            is EditNoteContract.Event.OnDeleteNote -> {
//                do not use until fix fetching null object from room.
//                when we get note id in fragment it starts to fetch it,
//                but when we deleted and try go back, it will try to fetch deleted object
                deleteNote(event.note.id)
            }

            is EditNoteContract.Event.OnSaveNote -> {
                viewModelScope.launch {
                    upsertNote(noteFlow.value)
                }
            }

            is EditNoteContract.Event.OnTitleChanged -> {
                updateTitle(event.updatedNote)
            }

            is EditNoteContract.Event.OnDescriptionChanged -> {
                updateDescription(event.updatedNote)
            }

        }
    }

    private fun updateTitle(updatedNote: NoteDTO) {
        _noteFlow.update { updatedNote }
        setState { copy(noteState = EditNoteContract.NoteState.Fetched(updatedNote)) }
    }

    private fun updateDescription(updatedNote: NoteDTO) {
        _noteFlow.update { updatedNote }
        setState { copy(noteState = EditNoteContract.NoteState.Fetched(updatedNote)) }
    }

    private fun fetchNote(noteId: Int) {
        viewModelScope.launch {
            if (noteId == NEW_NOTE_ID) {
                val emptyNote = NoteDTO(title = "", description = "", updatedAt = "")
                setState { copy(noteState = EditNoteContract.NoteState.Fetched(emptyNote)) }
            } else {
                getNote(noteId).collect {
                    setState { copy(noteState = EditNoteContract.NoteState.Fetched(it)) }
                }
            }
        }
    }

    private fun deleteNote(noteId: Int) {

        if (noteId != NEW_NOTE_ID) {
            viewModelScope.launch(Dispatchers.IO) {
                deleteNote.invoke(noteId)
            }
        }
    }

}