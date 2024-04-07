package com.softwareit.sduhub.ui.screens.profile_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.data.network.backend.Student

class ProfileScreenContract {

    sealed class Event : UiEvent {
        data object OnAuthUser : Event()
        data class OnStudentCardClick(val student: Student) : Event()
        data object OnStudentCardDialogClose : Event()
    }

    data class State(
        val profileState: ProfileState,
    ) : UiState

    sealed class Effect : UiEffect {
        data object Nothing : Effect()
        data class ShowStudentCardDialog(val student: Student) : Effect()

    }

    sealed class ProfileState {
        data object Idle : ProfileState()
        data object Loading : ProfileState()
        data class Error(val exception: Throwable) : ProfileState()
        data class Success(val student: Student) : ProfileState()
    }

}