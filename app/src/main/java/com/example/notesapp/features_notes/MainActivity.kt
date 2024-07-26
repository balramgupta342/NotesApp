package com.example.notesapp.features_notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesapp.features_notes.presentation.add_edit_notes.AddEditNoteScreen
import com.example.notesapp.features_notes.presentation.notes.NotesScreen
import com.example.notesapp.features_notes.presentation.util.Screen
import com.example.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.NotesScreen.route
                        ) {
                            composable(route = Screen.NotesScreen.route) {
                                NotesScreen(navController = navController)
                            }
                            composable(
                                route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                                arguments = listOf(
                                    navArgument(
                                        name = "noteId"
                                    ) {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    },
                                    navArgument(
                                        name = "noteColor"
                                    ) {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    },
                                )
                            ) {
                                val color = it.arguments?.getInt("noteColor") ?: -1
                                AddEditNoteScreen(
                                    navController = navController,
                                    noteColor = color
                                )
                            }
                        }
                }
            }
        }
    }
}
