package com.softwareit.sduhub.ui.screens.home_screen

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.domain.AddNoteUseCase
import com.softwareit.sduhub.domain.DeleteNotesUseCase
import com.softwareit.sduhub.domain.GetImportantInfoUseCase
import com.softwareit.sduhub.domain.GetNotesUseCase
import com.softwareit.sduhub.ui.base.BaseViewModel
import com.softwareit.sduhub.ui.navigation.NavigationScreens
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfoDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeScreenViewModel(
    private val router: Router,
    private val getNotes: GetNotesUseCase,
    private val addNote: AddNoteUseCase,
    private val deleteNotes: DeleteNotesUseCase,
    private val getImportantInfo: GetImportantInfoUseCase,
) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    /* Navigation functions */
    fun onBackPressed() = router.exit()
    fun goToCategory() = router.navigateTo(NavigationScreens.category())

    private val _data = MutableStateFlow<ImportantInfoDTO?>(null)
    val data: StateFlow<ImportantInfoDTO?> = _data

//    val db = database.reference.child("important_info_table")

//    fun setData(data: ImportantInfoDTO) {
//        db.setValue(data)
//    }

    init {
        fetchImportantInfo()
        fetchNotes()
    }

    private fun fetchImportantInfo() {
        viewModelScope.launch {
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

            HomeContract.Event.OnNotesDeleted -> {
                viewModelScope.launch {
                    deleteNotes()
                    setState {
                        copy(notesState = HomeContract.NotesState.Idle)
                    }
                }
            }
        }
    }

    private fun addNoteUseCase(note: NoteDTO) {
        viewModelScope.launch {
            addNote(note)
        }
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            getNotes().collect() {
                if (it.isEmpty()) {
                    setState { copy(notesState = HomeContract.NotesState.Idle) }
                } else {
                    setState { copy(notesState = HomeContract.NotesState.Success(it)) }
                }
            }
        }
    }

}