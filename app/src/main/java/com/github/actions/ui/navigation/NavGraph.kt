package com.github.actions.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.actions.ToDoApp
import com.github.actions.model.Todo
import com.github.actions.ui.screens.Details

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = "todo_list" ){

        composable("todo_list") {ToDoApp(navController = navController)}

        composable(
            route = "details/{userId}/{id}/{title}/{completed}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
                navArgument("id") { type = NavType.IntType },
                navArgument("title") { type = NavType.StringType },
                navArgument("completed") { type = NavType.BoolType }
            )
        ) { backStackEntry ->
            val args = backStackEntry.arguments!!
            val todo = Todo(
                userId = args.getInt("userId"),
                id = args.getInt("id"),
                title = args.getString("title") ?: "",
                completed = args.getBoolean("completed")
            )
            Details(todo, navController)
        }
    }
}