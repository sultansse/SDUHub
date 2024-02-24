package com.softwareit.sduhub.di

import android.content.Context
import androidx.room.Room
import com.softwareit.sduhub.data.local.notes.NotesDatabase
import com.softwareit.sduhub.utils.Constants.Companion.NOTE_TABLE


//db
fun provideNoteDao(appDatabase: NotesDatabase) = appDatabase.noteDao()
fun provideAppDatabase(context: Context): NotesDatabase {
    return Room.databaseBuilder(
        context,
        NotesDatabase::class.java,
        NOTE_TABLE
    ).build()
}
