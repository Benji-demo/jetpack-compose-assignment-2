package com.github.actions.data.repository

import com.github.actions.data.local.TodoDao
import com.github.actions.data.remote.TodoApiService
import com.github.actions.data.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class TodoRepository(
    private val apiService: TodoApiService,
    private val todoDao: TodoDao
) {
    fun getTodos(): Flow<List<Todo>> = flow {
        val cachedTodo = todoDao.getAllTodos().first()
        emit(cachedTodo)

//        Trying to fetch from web
        try {
            val newTodos = apiService.getTodos()
            todoDao.clearAll()
            todoDao.insertAll(newTodos)
            emit(newTodos)
        } catch (e: Exception) {
            if (cachedTodo.isEmpty()) {
                emit(emptyList<Todo>())
            }
        }
    }
}