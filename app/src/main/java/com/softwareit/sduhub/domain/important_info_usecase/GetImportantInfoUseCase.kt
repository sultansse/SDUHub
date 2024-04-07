package com.softwareit.sduhub.domain.important_info_usecase

import com.softwareit.sduhub.data.repository.NetworkRepository
import com.softwareit.sduhub.ui.model.ImportantInfoDIO


class GetImportantInfoUseCase(
    private val repository: NetworkRepository,
) {
    suspend operator fun invoke(): Result<ImportantInfoDIO> {
        return repository.getImportantInfo().map { importantInfoDTO ->
            importantInfoDTO.map()
        }
    }
}
