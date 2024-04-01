package com.softwareit.sduhub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.softwareit.sduhub.data.local.faq.FaqDBO
import com.softwareit.sduhub.data.local.faq.FaqDao
import com.softwareit.sduhub.data.local.notes.NoteDBO
import com.softwareit.sduhub.data.local.notes.NoteDao


@Database(
    entities = [NoteDBO::class, FaqDBO::class] ,
    version = 1,
    exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    abstract fun faqDao(): FaqDao
}