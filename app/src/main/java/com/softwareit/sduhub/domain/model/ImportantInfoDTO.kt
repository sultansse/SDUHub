package com.softwareit.sduhub.domain.model

import com.softwareit.sduhub.core.network.Mappable
import com.softwareit.sduhub.ui.model.ImportantInfoDIO

data class ImportantInfoDTO(
    val title: String,
    val description: String,
    val tags: List<String>,
) : Mappable<ImportantInfoDIO> {

    override suspend fun map() = ImportantInfoDIO(
        title,
        description,
        tags,
    )
}