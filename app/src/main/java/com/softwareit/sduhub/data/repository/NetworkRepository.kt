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

    suspend fun getInternships(): Result<List<InternshipItemDTO>>

    suspend fun getSpecificInternship(id: Int): InternshipItemDTO

    suspend fun getNews(): Result<List<NewsItemDTO>>

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

    override suspend fun getInternships(): Result<List<InternshipItemDTO>> {
        return backendDataSource.getInternships()
    }

    override suspend fun getSpecificInternship(id: Int): InternshipItemDTO {
        return backendDataSource.getInternshipById(id)
    }

    override suspend fun getNews(): Result<List<NewsItemDTO>> {
//        logic of caching and converting Result to data
//        when (val result = backendDataSource.getNews()) {
//            is Result.Companion.S -> {
//                return save to local, return result by strategy (ignoreCache, invalidateCache, cacheOnly, etc.)
//            }
//            is Result.Error -> {
//                return local result
//            }
//        }
        return backendDataSource.getNews()
    }

    override suspend fun getNewsById(id: Int): NewsItemDTO {
        return backendDataSource.getNewsById(id)
    }

}
