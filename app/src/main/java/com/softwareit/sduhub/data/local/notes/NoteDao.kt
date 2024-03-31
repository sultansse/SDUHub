package com.softwareit.sduhub.data.local.notes

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM NOTES_TABLE WHERE id = :id")
    fun getNote(id: Int): NoteDTO

    @Query("SELECT * FROM NOTES_TABLE ORDER BY updatedAt DESC")
    fun getNotes(): Flow<List<NoteDTO>>

    @Upsert
    suspend fun upsertNote(note: NoteDTO)

    @Query("DELETE FROM NOTES_TABLE WHERE id = :id")
    suspend fun deleteNote(id: Int)

    @Query("DELETE FROM NOTES_TABLE")
    suspend fun deleteNotes()
}