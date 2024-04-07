package com.softwareit.sduhub.data.local.room.notes

import androidx.compose.runtime.Stable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.softwareit.sduhub.utils.Constants.Companion.NOTE_TABLE
import com.softwareit.sduhub.utils.empty

@Entity(tableName = NOTE_TABLE)
@Stable
data class NoteDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val updatedAt: String,
    val notifyTime: String? = String.empty,
)