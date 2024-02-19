package com.softwareit.sduhub.data.local.notes

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [NoteDTO::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}