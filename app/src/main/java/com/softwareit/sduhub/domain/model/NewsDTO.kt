package com.softwareit.sduhub.domain.model

import com.softwareit.sduhub.core.network.Mappable
import com.softwareit.sduhub.ui.model.NewsDIO

data class NewsDTO(
    val id: String,
    val imageUrl: String,
    val title: String,
    val announce: String,
    val date: String,
    val link: String,
) : Mappable<NewsDIO> {

    override fun map() = NewsDIO(
        id = id,
        imageUrl = imageUrl,
        title = title,
        announce = announce,
        date = date,
        link = link
    )
}
