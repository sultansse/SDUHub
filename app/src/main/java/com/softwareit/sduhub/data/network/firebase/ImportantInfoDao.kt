package com.softwareit.sduhub.data.network.firebase

import com.google.firebase.database.FirebaseDatabase
import com.softwareit.sduhub.common.utils.Constants.Companion.IMPORTANT_INFO_TABLE
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfoDTO
import kotlinx.coroutines.tasks.await

class ImportantInfoDao(
    private val database: FirebaseDatabase,
) {

    private val ref = database.reference.child(IMPORTANT_INFO_TABLE)

    // TODO make better checking
    suspend fun getImportantInfo() : ImportantInfoDTO? {
        return try {
            val dataSnapshot = ref.get().await()
            val data = dataSnapshot.getValue(ImportantInfoDTO::class.java)
            data!!
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}