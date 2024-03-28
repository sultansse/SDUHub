package com.softwareit.sduhub.ui.screens.resources_screen.news_screen

import com.github.terrakok.cicerone.Router
import com.softwareit.sduhub.core.BaseViewModel


class NewsScreenViewModel(
    private val router: Router,
) : BaseViewModel<NewsContract.Event, NewsContract.State, NewsContract.Effect>() {

    fun onBackPressed() {
        router.exit()
    }

    override fun setInitialState(): NewsContract.State {
        return NewsContract.State(
            newsState = NewsContract.NewsState.Idle,
        )
    }

    override fun handleEvent(event: NewsContract.Event) {

    }

}