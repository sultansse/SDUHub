package com.softwareit.sduhub.ui.screens.profile_screen

import com.softwareit.sduhub.core.base.UiEffect
import com.softwareit.sduhub.core.base.UiEvent
import com.softwareit.sduhub.core.base.UiState
import com.softwareit.sduhub.ui.model.StudentDIO

class ProfileScreenContract {

    sealed class Event : UiEvent {
        data object EmptyEffect : Event()
        data class OnStudentCardClick(val student: StudentDIO) : Event()
        data object OnAuthClick : Event()
        data object OnLogoutClick : Event()
        data class OnSubmitAuth(val username : String, val password : String) : Event()
    }

    data class State(
        val authState: AuthState,
    ) : UiState

    sealed class Effect : UiEffect {
        data object Nothing : Effect()
        data class ShowStudentCardDialog(val student: StudentDIO) : Effect()
        data object ShowAuthDialog : Effect()
    }

    sealed class AuthState {
        data object Idle : AuthState()
        data object Loading : AuthState()
        data class Error(val exception: Throwable) : AuthState()
        data class Success(val student: StudentDIO) : AuthState()
    }

}