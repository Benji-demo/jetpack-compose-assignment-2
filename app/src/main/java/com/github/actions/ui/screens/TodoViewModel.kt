package com.github.actions.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.actions.model.Todo
import com.github.actions.network.TodoApi
import kotlinx.coroutines.launch


sealed interface TodoUiState {
    data class Success(val todos: List<Todo>) : TodoUiState
    data class Error(val message: String) : TodoUiState
    object Loading : TodoUiState
}

class TodoViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var uiState by mutableStateOf<TodoUiState>(TodoUiState.Loading)
        private set
//    var todoState by mutableStateOf<Todo?>(null)

    init {
        getTodoList()
    }

    private fun getTodoList() {
        viewModelScope.launch {
            uiState = TodoUiState.Loading
            uiState = try {
                val listResult = TodoApi.retrofitService.getTodos()
                TodoUiState.Success(listResult)
//                println("Fetched Todos: $listResult")
//            todoState =listResult
            } catch (e: Exception) {
                TodoUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
