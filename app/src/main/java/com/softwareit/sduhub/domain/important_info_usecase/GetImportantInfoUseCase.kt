package com.softwareit.sduhub.domain.important_info_usecase

import com.softwareit.sduhub.data.repository.NetworkRepository
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfoDTO


class GetImportantInfoUseCase(
    private val repository: NetworkRepository,
) {
    suspend operator fun invoke(): ImportantInfoDTO? = repository.getImportantInfo()
}
