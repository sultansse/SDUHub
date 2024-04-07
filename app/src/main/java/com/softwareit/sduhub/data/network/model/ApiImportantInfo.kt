package com.softwareit.sduhub.data.network.model

import com.softwareit.sduhub.core.network.Mappable
import com.softwareit.sduhub.domain.model.ImportantInfoDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiImportantInfo(
    val title: String,
    val description: String,
    val tags: List<String>,
) : Mappable<ImportantInfoDTO> {
//    empty must have for firebase to work
    constructor() : this("", "", emptyList())

    override fun map() = ImportantInfoDTO(
        title,
        description,
        tags
    )
}