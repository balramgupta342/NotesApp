package com.example.notesapp.features_notes.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.features_notes.domain.model.Note
import com.example.notesapp.features_notes.domain.use_case.NoteUseCase
import com.example.notesapp.features_notes.domain.use_case.SearchNote
import com.example.notesapp.features_notes.domain.util.NoteOrder
import com.example.notesapp.features_notes.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    private val searchNote = SearchNote()

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when(event) {

            is NotesEvent.Order -> {
                if(state.value.noteOrder::class == event.noteOder::class &&
                    state.value.noteOrder.orderType == event.noteOder.orderType){
                    return
                }
                getNotes(event.noteOder)
            }

            is NotesEvent.DeleteNote -> {
                    viewModelScope.launch {
                        noteUseCase.deleteNote(event.note)
                        // Here it will store the deleted note in the list
                        recentlyDeletedNote = event.note

                    }
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    // Here it will return the deleted note if clicked on Undo button
                    noteUseCase.addNote(recentlyDeletedNote ?: return@launch)
                    // Here it will remove the deleted note from the list
                    recentlyDeletedNote = null
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                // Here it will toggle the visibility of the order section
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

            is NotesEvent.SearchBar -> {
                _state.value = state.value.copy(
                    notes = searchNote.execute(
                        notes = state.value.notes,
                        query = state.value.searchText
                    ),
                    searchText = state.value.searchText,
                    isSearchActive = !state.value.isSearchActive
                )
            }

        }
    }

    fun onSearchTextChange(text: String) {
        _state.value = state.value.copy(searchText = text)
        if(text.isBlank()) {
            getNotes(NoteOrder.Date(OrderType.Descending))
        }
    }

    fun onToggleSearch() {
        _state.value = state.value.copy(
            isSearchActive = !state.value.isSearchActive
        )
        if(!state.value.isSearchActive) {
            _state.value = state.value.copy(
                searchText = ""
            )
        }
    }


    // Here it will get the notes based on the order
    // Return the Flow is used to get the notes
    private fun getNotes(noteOrder: NoteOrder){
        getNotesJob?.cancel()
        getNotesJob = noteUseCase.getNotes(noteOrder)
            .onEach {
                notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

}


