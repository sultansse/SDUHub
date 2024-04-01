package com.softwareit.sduhub.data.local.faq

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FaqDao {

    @Query("SELECT * FROM FAQ_TABLE ORDER BY updatedAt DESC")
    fun getFaqItems(): Flow<List<FaqDBO>>
}