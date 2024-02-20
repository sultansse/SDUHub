package com.softwareit.sduhub.di

import android.content.Context
import androidx.room.Room
import com.softwareit.sduhub.common.utils.Constants.Companion.NOTE_TABLE
import com.softwareit.sduhub.data.local.notes.NotesDatabase


//db
fun provideNoteDao(appDatabase: NotesDatabase) = appDatabase.noteDao()
fun provideAppDatabase(context: Context): NotesDatabase {
    return Room.databaseBuilder(
        context,
        NotesDatabase::class.java,
        NOTE_TABLE
    ).build()
}
