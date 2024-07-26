package com.example.notesapp.features_notes.presentation.notes

import com.example.notesapp.features_notes.domain.model.Note
import com.example.notesapp.features_notes.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    data object RestoreNote : NotesEvent()
    data object ToggleOrderSection : NotesEvent()
}