package com.example.notesapp.di

import android.app.Application
import androidx.room.Room
import com.example.notesapp.features_notes.data.data_source.NoteDatabase
import com.example.notesapp.features_notes.domain.repository.NoteRepositoryImpl
import com.example.notesapp.features_notes.domain.repository.NoteRepository
import com.example.notesapp.features_notes.domain.use_case.AddNote
import com.example.notesapp.features_notes.domain.use_case.DeleteNote
import com.example.notesapp.features_notes.domain.use_case.GetNote
import com.example.notesapp.features_notes.domain.use_case.GetNotes
import com.example.notesapp.features_notes.domain.use_case.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}