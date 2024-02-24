package com.softwareit.sduhub.domain

import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.data.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetNoteUseCase(
    private val repository: NotesRepository,
) {

    operator fun invoke(noteId: Int): Flow<NoteDTO> {
        return repository.getNote(noteId)
    }
}
