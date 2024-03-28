package com.softwareit.sduhub.domain.news_usecase

import com.softwareit.sduhub.data.network.backend.NewsItemDTO
import com.softwareit.sduhub.data.repository.NetworkRepository

class GetNewsUseCase(
    private val repository: NetworkRepository,
) {

    suspend operator fun invoke(): List<NewsItemDTO> = repository.getNews()
}