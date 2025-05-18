package com.github.actions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.github.actions.data.local.TodoDatabase
import com.github.actions.data.repository.TodoRepository
import com.github.actions.data.remote.TodoApi
import com.github.actions.ui.navigation.AppNavGraph
import com.github.actions.ui.screens.TodoViewModelFactory
import com.github.actions.ui.theme.ActionsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize dependencies
        val db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "todo_database"
        ).build()
        val todoDao = db.todoDao()
        val apiService = TodoApi.retrofitService
        val repository = TodoRepository(apiService, todoDao = todoDao)
        val viewModelFactory = TodoViewModelFactory(repository)
        enableEdgeToEdge()
        setContent {
            ActionsTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(
                    LocalViewModelFactory provides viewModelFactory
                ) {
                    AppNavGraph(navController = navController)
                }
            }
        }
    }
}

val LocalViewModelFactory = staticCompositionLocalOf<TodoViewModelFactory> {
    error("No ViewModelFactory provided")
}