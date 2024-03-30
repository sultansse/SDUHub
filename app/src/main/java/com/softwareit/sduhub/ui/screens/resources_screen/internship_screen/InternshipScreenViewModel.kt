package com.softwareit.sduhub.ui.screens.resources_screen.internship_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.BaseViewModel
import com.softwareit.sduhub.domain.internship_usecase.GetSpecificInternshipUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class InternshipScreenViewModel(
    private val getSpecificInternship: GetSpecificInternshipUseCase,
) : BaseViewModel<InternshipContract.Event, InternshipContract.State, InternshipContract.Effect>() {

    override fun setInitialState(): InternshipContract.State {
        return InternshipContract.State(
            internshipState = InternshipContract.InternShipState.Idle,
        )
    }

    override fun handleEvent(event: InternshipContract.Event) {
        when (event) {
            is InternshipContract.Event.OnFetchInternship -> {
                fetchCurrentInternship(event.id)
            }
        }
    }

    private fun fetchCurrentInternship(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getSpecificInternship.invoke(id).let {
                setState { copy(internshipState = InternshipContract.InternShipState.Success(it)) }
            }
        }
    }

}