package com.softwareit.sduhub.data.repository

import com.softwareit.sduhub.data.network.firebase.ImportantInfoDao
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfoDTO

interface NetworkRepository {

    suspend fun getImportantInfo(): ImportantInfoDTO?
}

class NetworkRepositoryImpl(
    private val networkDataSource: ImportantInfoDao,
) : NetworkRepository {

    override suspend fun getImportantInfo(): ImportantInfoDTO? {
        return networkDataSource.getImportantInfo()
    }

}
