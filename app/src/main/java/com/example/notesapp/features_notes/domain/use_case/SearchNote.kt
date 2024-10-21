package com.example.notesapp.features_notes.domain.use_case

import com.example.notesapp.features_notes.domain.model.Note

class SearchNote {
    fun execute(notes : List<Note>, query: String) : List<Note> {
        if(query.isBlank()) {
            return notes
        }
        return notes.filter {
            it.title.trim().lowercase().contains(query) ||
                    it.content.trim().lowercase().contains(query)
        }.sortedBy {
            it.timeStamp
        }
    }
}