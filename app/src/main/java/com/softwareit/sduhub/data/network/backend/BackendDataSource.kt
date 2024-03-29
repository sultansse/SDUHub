package com.softwareit.sduhub.data.network.backend

import com.softwareit.sduhub.ui.screens.resources_screen.InternshipItemDTO

class BackendDataSource(
    private val backendApi: BackendService
) {

    suspend fun getStudent(): Student {
//        use safeApiCall
        return backendApi.getStudent()
    }

    suspend fun getNews(): List<NewsItemDTO> {
//        use safeApiCall
        return backendApi.getNews()
    }

    suspend fun getNewsById(id: Int): NewsItemDTO {
//        use safeApiCall
        return backendApi.getNewsById(id)
    }

    suspend fun getInternships(): List<InternshipItemDTO> {
        return backendApi.getInternships()
    }

    suspend fun getInternshipById(id: Int): InternshipItemDTO {
        return backendApi.getInternshipById(id)
    }
}