package com.softwareit.sduhub.ui.screens.resources_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.base.BaseViewModel
import com.softwareit.sduhub.domain.internship_usecase.GetInternshipsUseCase
import com.softwareit.sduhub.domain.news_usecase.GetNewsUseCase
import com.softwareit.sduhub.ui.screens.resources_screen.components.ResourceTab
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ResourceScreenViewModel(
    private val getNewsUseCase: GetNewsUseCase,
    private val getInternshipsUseCase: GetInternshipsUseCase,
) : BaseViewModel<ResourceContract.Event, ResourceContract.State, ResourceContract.Effect>() {

    //    todo remove since its not according to the MVI architecture (single state)
    private val _selectedTab = MutableStateFlow(ResourceTab.INTERNSHIPS.page)
    val selectedTab: StateFlow<Int> = _selectedTab

    override fun setInitialState(): ResourceContract.State {
        return ResourceContract.State(
            internshipsState = ResourceContract.InternShipsState.Loading,
            newsState = ResourceContract.NewsState.Loading
        )
    }

    override fun handleEvent(event: ResourceContract.Event) {
        when (event) {
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
        viewModelScope.launch {
            getNewsUseCase.invoke().fold(
                onSuccess = { newsList ->
                    setState { copy(newsState = ResourceContract.NewsState.Success(newsList)) }
                },
                onFailure = { exception ->
                    setState { copy(newsState = ResourceContract.NewsState.Error(exception)) }
                }
            )
        }
    }

    private fun fetchInternships() {
        viewModelScope.launch {
            getInternshipsUseCase.invoke().fold(
                onSuccess = { internships ->
                    setState { copy(internshipsState = ResourceContract.InternShipsState.Success(internships)) }
                },
                onFailure = { exception ->
                    setState { copy(internshipsState = ResourceContract.InternShipsState.Error(exception)) }
                }
            )
        }
    }

}