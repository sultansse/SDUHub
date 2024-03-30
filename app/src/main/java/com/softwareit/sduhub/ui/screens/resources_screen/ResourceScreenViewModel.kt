package com.softwareit.sduhub.ui.screens.resources_screen

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.softwareit.sduhub.core.BaseViewModel
import com.softwareit.sduhub.domain.internship_usecase.GetInternshipsUseCase
import com.softwareit.sduhub.domain.news_usecase.GetNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ResourceScreenViewModel(
    private val router: Router,
    private val getNewsUseCase: GetNewsUseCase,
    private val getInternshipsUseCase: GetInternshipsUseCase,
) : BaseViewModel<ResourceContract.Event, ResourceContract.State, ResourceContract.Effect>() {

    override fun setInitialState(): ResourceContract.State {
        return ResourceContract.State(
            internshipsState = ResourceContract.InternShipsState.Idle,
            newsState = ResourceContract.NewsState.Idle
        )
    }

    override fun handleEvent(event: ResourceContract.Event) {
        when(event) {
            is ResourceContract.Event.OnFetchInternships -> {
                // fetch internships
                fetchInternships()
            }
            is ResourceContract.Event.OnFetchNews -> {
                // fetch news
                fetchNews()
            }
            is ResourceContract.Event.OnSearch -> {
                // search
            }
        }
    }

    private fun fetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase.invoke().let {
                setState { copy(newsState = ResourceContract.NewsState.Success(it)) }
            }
        }
    }

    private fun fetchInternships() {
        viewModelScope.launch(Dispatchers.IO) {
            getInternshipsUseCase.invoke().let {
                setState { copy(internshipsState = ResourceContract.InternShipsState.Success(it)) }
            }
        }
    }

}