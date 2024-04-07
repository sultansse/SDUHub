package com.softwareit.sduhub.domain.internship_usecase

import com.google.common.collect.ImmutableList
import com.softwareit.sduhub.data.repository.NetworkRepository
import com.softwareit.sduhub.ui.model.InternshipDIO

class GetInternshipsUseCase(
    private val repository: NetworkRepository,
) {
    suspend operator fun invoke(): Result<ImmutableList<InternshipDIO>> {
        return repository.getInternships().map { internshipsDTOList ->
            ImmutableList.copyOf(
                internshipsDTOList.map { internshipDTO ->
                    internshipDTO.map()
                }
            )
        }
    }
//
//    suspend operator fun invoke(): Result<ImmutableList<NewsDIO>> {
//        return repository.getNews().map { newsDTOList ->
//            ImmutableList.copyOf(
//                newsDTOList.map { newsDTO ->
//                    newsDTO.map()
//                }
//            )
//        }
//    }
}