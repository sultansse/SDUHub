package com.softwareit.sduhub.ui.screens.resources_screen

import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.softwareit.sduhub.core.BaseViewModel
import com.softwareit.sduhub.domain.internship_usecase.GetInternshipsUseCase
import com.softwareit.sduhub.domain.news_usecase.GetNewsUseCase
import com.softwareit.sduhub.navigation.NavigationScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.immutableListOf


class ResourceScreenViewModel(
    private val router: Router,
    private val getNewsUseCase: GetNewsUseCase,
    private val getInternshipsUseCase: GetInternshipsUseCase,
) : BaseViewModel<ResourceContract.Event, ResourceContract.State, ResourceContract.Effect>() {

    fun onNewsClick(id: String, link: String) {
        router.navigateTo(NavigationScreens.Resources.newResource(id, link))
    }
    fun onInternshipClick(id: Int) {
        router.navigateTo(NavigationScreens.Resources.internshipResource(id))
    }

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
                val internships = immutableListOf(
                    InternshipItemDTO(0,"Internship 1", "Description 1"),
                    InternshipItemDTO(1,"Internship 2", "Description 2"),
                    InternshipItemDTO(2,"Internship 3", "Description 3"),
                    InternshipItemDTO(3,"Internship 4", "Description 4"),
                    InternshipItemDTO(4,"Internship 5", "Description 5"),
                    InternshipItemDTO(5,"Internship 1", "Description 1"),
                    InternshipItemDTO(6,"Internship 2", "Description 2"),
                    InternshipItemDTO(7,"Internship 3", "Description 3"),
                    InternshipItemDTO(8,"Internship 4", "Description 4"),
                    InternshipItemDTO(9,"Internship 5", "Description 5"),
                    InternshipItemDTO(0,"Internship 1", "Description 1"),
                    InternshipItemDTO(0,"Internship 2", "Description 2"),
                    InternshipItemDTO(0,"Internship 3", "Description 3"),
                    InternshipItemDTO(2,"Internship 4", "Description 4"),
                    InternshipItemDTO(2,"Internship 5", "Description 5"),
                    InternshipItemDTO(1,"Internship 1", "Description 1"),
                    InternshipItemDTO(1,"Internship 2", "Description 2"),
                    InternshipItemDTO(1,"Internship 3", "Description 3"),
                    InternshipItemDTO(1,"Internship 4", "Description 4"),
                    InternshipItemDTO(1,"Internship 5", "Description 5"),
                )
                setState { copy(internshipsState = ResourceContract.InternShipsState.Success(internships)) }
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

}