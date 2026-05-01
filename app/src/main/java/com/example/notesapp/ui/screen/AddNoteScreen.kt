package com.example.notesapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notesapp.ui.viewmodel.NotesViewModel

@Composable
fun AddNoteScreen(
    vm: NotesViewModel,
    nav: NavController
) {
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    var lat by remember { mutableStateOf(37.4219983) }
    var lng by remember { mutableStateOf(-122.084) }

    Column(Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )

        OutlinedTextField(
            value = desc,
            onValueChange = { desc = it },
            label = { Text("Description") }
        )

        Button(
            onClick = {
                vm.addNote(title, desc, lat, lng)
                nav.popBackStack()
            }
        ) {
            Text("Save Note")
        }
    }
}