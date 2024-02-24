@file:OptIn(FlowPreview::class)

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
import kotlinx.coroutines.launch

class EditNoteViewModel(
    private val router: Router,

    private val getNote: GetNoteUseCase,
    private val upsertNote: UpsertNoteUseCase,
    private val deleteNote: DeleteNoteUseCase,
) : BaseViewModel<EditNoteContract.Event, EditNoteContract.State, EditNoteContract.Effect>() {

    fun onBackPressed() = router.exit()

    override fun setInitialState(): EditNoteContract.State {
        return EditNoteContract.State(
            noteState = EditNoteContract.NoteState.Idle,
        )
    }

    override fun handleEvent(event: EditNoteContract.Event) {
        when (event) {
            is EditNoteContract.Event.OnFetchNote -> {
                if (event.noteId == NEW_NOTE_ID) {
                    setState { copy(noteState = EditNoteContract.NoteState.Empty) }
                } else {
                    fetchNote(noteId = event.noteId)
                }
            }
            is EditNoteContract.Event.OnSaveNote -> {
                saveNote(event.note)
            }
            is EditNoteContract.Event.OnDeleteNote -> {
                deleteNote()
            }
        }
    }

    private fun fetchNote(noteId: Int) {
        viewModelScope.launch {
            getNote(noteId).collect() {
                setState { copy(noteState = EditNoteContract.NoteState.Success(it)) }
            }
        }
    }

    private fun saveNote(note: NoteDTO) {
        viewModelScope.launch {
            upsertNote.invoke(note)
        }
    }

    private fun deleteNote() {
        // todo
    }

}