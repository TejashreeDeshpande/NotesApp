package com.example.notesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.local.NoteEntity
import com.example.notesapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(
    private val repo: NotesRepository
) : ViewModel() {

    val notes = repo.getNotes()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun addNote(
        title: String,
        desc: String,
        lat: Double?,
        lng: Double?
    ) {
        viewModelScope.launch {
            repo.insert(
                NoteEntity(
                    title = title,
                    description = desc,
                    timestamp = System.currentTimeMillis(),
                    latitude = lat ?: 0.0,
                    longitude = lng ?: 0.0
                )
            )
        }
    }

    fun delete(note: NoteEntity) {
        viewModelScope.launch {
            repo.delete(note)
        }
    }

}