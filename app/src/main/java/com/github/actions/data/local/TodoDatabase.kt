package com.github.actions.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.actions.data.model.Todo

@Database(entities = [Todo::class],version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
