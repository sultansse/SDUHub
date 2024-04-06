package com.softwareit.sduhub.domain.internship_usecase

import com.softwareit.sduhub.data.repository.NetworkRepository
import com.softwareit.sduhub.ui.screens.resources_screen.InternshipItemDTO

class GetSpecificInternshipUseCase(
    private val repository: NetworkRepository,
) {
    suspend operator fun invoke(id: Int): Result<InternshipItemDTO> {
        return repository.getSpecificInternship(id)
    }
}