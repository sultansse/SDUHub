package com.softwareit.sduhub.data.repository

import com.softwareit.sduhub.data.local.room.notes.NoteDBO
import com.softwareit.sduhub.data.local.room.notes.NoteDao
import kotlinx.coroutines.flow.Flow


interface NotesRepository {

    fun getNoteById(id: Int): NoteDBO

    fun getNotes(): Flow<List<NoteDBO>>

    suspend fun upsertNote(note: NoteDBO)

    suspend fun deleteNote(id: Int)

    suspend fun deleteNotes()
}

class NotesRepositoryImpl(
    private val noteDao: NoteDao,
) : NotesRepository {

    override fun getNoteById(id: Int): NoteDBO {
        return noteDao.getNote(id)
    }

    override fun getNotes(): Flow<List<NoteDBO>> {
        return noteDao.getNotes()
    }

    override suspend fun upsertNote(note: NoteDBO) {
        noteDao.upsertNote(note)
    }

    override suspend fun deleteNote(id: Int) {
        noteDao.deleteNote(id)
    }

    override suspend fun deleteNotes() {
        noteDao.deleteNotes()
    }
}