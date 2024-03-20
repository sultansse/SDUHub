package com.softwareit.sduhub.data.repository

import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.data.local.notes.NoteDao
import kotlinx.coroutines.flow.Flow


interface NotesRepository {

    fun getNote(id: Int): Flow<NoteDTO>

    fun getNotes(): Flow<List<NoteDTO>>

    suspend fun upsertNote(note: NoteDTO)

    suspend fun deleteNote(id: Int)

    suspend fun deleteNotes()
}

class NotesRepositoryImpl(
    private val noteDao: NoteDao,
) : NotesRepository {

    override fun getNote(id: Int): Flow<NoteDTO> {
        return noteDao.getNote(id)
    }

    override fun getNotes(): Flow<List<NoteDTO>> {
        return noteDao.getNotes()
    }

    override suspend fun upsertNote(note: NoteDTO) {
        noteDao.upsertNote(note)
    }

    override suspend fun deleteNote(id: Int) {
        noteDao.deleteNote(id)
    }

    override suspend fun deleteNotes() {
        noteDao.deleteNotes()
    }
}