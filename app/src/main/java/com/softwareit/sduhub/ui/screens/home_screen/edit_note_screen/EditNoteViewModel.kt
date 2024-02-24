package com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.softwareit.sduhub.domain.AddNoteUseCase
import com.softwareit.sduhub.domain.DeleteNoteUseCase
import com.softwareit.sduhub.domain.GetNoteUseCase
import com.softwareit.sduhub.domain.UpdateNoteUseCase
import com.softwareit.sduhub.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class EditNoteViewModel(
    private val router: Router,

    private val getNote: GetNoteUseCase,
    private val addNote: AddNoteUseCase,
    private val updateNote: UpdateNoteUseCase,
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
                fetchNote(noteId = event.noteId)
            }
            is EditNoteContract.Event.OnSaveNote -> {
                saveNote()
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

    private fun saveNote() {
        // todo
    }

    private fun deleteNote() {
        // todo
    }

}