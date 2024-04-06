package com.softwareit.sduhub.ui.screens.resources_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.BaseViewModel
import com.softwareit.sduhub.domain.internship_usecase.GetInternshipsUseCase
import com.softwareit.sduhub.domain.news_usecase.GetNewsUseCase
import com.softwareit.sduhub.ui.screens.resources_screen.components.ResourceTab
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ResourceScreenViewModel(
    private val getNewsUseCase: GetNewsUseCase,
    private val getInternshipsUseCase: GetInternshipsUseCase,
) : BaseViewModel<ResourceContract.Event, ResourceContract.State, ResourceContract.Effect>() {

    private val _selectedTab = MutableStateFlow(ResourceTab.INTERNSHIPS.page)
    val selectedTab: StateFlow<Int> = _selectedTab

    override fun setInitialState(): ResourceContract.State {
        return ResourceContract.State(
            internshipsState = ResourceContract.InternShipsState.Idle,
            newsState = ResourceContract.NewsState.Idle
        )
    }

    override fun handleEvent(event: ResourceContract.Event) {
        when(event) {
            is ResourceContract.Event.OnFetchInternships -> {
                fetchInternships()
            }
            is ResourceContract.Event.OnFetchNews -> {
                fetchNews()
            }
            is ResourceContract.Event.OnChangeTabIndex -> {
                _selectedTab.value = event.index
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