package com.softwareit.sduhub.ui.screens.profile_screen.faq_screen

import com.softwareit.sduhub.core.BaseViewModel

class FaqScreenViewModel : BaseViewModel<FaqContract.Event, FaqContract.State, FaqContract.Effect>() {


    override fun setInitialState(): FaqContract.State {
        return FaqContract.State(
            noteState = FaqContract.FaqState.Idle,
        )
    }

    override fun handleEvent(event: FaqContract.Event) {
        when (event) {
            is FaqContract.Event.OnFetchNote -> {
            }

            is FaqContract.Event.OnDeleteNote -> {
            }

            is FaqContract.Event.OnTitleChanged -> {
            }

            is FaqContract.Event.OnDescriptionChanged -> {
            }

        }
    }

}