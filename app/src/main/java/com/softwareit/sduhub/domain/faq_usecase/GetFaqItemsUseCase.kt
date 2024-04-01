package com.softwareit.sduhub.domain.faq_usecase

import com.softwareit.sduhub.data.local.faq.FaqDBO
import com.softwareit.sduhub.data.repository.FaqRepository
import kotlinx.coroutines.flow.Flow

class GetFaqItemsUseCase(
    private val repository: FaqRepository,
) {
    operator fun invoke(): Flow<List<FaqDBO>> = repository.getFaqItems()
}

data class FaqDTO(
    val id: Int,
    val question: String,
    val answer: String,
//    val tags: List<String>
)
