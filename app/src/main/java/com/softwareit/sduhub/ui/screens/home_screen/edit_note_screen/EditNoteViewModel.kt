package com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.domain.DeleteNoteUseCase
import com.softwareit.sduhub.domain.GetNoteUseCase
import com.softwareit.sduhub.domain.UpsertNoteUseCase
import com.softwareit.sduhub.ui.base.BaseViewModel
import com.softwareit.sduhub.utils.Constants.Companion.NEW_NOTE_ID
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class EditNoteViewModel(
    private val router: Router,

    private val getNote: GetNoteUseCase,
    private val upsertNote: UpsertNoteUseCase,
    private val deleteNote: DeleteNoteUseCase,
) : BaseViewModel<EditNoteContract.Event, EditNoteContract.State, EditNoteContract.Effect>() {

    fun onBackPressed() = router.exit()

    // TODO fix and do single state
    private val noteFlow = MutableStateFlow(NoteDTO(title = "", description = "", updatedAt = ""))

    init {
        viewModelScope.launch {
            noteFlow
                .filter { it.title.isNotBlank() || it.description.isNotBlank() }
                .debounce(1000)
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
                deleteNote()
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
        noteFlow.update { updatedNote }
        setState { copy(noteState = EditNoteContract.NoteState.Fetched(updatedNote)) }
    }

    private fun updateDescription(updatedNote: NoteDTO) {
        noteFlow.update { updatedNote }
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

    private fun deleteNote() {
        // todo
    }

}