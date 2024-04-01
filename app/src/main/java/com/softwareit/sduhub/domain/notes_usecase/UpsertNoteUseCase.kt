package com.softwareit.sduhub.domain.notes_usecase

import com.softwareit.sduhub.data.local.notes.NoteDBO
import com.softwareit.sduhub.data.repository.NotesRepository

class UpsertNoteUseCase(
    private val repository: NotesRepository,
) {

    suspend operator fun invoke(noteDBO: NoteDBO) {
        repository.upsertNote(noteDBO)
    }
}
