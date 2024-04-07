package com.softwareit.sduhub.data.network.firebase

import com.google.firebase.database.FirebaseDatabase
import com.softwareit.sduhub.core.network.CoroutineCaller
import com.softwareit.sduhub.data.network.model.ApiImportantInfo
import com.softwareit.sduhub.domain.model.ImportantInfoDTO
import com.softwareit.sduhub.utils.Constants.Companion.IMPORTANT_INFO_TABLE
import kotlinx.coroutines.tasks.await

class FirebaseDataSource(
    private val database: FirebaseDatabase,
) : CoroutineCaller {

    private val refToImportant = database.reference.child(IMPORTANT_INFO_TABLE)

    // TODO make better checking
    suspend fun getImportantInfo(): Result<ImportantInfoDTO> {
        return apiCall {
            val dataSnapshot = refToImportant.get().await()
            dataSnapshot.getValue(ApiImportantInfo::class.java)!!.map()
        }
    }
}
