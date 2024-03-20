package com.softwareit.sduhub.data.network.backend

class BackendDataSource(
    private val backendApi: BackendService
) {

    suspend fun getStudent(): Student {
//        use safeApiCall
        return backendApi.getStudent()
    }
}