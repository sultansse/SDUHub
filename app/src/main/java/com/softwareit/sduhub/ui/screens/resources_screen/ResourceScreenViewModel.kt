package com.softwareit.sduhub.ui.screens.resources_screen

import com.github.terrakok.cicerone.Router
import com.softwareit.sduhub.core.BaseViewModel
import okhttp3.internal.immutableListOf


class ResourceScreenViewModel(
    private val router: Router,
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
                val internships = immutableListOf(
                    InternshipItemDTO("Internship 1", "Description 1"),
                    InternshipItemDTO("Internship 2", "Description 2"),
                    InternshipItemDTO("Internship 3", "Description 3"),
                    InternshipItemDTO("Internship 4", "Description 4"),
                    InternshipItemDTO("Internship 5", "Description 5"),
                    InternshipItemDTO("Internship 1", "Description 1"),
                    InternshipItemDTO("Internship 2", "Description 2"),
                    InternshipItemDTO("Internship 3", "Description 3"),
                    InternshipItemDTO("Internship 4", "Description 4"),
                    InternshipItemDTO("Internship 5", "Description 5"),
                    InternshipItemDTO("Internship 1", "Description 1"),
                    InternshipItemDTO("Internship 2", "Description 2"),
                    InternshipItemDTO("Internship 3", "Description 3"),
                    InternshipItemDTO("Internship 4", "Description 4"),
                    InternshipItemDTO("Internship 5", "Description 5"),
                    InternshipItemDTO("Internship 1", "Description 1"),
                    InternshipItemDTO("Internship 2", "Description 2"),
                    InternshipItemDTO("Internship 3", "Description 3"),
                    InternshipItemDTO("Internship 4", "Description 4"),
                    InternshipItemDTO("Internship 5", "Description 5"),
                )
                setState { copy(internshipsState = ResourceContract.InternShipsState.Success(internships)) }
            }
            is ResourceContract.Event.OnFetchNews -> {
                // fetch news
                val news = immutableListOf(
                    NewsItemDTO("News 1", "Description 1", ""),
                    NewsItemDTO("News 2", "Description 2", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 3", "Description 3", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 4", "Description 4", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 5", "Description 5", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 1", "Description 1", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 2", "Description 2", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 3", "Description 3", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 4", "Description 4", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 5", "Description 5", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 1", "Description 1", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 2", "Description 2", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 3", "Description 3", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 4", "Description 4", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),
                    NewsItemDTO("News 5", "Description 5", "https://yt3.googleusercontent.com/82UnWmIFlgRnWam4R5tqS3qv-MaawpGx0QFLSYM5mrABFO1_XyFF7GRJLxToIU-gD9i4K_fc_w=s900-c-k-c0x00ffffff-no-rj"),

                    )

                setState { copy(newsState = ResourceContract.NewsState.Success(news)) }
            }
            is ResourceContract.Event.OnSearch -> {
                // search
            }
        }
    }

}