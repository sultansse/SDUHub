package com.softwareit.sduhub.data.network.firebase

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.softwareit.sduhub.ui.screens.home_screen.components.ImportantInfoDTO
import kotlinx.coroutines.tasks.await

class ImportantInfoDao(
    private val database: FirebaseDatabase
) {

    private val ref = database.reference.child("important_info_table")

    suspend fun getImportantInfo() : ImportantInfoDTO? {
        try {
            val dataSnapshot = ref.get().await()
            val data = dataSnapshot.getValue(ImportantInfoDTO::class.java)
            Log.e("TAG", ">>>> ImportantInfoDao.kt -> getImportantInfo (17): $data");
            return data!!
//            setState { copy(importantInfoState = data) }
//            _data.emit(data) // Update the state with the fetched data
        } catch (e: Exception) {
            e.printStackTrace()

//            _data.emit(null) // In case of an error, emit null
        }
        return ImportantInfoDTO()
    }
}