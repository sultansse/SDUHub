package com.softwareit.sduhub.ui.screens.profile_screen.faq_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.BaseViewModel
import com.softwareit.sduhub.domain.faq_usecase.FaqDTO
import com.softwareit.sduhub.domain.faq_usecase.GetFaqItemsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FaqDetailsViewModel(
    private val getFaqItems: GetFaqItemsUseCase,
) : BaseViewModel<FaqDetailsContract.Event, FaqDetailsContract.State, FaqDetailsContract.Effect>() {


    override fun setInitialState(): FaqDetailsContract.State {
        return FaqDetailsContract.State(
            faqState = FaqDetailsContract.FaqState.Idle,
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
                FaqDTO(
                    5,
                    "How can I contact SDUHUB?",
                    "You can contact SDUHUB by sending an email to sdu@sdu.edu.kz.",
                ),
                FaqDTO(
                    6,
                    "How can I find resources?",
                    "You can find resources by going to the Resources tab.",
                ),
                FaqDTO(
                    7,
                    "How can I find internships?",
                    "You can find internships by going to the Internships tab.",
                ),
                FaqDTO(
                    8,
                    "What is SDUHUB?",
                    "SDUHUB is a platform for students to find resources and internships.",
                ),
                FaqDTO(
                    9,
                    "How can I find internships?",
                    "You can find internships by going to the Internships tab.",
                ),
                FaqDTO(
                    10,
                    "How can I find resources?",
                    "You can find resources by going to the Resources tab.",
                ),
                FaqDTO(
                    11,
                    "How can I contact SDUHUB?",
                    "You can contact SDUHUB by sending an email to",
                ),
                FaqDTO(
                    12,
                    "How can I contact SDUHUB?",
                    "You can contact SDUHUB by sending an email to",
                ),
                FaqDTO(
                    13,
                    "How can I find resources?",
                    "You can find resources by going to the Resources tab.",
                ),
                FaqDTO(
                    14,
                    "How can I find internships?",
                    "You can find internships by going to the Internships tab.",
                ),
                FaqDTO(
                    15,
                    "What is SDUHUB?",
                    "SDUHUB is a platform for students to find resources and internships.",
                ),
                FaqDTO(
                    16,
                    "How can I find internships?",
                    "You can find internships by going to the Internships tab.",
                ),

            )
            setState { copy(faqState = FaqDetailsContract.FaqState.Fetched(faqItems))}
        }
    }

}