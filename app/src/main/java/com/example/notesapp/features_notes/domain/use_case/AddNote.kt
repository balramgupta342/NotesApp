package com.example.notesapp.features_notes.domain.use_case

import com.example.notesapp.features_notes.domain.model.InvalidateException
import com.example.notesapp.features_notes.domain.model.Note
import com.example.notesapp.features_notes.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidateException::class)
    suspend operator fun invoke(note: Note){

        if(note.title.isBlank()){
            throw InvalidateException("The title of the note can't be empty")
        }
        if(note.content.isBlank()){
            throw InvalidateException("The content of the note can't be empty")
        }
        repository.insertNote(note)
    }
}