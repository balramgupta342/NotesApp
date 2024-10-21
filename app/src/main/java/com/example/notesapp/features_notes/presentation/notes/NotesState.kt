package com.example.notesapp.features_notes.presentation.notes

import com.example.notesapp.features_notes.domain.model.Note
import com.example.notesapp.features_notes.domain.util.NoteOrder
import com.example.notesapp.features_notes.domain.util.OrderType

data class NotesState(
    val notes : List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val searchText: String = "",
    val isSearchActive: Boolean = false
)
