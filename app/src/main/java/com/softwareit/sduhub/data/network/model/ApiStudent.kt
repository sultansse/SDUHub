package com.softwareit.sduhub.data.network.model

import com.softwareit.sduhub.core.network.Mappable
import com.softwareit.sduhub.domain.model.StudentDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiStudent(
    val fullname: String,
    val studentId: Int,
    val faculty: String,
) : Mappable<StudentDTO> {

    override suspend fun map() = StudentDTO(
        fullname = fullname,
        studentId = studentId,
        faculty = faculty
    )
}
