package com.softwareit.sduhub.ui.screens.profile_screen.faq_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.BaseViewModel
import com.softwareit.sduhub.domain.faq_usecase.FaqDTO
import com.softwareit.sduhub.domain.faq_usecase.GetFaqItemsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FaqScreenViewModel(
    private val getFaqItems: GetFaqItemsUseCase,
) : BaseViewModel<FaqContract.Event, FaqContract.State, FaqContract.Effect>() {


    override fun setInitialState(): FaqContract.State {
        return FaqContract.State(
            faqState = FaqContract.FaqState.Idle,
        )
    }

    override fun handleEvent(event: FaqContract.Event) {
        when (event) {
            is FaqContract.Event.OnFetchFaqItems -> {
                fetchFaqItems()
            }
        }
    }

    private fun fetchFaqItems() {
        viewModelScope.launch(Dispatchers.IO) {
//            getFaqItems.invoke().collect() {
//                setState { copy(faqState = FaqContract.FaqState.Fetched(it)) }
//            }
            val faqItems = listOf(
                FaqDTO(
                    1,
                    "What is SDUHUB?",
                    "SDUHUB is a platform for students to find resources and internships.",
                ),
                FaqDTO(
                    2,
                    "How can I find internships?",
                    "You can find internships by going to the Internships tab.",
                ),
                FaqDTO(
                    3,
                    "How can I find resources?",
                    "You can find resources by going to the Resources tab.",
                ),
                FaqDTO(
                    4,
                    "How can I contact SDUHUB?",
                    "You can contact SDUHUB by sending an email to sdu@sdu.edu.kz.",
                ),
            )
            setState { copy(faqState = FaqContract.FaqState.Fetched(faqItems))}
        }
    }

}