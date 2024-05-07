package com.softwareit.sduhub.ui.screens.profile_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.base.BaseViewModel
import com.softwareit.sduhub.domain.student_usecase.GetStudentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    private val getStudentUseCase: GetStudentUseCase,
) : BaseViewModel<ProfileScreenContract.Event, ProfileScreenContract.State, ProfileScreenContract.Effect>() {

    override fun setInitialState(): ProfileScreenContract.State {
        return ProfileScreenContract.State(
            authState = ProfileScreenContract.AuthState.Idle
        )
    }

    override fun handleEvent(event: ProfileScreenContract.Event) {
        when (event) {
            is ProfileScreenContract.Event.EmptyEffect -> {
                setEffect { ProfileScreenContract.Effect.Nothing }
            }

            is ProfileScreenContract.Event.OnStudentCardClick -> {
                setEffect { ProfileScreenContract.Effect.ShowStudentCardDialog(event.student) }
            }

            is ProfileScreenContract.Event.OnAuthClick -> {
                setEffect { ProfileScreenContract.Effect.ShowAuthDialog }
            }

            is ProfileScreenContract.Event.OnSubmitAuth -> {
                setEffect { ProfileScreenContract.Effect.Nothing }
                setState { copy(authState = ProfileScreenContract.AuthState.Loading)}
                fetchProfile(event.username, event.password)
            }

            is ProfileScreenContract.Event.OnLogoutClick -> {
                setEffect { ProfileScreenContract.Effect.Nothing }
            }
        }
    }

    private fun fetchProfile(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getStudentUseCase.invoke(username, password).fold(
                onSuccess = {
                    setState { copy(authState = ProfileScreenContract.AuthState.Success(it)) }
                },
                onFailure = {
                    setState { copy(authState = ProfileScreenContract.AuthState.Error(it)) }
                }
            )
        }
    }
}