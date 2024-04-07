package com.softwareit.sduhub.domain.model

import com.softwareit.sduhub.core.network.Mappable
import com.softwareit.sduhub.ui.model.FaqDIO

data class FaqDTO(
    val id: Int,
    val question: String,
    val answer: String,
) : Mappable<FaqDIO> {

    override fun map() = FaqDIO(
        id = id,
        question = question,
        answer = answer
    )
}