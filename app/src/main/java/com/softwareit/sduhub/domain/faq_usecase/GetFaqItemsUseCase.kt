package com.softwareit.sduhub.domain.faq_usecase

import com.softwareit.sduhub.data.repository.FaqRepository
import com.squareup.moshi.JsonClass

class GetFaqItemsUseCase(
    private val repository: FaqRepository,
) {
    suspend operator fun invoke(): Result<List<FaqDTO>> = repository.getFaqItems()
}

@JsonClass(generateAdapter = true)
data class FaqDTO(
    val id: Int,
    val question: String,
    val answer: String,
)
