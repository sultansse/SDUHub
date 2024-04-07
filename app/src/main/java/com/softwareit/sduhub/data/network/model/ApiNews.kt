package com.softwareit.sduhub.data.network.model

import com.softwareit.sduhub.core.network.Mappable
import com.softwareit.sduhub.domain.model.NewsDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiNews(
    val id: String,
    val imageUrl: String,
    val title: String,
    val announce: String,
    val date: String,
    val link: String,
) : Mappable<NewsDTO> {

    override fun map() = NewsDTO(
        id = id,
        imageUrl = imageUrl,
        title = title,
        announce = announce,
        date = date,
        link = link,
    )
}
