package com.softwareit.sduhub.ui.screens.profile_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.ui.model.StudentDIO

class ProfileScreenContract {

    sealed class Event : UiEvent {
        data object OnAuthUser : Event()
        data class OnStudentCardClick(val student: StudentDIO) : Event()
        data object OnStudentCardDialogClose : Event()
        data object OnLogoutClick : Event()
    }

    data class State(
        val profileState: ProfileState,
    ) : UiState

    sealed class Effect : UiEffect {
        data object Nothing : Effect()
        data object UnavailableFeature : Effect()
        data class ShowStudentCardDialog(val student: StudentDIO) : Effect()
    }

    sealed class ProfileState {
        data object Idle : ProfileState()
        data object Loading : ProfileState()
        data class Error(val exception: Throwable) : ProfileState()
        data class Success(val student: StudentDIO) : ProfileState()
    }

}