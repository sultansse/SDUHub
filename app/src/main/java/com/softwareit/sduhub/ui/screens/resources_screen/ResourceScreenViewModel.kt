package com.softwareit.sduhub.ui.screens.resources_screen

import androidx.lifecycle.viewModelScope
import com.softwareit.sduhub.core.base.BaseViewModel
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
) : BaseViewModel<ResourceScreenContract.Event, ResourceScreenContract.State, ResourceScreenContract.Effect>() {

    //    todo remove since its not according to the MVI architecture (single state)
    private val _selectedTab = MutableStateFlow(ResourceTab.INTERNSHIPS.page)
    val selectedTab: StateFlow<Int> = _selectedTab

    override fun setInitialState(): ResourceScreenContract.State {
        return ResourceScreenContract.State(
            internshipsState = ResourceScreenContract.InternShipsState.Loading,
            newsState = ResourceScreenContract.NewsState.Loading
        )
    }

    override fun handleEvent(event: ResourceScreenContract.Event) {
        when (event) {
            is ResourceScreenContract.Event.OnFetchInternships -> {
                fetchInternships()
            }

            is ResourceScreenContract.Event.OnFetchNews -> {
                fetchNews()
            }

            is ResourceScreenContract.Event.OnChangeTabIndex -> {
                _selectedTab.value = event.index
            }
        }
    }

    private fun fetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase.invoke().fold(
                onSuccess = { newsList ->
                    setState { copy(newsState = ResourceScreenContract.NewsState.Success(newsList)) }
                },
                onFailure = { exception ->
                    setState { copy(newsState = ResourceScreenContract.NewsState.Error(exception)) }
                }
            )
        }
    }

    private fun fetchInternships() {
        viewModelScope.launch(Dispatchers.IO) {
            getInternshipsUseCase.invoke().fold(
                onSuccess = { internships ->
                    setState { copy(internshipsState = ResourceScreenContract.InternShipsState.Success(internships)) }
                },
                onFailure = { exception ->
                    setState { copy(internshipsState = ResourceScreenContract.InternShipsState.Error(exception)) }
                }
            )
        }
    }

}