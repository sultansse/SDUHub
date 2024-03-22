package com.softwareit.sduhub.data.repository

import android.util.Log
import com.softwareit.sduhub.data.network.backend.BackendDataSource
import com.softwareit.sduhub.data.network.backend.Student
import com.softwareit.sduhub.data.network.firebase.FirebaseDataSource
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfoDTO

interface NetworkRepository {

    suspend fun getImportantInfo(): ImportantInfoDTO?

    suspend fun getStudent(): Student
}

//its better to have different repositories for each data source
class NetworkRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource,
    private val backendDataSource: BackendDataSource,
) : NetworkRepository {

    override suspend fun getImportantInfo(): ImportantInfoDTO? {
        return firebaseDataSource.getImportantInfo()
    }

    override suspend fun getStudent(): Student {
        val data = backendDataSource.getStudent()
        Log.e("TAG", ">>>> NetworkRepository.kt -> getStudent (27): $data");
        return data
    }

}
