package com.softwareit.sduhub.ui.screens.resources_screen.internship_details_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.BaseViewModel
import com.softwareit.sduhub.domain.internship_usecase.GetSpecificInternshipUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class InternshipDetailsViewModel(
    private val getSpecificInternship: GetSpecificInternshipUseCase,
) : BaseViewModel<InternshipDetailsContract.Event, InternshipDetailsContract.State, InternshipDetailsContract.Effect>() {

    override fun setInitialState(): InternshipDetailsContract.State {
        return InternshipDetailsContract.State(
            internshipState = InternshipDetailsContract.InternShipState.Idle,
        )
    }

    override fun handleEvent(event: InternshipDetailsContract.Event) {
        when (event) {
            is InternshipDetailsContract.Event.OnFetchInternship -> {
                fetchCurrentInternship(event.id)
            }
        }
    }

    private fun fetchCurrentInternship(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getSpecificInternship.invoke(id).let {
                setState { copy(internshipState = InternshipDetailsContract.InternShipState.Success(it)) }
            }
        }
    }

}