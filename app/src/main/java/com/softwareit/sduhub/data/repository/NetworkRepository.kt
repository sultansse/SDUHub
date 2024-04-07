package com.softwareit.sduhub.data.repository

import com.softwareit.sduhub.data.network.backend.BackendDataSource
import com.softwareit.sduhub.data.network.firebase.FirebaseDataSource
import com.softwareit.sduhub.domain.model.ImportantInfoDTO
import com.softwareit.sduhub.domain.model.InternshipDTO
import com.softwareit.sduhub.domain.model.NewsDTO
import com.softwareit.sduhub.domain.model.StudentDTO

interface NetworkRepository {

    suspend fun getStudent(): Result<StudentDTO>

    suspend fun getImportantInfo(): Result<ImportantInfoDTO>

    suspend fun getInternships(): Result<List<InternshipDTO>>

    suspend fun getInternshipById(id: Int): Result<InternshipDTO>

    suspend fun getNews(): Result<List<NewsDTO>>
}

/**
 * Repository is responsible for single source of truth and caching
 */
class NetworkRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource,
    private val backendDataSource: BackendDataSource,
) : NetworkRepository {

//        logic of caching and converting Result to data

    override suspend fun getStudent(): Result<StudentDTO> {
        return backendDataSource.getStudent()
    }

    override suspend fun getImportantInfo(): Result<ImportantInfoDTO> {
        return firebaseDataSource.getImportantInfo()
    }

    override suspend fun getInternships(): Result<List<InternshipDTO>> {
        return backendDataSource.getInternships()
    }

    override suspend fun getInternshipById(id: Int): Result<InternshipDTO> {
        return backendDataSource.getInternshipById(id)
    }

    override suspend fun getNews(): Result<List<NewsDTO>> {

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
}
