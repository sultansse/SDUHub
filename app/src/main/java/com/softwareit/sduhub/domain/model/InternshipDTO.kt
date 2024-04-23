package com.softwareit.sduhub.domain.model

import com.softwareit.sduhub.core.network.Mappable
import com.softwareit.sduhub.ui.model.InternshipDIO

data class InternshipDTO(
    val id: Int,
    val title: String,
    val salary: String? = null,
    val timeFormat: String? = null,
    val placeFormat: String? = null,
    val duration: String? = null,
    val company: String,
    val location: String? = null,
    val applyDeadline: String? = null,
    val description: String,
    val contacts: String,
    val contactsLink : String?
) : Mappable<InternshipDIO> {

    override suspend fun map() = InternshipDIO(
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
        contactsLink = contactsLink,
    )
}
