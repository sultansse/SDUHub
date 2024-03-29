package com.softwareit.sduhub.data.repository

import com.softwareit.sduhub.data.network.backend.BackendDataSource
import com.softwareit.sduhub.data.network.backend.NewsItemDTO
import com.softwareit.sduhub.data.network.backend.Student
import com.softwareit.sduhub.data.network.firebase.FirebaseDataSource
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfoDTO
import com.softwareit.sduhub.ui.screens.resources_screen.InternshipItemDTO

interface NetworkRepository {

    suspend fun getStudent(): Student

    suspend fun getImportantInfo(): ImportantInfoDTO?

    suspend fun getInternships(): List<InternshipItemDTO>

    suspend fun getNews(): List<NewsItemDTO>

    suspend fun getNewsById(id: Int): NewsItemDTO
}

//its better to have different repositories for each data source
class NetworkRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource,
    private val backendDataSource: BackendDataSource,
) : NetworkRepository {

    override suspend fun getStudent(): Student {
        return backendDataSource.getStudent()
    }

    override suspend fun getImportantInfo(): ImportantInfoDTO? {
        return firebaseDataSource.getImportantInfo()
    }

    override suspend fun getInternships(): List<InternshipItemDTO> {
//        return firebaseDataSource.getInternship()
        return listOf(
            InternshipItemDTO(0, "Internship 0", description = "Description 0"),
            InternshipItemDTO(1, "Internship 1", description = "Description 1"),
            InternshipItemDTO(2, "Internship 2", description = "Description 2"),
            InternshipItemDTO(3, "Internship 3", description = "Description 3"),
            InternshipItemDTO(4, "Internship 4", description = "Description 4"),
            InternshipItemDTO(5, "Internship 5", description = "Description 5"),
            InternshipItemDTO(6, "Internship 6", description = "Description 6"),
            InternshipItemDTO(7, "Internship 7", description = "Description 7"),
            InternshipItemDTO(8, "Internship 8", description = "Description 8"),
            InternshipItemDTO(9, "Internship 9", description = "Description 9"),
        )
    }

    override suspend fun getNews(): List<NewsItemDTO> {
        return backendDataSource.getNews()
    }

    override suspend fun getNewsById(id: Int): NewsItemDTO {
        return backendDataSource.getNewsById(id)
    }

}
