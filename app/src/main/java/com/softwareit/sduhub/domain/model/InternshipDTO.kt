package com.softwareit.sduhub.domain.model

import com.softwareit.sduhub.core.network.Mappable
import com.softwareit.sduhub.ui.model.InternshipDIO

data class InternshipDTO(
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
) : Mappable<InternshipDIO> {

    override fun map() = InternshipDIO(
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
        contacts = contacts,
    )
}
