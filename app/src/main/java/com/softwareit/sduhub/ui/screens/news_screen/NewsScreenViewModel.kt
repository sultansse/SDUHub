package com.softwareit.sduhub.ui.screens.news_screen

import com.softwareit.sduhub.core.BaseViewModel


class NewsScreenViewModel(
//    private val router: Router,
) : BaseViewModel<NewsContract.Event, NewsContract.State, NewsContract.Effect>() {
    override fun setInitialState(): NewsContract.State {
        return NewsContract.State(
            internshipsState = NewsContract.InternShipsState.Idle,
            newsState = NewsContract.NewsState.Idle
        )
    }

    override fun handleEvent(event: NewsContract.Event) {
        //
    }

}