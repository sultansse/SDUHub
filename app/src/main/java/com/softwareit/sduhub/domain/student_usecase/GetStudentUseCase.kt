package com.softwareit.sduhub.domain.student_usecase

import com.softwareit.sduhub.data.repository.NetworkRepository
import com.softwareit.sduhub.ui.model.StudentDIO

class GetStudentUseCase(
    private val repository: NetworkRepository,
) {
    suspend operator fun invoke(username: String, password: String): Result<StudentDIO> {
        return repository.getStudent(username, password).mapCatching { it.map() }
    }
}
