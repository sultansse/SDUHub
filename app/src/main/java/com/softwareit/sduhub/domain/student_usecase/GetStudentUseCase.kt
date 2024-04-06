package com.softwareit.sduhub.domain.student_usecase

import com.softwareit.sduhub.data.network.backend.Student
import com.softwareit.sduhub.data.repository.NetworkRepository

class GetStudentUseCase(
    private val repository: NetworkRepository,
) {
    suspend operator fun invoke(): Result<Student> = repository.getStudent()
}
