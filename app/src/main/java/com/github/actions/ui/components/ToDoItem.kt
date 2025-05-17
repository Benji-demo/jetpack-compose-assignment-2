package com.github.actions.ui.components

import android.R.attr.maxLines
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.actions.model.Todo

@Composable
fun ToDoItem(modifier: Modifier = Modifier, todo: Todo, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .clickable { onClick() },
        color = MaterialTheme.colorScheme.secondary,
        shape = RoundedCornerShape(12.dp)){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
        Text("Task ${todo.id}: ${todo.title}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1)
        Text( if (todo.completed) "Status: Completed"
        else "Status: Ongoing")
    }
    }
}