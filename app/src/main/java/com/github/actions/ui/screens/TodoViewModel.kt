package com.github.actions.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.actions.data.model.Todo
import com.github.actions.data.repository.TodoRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

sealed interface TodoUiState {
    data class Success(val todos: List<Todo>) : TodoUiState
    data class Error(val message: String) : TodoUiState
    object Loading : TodoUiState
}

class TodoViewModel(
    private val repository: TodoRepository) : ViewModel() {
    var uiState by mutableStateOf<TodoUiState>(TodoUiState.Loading)
        private set
    var isRefreshing by mutableStateOf(false)
        private set
//    var todoState by mutableStateOf<Todo?>(null)

    init {
        getTodoList()
    }
    fun refreshTodos() {
        viewModelScope.launch {
            isRefreshing = true
            getTodoList()
            isRefreshing = false
        }
    }
    private fun getTodoList() {
        viewModelScope.launch {
            repository.getTodos().collectLatest { todos ->
                uiState = if (todos.isNotEmpty()) {
                    TodoUiState.Success(todos)
                } else {
                    TodoUiState.Error("No saved data available")
                }
            }
        }
    }
}