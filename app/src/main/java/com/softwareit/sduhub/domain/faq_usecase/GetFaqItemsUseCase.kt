package com.softwareit.sduhub.domain.faq_usecase

import com.google.common.collect.ImmutableList
import com.softwareit.sduhub.data.repository.FaqRepository
import com.softwareit.sduhub.ui.model.FaqDIO

class GetFaqItemsUseCase(
    private val repository: FaqRepository,
) {
//    todo perform mapping optimization
    suspend operator fun invoke(): Result<ImmutableList<FaqDIO>> {
        return repository.getFaqItems().map { faqDtoList ->
            ImmutableList.copyOf(
                faqDtoList.map { faqDTO ->
                    faqDTO.map()
                }
            )
        }
    }
}
