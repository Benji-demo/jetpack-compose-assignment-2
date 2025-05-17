package com.github.actions.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.actions.model.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(todo: Todo,navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo",
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(top = 4.dp))},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back",
                            tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary),
//                windowInsets = WindowInsets(10.dp)
            )
        }
    ) { paddingValues ->

        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .padding(top = 6.dp)) {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)) {
                // Title part for the Todo title
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Task: ${todo.title}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f),
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            // Code for the description
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
                    .background(color = MaterialTheme.colorScheme.background)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                ) {
                    Column(modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)){
                        Row() {
                            Text("UserID: ${todo.userId}",
                                modifier = Modifier.weight(1f))
                            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                            Text("Task-ID: ${todo.id}",
                                modifier = Modifier.weight(1f))}

                        Spacer(modifier = Modifier.padding(4.dp))
                        Text("Status: ${if (todo.completed) "Completed" else "Ongoing"}",
                            style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.padding(4.dp))

                        Text(
                            "Description",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "No description provided.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}