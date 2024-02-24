package com.softwareit.sduhub.ui.screens.home_screen.edit_note_screen

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.softwareit.sduhub.domain.AddNoteUseCase
import com.softwareit.sduhub.domain.DeleteNotesUseCase
import com.softwareit.sduhub.domain.GetNotesUseCase

class EditNoteViewModel(
    private val router: Router,
    private val getNotes: GetNotesUseCase,
    private val addNote: AddNoteUseCase,
    private val deleteNotes: DeleteNotesUseCase,
) : ViewModel() {
//) : BaseViewModel<EditNoteContract.Event, EditNoteContract.State, EditNoteContract.Effect>() {

    fun onBackPressed() = router.exit()

//    override fun setInitialState(): EditNoteContract.State {
//        return EditNoteContract.State()
//    }
//
//    override fun handleEvent(event: EditNoteContract.Event) {
//
//    }

}