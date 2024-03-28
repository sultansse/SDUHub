package com.softwareit.sduhub.domain.internship_usecase

import com.softwareit.sduhub.data.repository.NetworkRepository
import com.softwareit.sduhub.ui.screens.resources_screen.InternshipItemDTO

class GetInternshipsUseCase(
    private val repository: NetworkRepository,
) {
    suspend operator fun invoke(): List<InternshipItemDTO>? = repository.getInternship()
}