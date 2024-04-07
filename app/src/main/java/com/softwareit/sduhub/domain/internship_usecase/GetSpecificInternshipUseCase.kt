package com.softwareit.sduhub.domain.internship_usecase

import com.softwareit.sduhub.data.repository.NetworkRepository
import com.softwareit.sduhub.ui.model.InternshipDIO

class GetSpecificInternshipUseCase(
    private val repository: NetworkRepository,
) {
    suspend operator fun invoke(id: Int): Result<InternshipDIO> {
        return repository.getInternshipById(id).map { internshipDTO ->
            internshipDTO.map()
        }
    }
}