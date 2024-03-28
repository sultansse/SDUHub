package com.softwareit.sduhub.domain.notes_usecase

import com.softwareit.sduhub.data.repository.NotesRepository

class DeleteNoteUseCase(
    private val repository: NotesRepository,
) {

    suspend operator fun invoke(noteId: Int) {
        repository.deleteNote(noteId)
    }
}