package com.softwareit.sduhub.data.network.backend

import com.softwareit.sduhub.core.network.CoroutineCaller
import com.softwareit.sduhub.ui.screens.resources_screen.InternshipItemDTO

class BackendDataSource(
    private val backendApi: BackendService
) : CoroutineCaller {

    suspend fun getStudent(): Student {
//        use safeApiCall
        return backendApi.getStudent()
    }

    suspend fun getNews(): Result<List<NewsItemDTO>> {
//        logic of mapping data safely
        return apiCall { backendApi.getNews() }
    }

    suspend fun getNewsById(id: Int): NewsItemDTO {
//        use safeApiCall
        return backendApi.getNewsById(id)
    }

    suspend fun getInternships(): Result<List<InternshipItemDTO>> {
        return apiCall { backendApi.getInternships() }
    }

    suspend fun getInternshipById(id: Int): InternshipItemDTO {
        return backendApi.getInternshipById(id)
    }
}