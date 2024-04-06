package com.softwareit.sduhub.ui.screens.resources_screen.news_screen

import com.softwareit.sduhub.core.base.BaseViewModel


class NewsDetailsViewModel : BaseViewModel<NewsDetailsContract.Event, NewsDetailsContract.State, NewsDetailsContract.Effect>() {

    override fun setInitialState(): NewsDetailsContract.State {
        return NewsDetailsContract.State(
            newsState = NewsDetailsContract.NewsState.Idle,
        )
    }

    override fun handleEvent(event: NewsDetailsContract.Event) {

    }

}