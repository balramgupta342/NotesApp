package com.example.notesapp.features_notes.presentation.util

sealed class Screen(
    val route: String
) {
    object NotesScreen : Screen("notes_screen")
    object AddEditNoteScreen : Screen("add_edit_note_screen")

}