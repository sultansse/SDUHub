package com.softwareit.sduhub.data.network.model

import com.softwareit.sduhub.core.network.Mappable
import com.softwareit.sduhub.domain.model.InternshipDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiInternship(
    val id: Int,
    val title: String,
    val salary: String,
    val timeFormat: String,
    val placeFormat: String,
    val duration: String,
    val company: String,
    val location: String,
    val applyDeadline: String,
    val description: String,
    val contacts: String,
) : Mappable<InternshipDTO> {

    override suspend fun map() = InternshipDTO(
        id = id,
        title = title,
        salary = salary,
        timeFormat = timeFormat,
        placeFormat = placeFormat,
        duration = duration,
        company = company,
        location = location,
        applyDeadline = applyDeadline,
        description = description,
        contacts = contacts
    )
}
