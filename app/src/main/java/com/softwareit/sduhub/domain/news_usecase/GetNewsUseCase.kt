package com.softwareit.sduhub.domain.news_usecase

import com.softwareit.sduhub.data.network.backend.NewsItemDTO
import com.softwareit.sduhub.data.repository.NetworkRepository

class GetNewsUseCase(
    private val repository: NetworkRepository,
) {
    //        return repository.getNews().getOrDefault(emptyList())

    suspend operator fun invoke(): Result<List<NewsItemDTO>> {

//        logic of combining data from different sources
        return repository.getNews()
    }
}