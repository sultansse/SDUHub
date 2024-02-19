package com.softwareit.sduhub.data.repository

import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.data.local.notes.NoteDao
import kotlinx.coroutines.flow.Flow


interface NotesRepository {

    fun getNote(id: Int): Flow<NoteDTO>

    fun getNotes(): Flow<List<NoteDTO>>

    suspend fun addNote(note: NoteDTO)

    suspend fun deleteNote(id: Int)

    suspend fun deleteNotes()
}

class NotesRepositoryImpl(
    private val localDataSource: NoteDao,
) : NotesRepository {

    override fun getNote(id: Int): Flow<NoteDTO> {
        return localDataSource.getNote(id)
    }

    override fun getNotes(): Flow<List<NoteDTO>> {
        return localDataSource.getNotes()
    }

    override suspend fun addNote(note: NoteDTO) {
        localDataSource.addNote(note)
    }

    override suspend fun deleteNote(id: Int) {
        localDataSource.deleteNote(id)
    }

    override suspend fun deleteNotes() {
        localDataSource.deleteNotes()
    }
}