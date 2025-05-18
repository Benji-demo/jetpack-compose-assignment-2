package com.github.actions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.actions.ui.screens.TodoViewModel
import androidx.navigation.NavController
import com.github.actions.ui.components.ToDoItem
import com.github.actions.ui.screens.TodoUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoApp(navController: NavController) {
    val factory = LocalViewModelFactory.current

    val todoViewModel: TodoViewModel = viewModel(
        factory = factory)
    val uiState = todoViewModel.uiState

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ToDo List",
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(top = 4.dp))},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary),
//                windowInsets = WindowInsets(10.dp)
            )
        }
    ) { paddingValues ->
        when(uiState){
            is TodoUiState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
//                    Image(
//                        modifier = modifier.size(200.dp),
//                        painter = painterResource(R.drawable.loading_img),
//                        contentDescription = stringResource(R.string.loading)
//                    )
                    CircularProgressIndicator()
                    Text("Loading...")
                }
            }

            is TodoUiState.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Error: ${uiState.message}")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { todoViewModel.refreshTodos() }) {
                        Text("Retry")}
                }
            }

            is TodoUiState.Success -> {
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(uiState.todos.size) { index ->
                ToDoItem(
                    todo = uiState.todos[index],
                    onClick = {
                    navController.navigate(
                        "details/${uiState.todos[index].userId}/${uiState.todos[index].id}/${uiState.todos[index].title}/${uiState.todos[index].completed}"
                    )
                } )
            }
        }
            }
        }
    }
}

