package com.softwareit.sduhub.data.network.backend

import com.softwareit.sduhub.core.network.CoroutineCaller
import com.softwareit.sduhub.data.network.model.ApiUserCredential
import com.softwareit.sduhub.domain.model.FaqDTO
import com.softwareit.sduhub.domain.model.InternshipDTO
import com.softwareit.sduhub.domain.model.NewsDTO
import com.softwareit.sduhub.domain.model.StudentDTO

class BackendDataSource(
    private val backendApi: BackendService
) : CoroutineCaller {

    suspend fun getStudent(username: String, password: String): Result<StudentDTO> {
        val credentials = ApiUserCredential(username, password)
        return apiCall { backendApi.authStudent(credentials).map() }
    }

    suspend fun getNews(): Result<List<NewsDTO>> {
        return apiCall { backendApi.getNews().map { it.map() }  }
    }

    suspend fun getInternships(): Result<List<InternshipDTO>> {
        return apiCall { backendApi.getInternships().map { it.map() } }
    }

    suspend fun getInternshipById(id: Int): Result<InternshipDTO> {
        return apiCall { backendApi.getInternshipById(id).map() }
    }

    suspend fun getFaqItems(): Result<List<FaqDTO>> {
        return apiCall { backendApi.getFaqItems().map { it.map() } }
    }
}