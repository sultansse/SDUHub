package com.softwareit.sduhub.domain.internship_usecase

import com.softwareit.sduhub.data.repository.NetworkRepository

class GetSpecificInternshipUseCase(
    private val repository: NetworkRepository,
) {
    suspend operator fun invoke(id: Int) = repository.getSpecificInternship(id)
}