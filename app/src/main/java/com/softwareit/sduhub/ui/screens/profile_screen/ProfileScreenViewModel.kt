package com.softwareit.sduhub.ui.screens.profile_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.base.BaseViewModel
import com.softwareit.sduhub.domain.student_usecase.GetStudentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    val getStudentUseCase: GetStudentUseCase,
) : BaseViewModel<ProfileScreenContract.Event, ProfileScreenContract.State, ProfileScreenContract.Effect>() {


    override fun setInitialState(): ProfileScreenContract.State {
        return ProfileScreenContract.State(
            profileState = ProfileScreenContract.ProfileState.Idle
        )
    }

    override fun handleEvent(event: ProfileScreenContract.Event) {
        when (event) {
            is ProfileScreenContract.Event.OnAuthUser -> {
                setEffect { ProfileScreenContract.Effect.UnavailableFeature }
                setState { copy(profileState = ProfileScreenContract.ProfileState.Loading) }
                fetchProfile()
            }

            is ProfileScreenContract.Event.OnStudentCardClick -> {
                setEffect { ProfileScreenContract.Effect.ShowStudentCardDialog(event.student) }
            }

            is ProfileScreenContract.Event.OnStudentCardDialogClose -> {
                setEffect { ProfileScreenContract.Effect.Nothing }
            }

            is ProfileScreenContract.Event.OnLogoutClick -> {
                setEffect { ProfileScreenContract.Effect.UnavailableFeature }
            }
        }
    }

    private fun fetchProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            getStudentUseCase.invoke().fold(
                onSuccess = {
                    setState { copy(profileState = ProfileScreenContract.ProfileState.Success(it)) }
                },
                onFailure = {
                    setState { copy(profileState = ProfileScreenContract.ProfileState.Error(it))}
                }
            )
        }
    }
}