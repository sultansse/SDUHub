package com.softwareit.sduhub.domain.notes_usecase

import com.softwareit.sduhub.data.repository.NotesRepository


class DeleteNotesUseCase(
    private val repository: NotesRepository,
) {
    suspend operator fun invoke() = repository.deleteNotes()
}
