package com.example.notesapp.features_notes.domain.use_case

import com.example.notesapp.features_notes.domain.model.Note
import com.example.notesapp.features_notes.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}