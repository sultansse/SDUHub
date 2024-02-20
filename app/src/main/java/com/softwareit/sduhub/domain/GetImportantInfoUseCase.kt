package com.softwareit.sduhub.domain

import com.softwareit.sduhub.data.repository.NetworkRepository
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfoDTO


class GetImportantInfoUseCase(
    private val repository: NetworkRepository,
) {
    suspend operator fun invoke(): ImportantInfoDTO? = repository.getImportantInfo()
}
