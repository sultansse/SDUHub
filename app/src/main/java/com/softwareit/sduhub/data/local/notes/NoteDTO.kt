package com.softwareit.sduhub.data.local.notes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.softwareit.sduhub.common.utils.Constants.Companion.NOTE_TABLE
import com.softwareit.sduhub.common.utils.empty

@Entity(tableName = NOTE_TABLE)
data class NoteDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val notifyTime: String? = String.empty,
)