package com.example.notesapp.data.repository

import com.example.notesapp.data.local.NoteDao
import com.example.notesapp.data.local.NoteEntity
import com.example.notesapp.domain.repository.NotesRepository

class NotesRepositoryImpl(
    private val dao: NoteDao
) : NotesRepository {

    override fun getNotes() = dao.getNotes()

    override suspend fun insert(note: NoteEntity) = dao.insert(note)

    override suspend fun delete(note: NoteEntity) = dao.delete(note)

    override suspend fun getNoteById(id: Int): NoteEntity? {
        return dao.getNoteById(id)
    }
}