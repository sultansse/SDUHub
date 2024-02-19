package com.softwareit.sduhub.domain

import com.softwareit.sduhub.data.local.notes.NoteDTO
import com.softwareit.sduhub.data.repository.NotesRepository


class AddNoteUseCase(
    private val repository: NotesRepository,
) {
    suspend operator fun invoke(noteDTO: NoteDTO) {
        repository.addNote(noteDTO)
    }
}
