package com.softwareit.sduhub.domain.news_usecase

import com.softwareit.sduhub.data.network.backend.NewsItemDTO
import com.softwareit.sduhub.data.repository.NetworkRepository

/**
 *  UseCase is Responsible for combining data from different sources (repositories)
 */
class GetNewsUseCase(
    private val repository: NetworkRepository,
) {
    suspend operator fun invoke(): Result<List<NewsItemDTO>> {
        return repository.getNews()
    }
}