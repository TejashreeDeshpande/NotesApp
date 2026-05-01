package com.example.notesapp.domain.repository

import com.example.notesapp.data.local.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNotes(): Flow<List<NoteEntity>>
    suspend fun insert(note: NoteEntity)
    suspend fun delete(note: NoteEntity)
    suspend fun getNoteById(id: Int): NoteEntity?
}