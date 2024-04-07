package com.softwareit.sduhub.domain.news_usecase

import com.google.common.collect.ImmutableList
import com.softwareit.sduhub.data.repository.NetworkRepository
import com.softwareit.sduhub.ui.model.NewsDIO

/**
 *  UseCase is Responsible for combining data from different sources (repositories)
 */
class GetNewsUseCase(
    private val repository: NetworkRepository,
) {
//    TODO perform optimization of mapping
    suspend operator fun invoke(): Result<ImmutableList<NewsDIO>> {
        return repository.getNews().map { newsDTOList ->
            ImmutableList.copyOf(
                newsDTOList.map { newsDTO ->
                    newsDTO.map()
                }
            )
        }
    }
}