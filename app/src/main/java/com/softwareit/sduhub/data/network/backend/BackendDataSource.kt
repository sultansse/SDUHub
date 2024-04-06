package com.softwareit.sduhub.data.network.backend

import com.softwareit.sduhub.core.network.CoroutineCaller
import com.softwareit.sduhub.ui.screens.resources_screen.InternshipItemDTO

class BackendDataSource(
    private val backendApi: BackendService
) : CoroutineCaller {

//        logic of mapping data safely

    suspend fun getStudent(): Student {
        return backendApi.getStudent()
    }

    suspend fun getNews(): Result<List<NewsItemDTO>> {
        return apiCall { backendApi.getNews() }
    }

    suspend fun getNewsById(id: Int): NewsItemDTO {
        return backendApi.getNewsById(id)
    }

    suspend fun getInternships(): Result<List<InternshipItemDTO>> {
        return apiCall { backendApi.getInternships() }
    }

    suspend fun getInternshipById(id: Int): Result<InternshipItemDTO> {
        return apiCall { backendApi.getInternshipById(id) }
    }
}