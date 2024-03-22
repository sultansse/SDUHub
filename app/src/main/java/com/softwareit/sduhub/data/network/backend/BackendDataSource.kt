package com.softwareit.sduhub.data.network.backend

import android.util.Log

class BackendDataSource(
    private val backendApi: BackendService
) {

    suspend fun getStudent(): Student {
//        use safeApiCall
        val data = backendApi.getStudent()
        Log.e("TAG", ">>>> BackendDataSource.kt -> getStudent (10): $data");
        return data
    }
}