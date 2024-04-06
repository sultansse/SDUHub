package com.softwareit.sduhub.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.softwareit.sduhub.data.local.room.notes.NoteDBO
import com.softwareit.sduhub.data.local.room.notes.NoteDao


@Database(
    entities = [NoteDBO::class] ,
    version = 1,
    exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}