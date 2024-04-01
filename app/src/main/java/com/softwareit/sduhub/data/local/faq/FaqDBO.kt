package com.softwareit.sduhub.data.local.faq

import androidx.compose.runtime.Stable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.softwareit.sduhub.domain.faq_usecase.FaqDTO
import com.softwareit.sduhub.utils.Constants.Companion.FAQ_TABLE
import com.softwareit.sduhub.utils.Mappable

@Entity(tableName = FAQ_TABLE)
@Stable
data class FaqDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val question: String,
    val answer: String,
//    val tags: List<String>,
    val updatedAt: String,
) : Mappable<FaqDTO> {

    override fun map() = FaqDTO(
        id = id,
        question = question,
        answer = answer,
//        tags = tags
    )
}