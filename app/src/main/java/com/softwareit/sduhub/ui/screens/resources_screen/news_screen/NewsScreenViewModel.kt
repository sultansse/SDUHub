package com.softwareit.sduhub.ui.screens.resources_screen.news_screen

import com.softwareit.sduhub.core.BaseViewModel


class NewsScreenViewModel : BaseViewModel<NewsContract.Event, NewsContract.State, NewsContract.Effect>() {

    override fun setInitialState(): NewsContract.State {
        return NewsContract.State(
            newsState = NewsContract.NewsState.Idle,
        )
    }

    override fun handleEvent(event: NewsContract.Event) {

    }

}