package com.softwareit.sduhub.data.local.notes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM NOTES_TABLE WHERE id = :id")
    fun getNote(id: Int): Flow<NoteDTO>

    @Query("SELECT * FROM NOTES_TABLE ORDER BY created_at DESC")
    fun getNotes(): Flow<List<NoteDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: NoteDTO)

    @Update
    suspend fun updateNote(note: NoteDTO)

    @Query("DELETE FROM NOTES_TABLE WHERE id = :id")
    suspend fun deleteNote(id: Int)

    @Query("DELETE FROM NOTES_TABLE")
    suspend fun deleteNotes()
}