package com.softwareit.sduhub.ui.screens.home_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.BaseViewModel
import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.domain.importtant_info_usecase.GetImportantInfoUseCase
import com.softwareit.sduhub.domain.notes_usecase.DeleteNoteUseCase
import com.softwareit.sduhub.domain.notes_usecase.DeleteNotesUseCase
import com.softwareit.sduhub.domain.notes_usecase.GetNotesUseCase
import com.softwareit.sduhub.domain.notes_usecase.UpsertNoteUseCase
import com.softwareit.sduhub.utils.getFormattedTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeScreenViewModel(
    private val getNotes: GetNotesUseCase,
    private val upsertNote: UpsertNoteUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val deleteNotes: DeleteNotesUseCase,
    private val getImportantInfo: GetImportantInfoUseCase,
) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    /* Navigation functions */
//    fun onBackPressed() = savedState.exit()
//    fun goToEditNote(noteId: Int = NEW_NOTE_ID) = savedState.navigateTo(NavigationScreens.Home.editNote(noteId))
//    fun goToAiAssistant() = savedState.navigateTo(NavigationScreens.Home.Categories.aiAssistant())
//    fun goToMySdu() = savedState.navigateTo(NavigationScreens.Home.Categories.mysdu())
//    fun goToSduKz() = savedState.navigateTo(NavigationScreens.Home.Categories.sdukz())
//    fun goToMoodle() = savedState.navigateTo(NavigationScreens.Home.Categories.moodle())
//    fun goToSduLibrary() = savedState.navigateTo(NavigationScreens.Home.Categories.sduLibrary())

    fun onNoteClick(noteId: Int) {

    }

    private fun fetchImportantInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            getImportantInfo()?.let {
                setState { copy(importantInfoState = HomeContract.ImportantInfoState.Success(it)) }
            }
        }
    }

    override fun setInitialState(): HomeContract.State {
        return HomeContract.State(
            importantInfoState = HomeContract.ImportantInfoState.Idle,
            notesState = HomeContract.NotesState.Idle,
        )
    }

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnFetchImportantInfo -> {
                fetchImportantInfo()
            }

            is HomeContract.Event.OnFetchNotes -> {
                fetchNotes()
            }

            is HomeContract.Event.OnNoteClicked -> {
                setEffect {
                    HomeContract.Effect.ShowError("note is clicked")
                }
            }

            is HomeContract.Event.OnNoteAdded -> {
                addNoteUseCase(event.note)
                setEffect {
                    HomeContract.Effect.ShowError("note is added")
                }
            }

            is HomeContract.Event.OnNoteDeleted -> {
                deleteNote(event.noteId)
            }

            is HomeContract.Event.OnNoteCopied -> {
               copyNote(event.note)
            }

            is HomeContract.Event.OnNotesDeleted -> {
                viewModelScope.launch(Dispatchers.IO) {
                    deleteNotes.invoke()
                    setState {
                        copy(notesState = HomeContract.NotesState.Idle)
                    }
                }
            }
        }
    }

    private fun copyNote(note: NoteDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            upsertNote.invoke(note.copy(id = 0, title = "${note.title} (Copy)", updatedAt = getFormattedTime()))
        }
    }

    private fun addNoteUseCase(note: NoteDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            upsertNote.invoke(note)
        }
    }

    private fun fetchNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            getNotes.invoke().collect() {
                if (it.isEmpty()) {
                    setState { copy(notesState = HomeContract.NotesState.Idle) }
                } else {
                    setState { copy(notesState = HomeContract.NotesState.Success(it)) }
                }
            }
        }
    }

    private fun deleteNote(noteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNote.invoke(noteId)
        }
    }

}