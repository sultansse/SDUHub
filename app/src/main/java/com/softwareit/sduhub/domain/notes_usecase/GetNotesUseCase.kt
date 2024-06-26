package com.softwareit.sduhub.domain.notes_usecase

import com.softwareit.sduhub.data.local.room.notes.NoteDBO
import com.softwareit.sduhub.data.repository.NotesRepository
import kotlinx.coroutines.flow.Flow


class GetNotesUseCase(
    private val repository: NotesRepository,
) {

    operator fun invoke(): Flow<List<NoteDBO>> {
        return repository.getNotes()
    }
}
