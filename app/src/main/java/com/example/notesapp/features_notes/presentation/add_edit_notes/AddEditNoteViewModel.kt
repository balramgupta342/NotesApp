package com.example.notesapp.features_notes.presentation.add_edit_notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.features_notes.domain.model.InvalidateException
import com.example.notesapp.features_notes.domain.model.Note
import com.example.notesapp.features_notes.domain.use_case.NoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _noteTitle = mutableStateOf(NoteTextFieldState(
        hint = "Enter title..."
    ))
    val noteTitle : State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(
        hint = "Enter some content"
    ))
    val noteContent : State<NoteTextFieldState> = _noteContent

    private val _noteColor = mutableIntStateOf(Note.noteColors.random().toArgb())
    val noteColor : State<Int> = _noteColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow : SharedFlow<UiEvent> = _eventFlow

    private var currentNoteId : Int?= null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteContent.value = noteContent.value.copy(
                            text = note.content,
                            isHintVisible = false
                        )
                        _noteColor.intValue = note.color
                    }
                }
            }
        }
    }


    fun onEvent(event: AddEditNoteEvent) {
        when(event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = noteTitle.value.text.isBlank() && !event.focusState.isFocused
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(
                    text = event.value
                )
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = noteContent.value.text.isBlank() && !event.focusState.isFocused
                )
            }
            is AddEditNoteEvent.ChangeColor -> {
                _noteColor.intValue  = event.color
            }
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                            title = noteTitle.value.text,
                            content = noteContent.value.text,
                            color = noteColor.value,
                            timeStamp = System.currentTimeMillis(),
                            id = currentNoteId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveNote)
                    }catch (e: InvalidateException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveNote : UiEvent()
    }

}
