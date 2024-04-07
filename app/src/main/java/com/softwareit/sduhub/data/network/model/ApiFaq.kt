package com.softwareit.sduhub.data.network.model

import com.softwareit.sduhub.core.network.Mappable
import com.softwareit.sduhub.domain.model.FaqDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiFaq(
    val id: Int,
    val question: String,
    val answer: String,
) : Mappable<FaqDTO> {

    override fun map() = FaqDTO(
        id = id,
        question = question,
        answer = answer
    )
}