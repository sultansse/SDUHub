package com.softwareit.sduhub.domain

import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.data.repository.NotesRepository

class UpsertNoteUseCase(
    private val repository: NotesRepository,
) {

    suspend operator fun invoke(noteDTO: NoteDTO) {
        repository.upsertNote(noteDTO)
    }
}
