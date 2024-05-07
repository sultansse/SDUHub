package com.softwareit.sduhub.domain.model

import com.softwareit.sduhub.core.network.Mappable
import com.softwareit.sduhub.ui.model.StudentDIO

data class StudentDTO(
    val fullname: String,
    val studentId: String,
    val faculty: String,
    val photoUrl: String? = null,
) : Mappable<StudentDIO> {

    override suspend fun map() = StudentDIO(
        fullname = fullname,
        studentId = studentId,
        faculty = faculty,
        photoUrl = photoUrl,
    )
}
