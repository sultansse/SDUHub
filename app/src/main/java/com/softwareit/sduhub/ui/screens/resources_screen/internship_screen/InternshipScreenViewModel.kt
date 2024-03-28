package com.softwareit.sduhub.ui.screens.resources_screen.internship_screen

import com.softwareit.sduhub.core.BaseViewModel
import com.softwareit.sduhub.domain.internship_usecase.GetInternshipsUseCase


class InternshipScreenViewModel(
    private val getInternShipUseCase: GetInternshipsUseCase,
) : BaseViewModel<InternshipContract.Event, InternshipContract.State, InternshipContract.Effect>() {

    override fun setInitialState(): InternshipContract.State {
        return InternshipContract.State(
            internshipState = InternshipContract.InternShipState.Idle,
        )
    }

    override fun handleEvent(event: InternshipContract.Event) {
        when (event) {
            is InternshipContract.Event.OnFetchInternship -> {
//                getInternShipUseCase(
//                    onSuccess = {
//                        setState { copy(internshipState = InternshipContract.InternShipState.Success(it)) }
//                    },
//                    onError = {
//                        setEffect { InternshipContract.Effect.ShowError(it.message) }
//                    }
//                )
            }
        }
    }

}