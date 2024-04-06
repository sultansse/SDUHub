package com.softwareit.sduhub.ui.screens.profile_screen.faq_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.base.BaseViewModel
import com.softwareit.sduhub.domain.faq_usecase.GetFaqItemsUseCase
import kotlinx.coroutines.launch

class FaqDetailsViewModel(
    private val getFaqItemsUseCase: GetFaqItemsUseCase,
) : BaseViewModel<FaqDetailsContract.Event, FaqDetailsContract.State, FaqDetailsContract.Effect>() {


    override fun setInitialState(): FaqDetailsContract.State {
        return FaqDetailsContract.State(
            faqState = FaqDetailsContract.FaqState.Loading,
        )
    }

    override fun handleEvent(event: FaqDetailsContract.Event) {
        when (event) {
            is FaqDetailsContract.Event.OnFetchFaqItems -> {
                fetchFaqItems()
            }
        }
    }

    private fun fetchFaqItems() {
        viewModelScope.launch {
            getFaqItemsUseCase.invoke().fold(
                onSuccess = { faqItems ->
                    setState { copy(faqState = FaqDetailsContract.FaqState.Success(faqItems)) }
                },
                onFailure = { exception ->
                    setState { copy(faqState = FaqDetailsContract.FaqState.Error(exception)) }
                }
            )
        }
    }

}