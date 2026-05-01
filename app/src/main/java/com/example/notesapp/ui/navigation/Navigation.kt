package com.example.notesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.notesapp.ui.screen.AddNoteScreen
import com.example.notesapp.ui.screen.MapScreen
import com.example.notesapp.ui.screen.NotesListScreen
import com.example.notesapp.ui.viewmodel.NotesViewModel
import kotlinx.serialization.Serializable

@Serializable
object NotesRoute

@Serializable
object AddRoute

@Serializable
data class MapRoute(
    val lat: Double,
    val lng: Double
)

@Composable
fun NotesApp(
    vm: NotesViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NotesRoute
    ) {

        composable<NotesRoute> {
            NotesListScreen(
                vm = vm,
                nav = navController
            )
        }

        composable<AddRoute> {
            AddNoteScreen(
                vm = vm,
                nav = navController
            )
        }

        composable<MapRoute> { backStackEntry ->

            val route = backStackEntry.toRoute<MapRoute>()

            MapScreen(
                lat = route.lat,
                lng = route.lng
            )
        }
    }
}