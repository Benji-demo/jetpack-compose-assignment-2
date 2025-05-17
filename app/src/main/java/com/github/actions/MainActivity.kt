package com.github.actions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.github.actions.ui.navigation.AppNavGraph
import com.github.actions.ui.theme.ActionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActionsTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
//                    ToDoApp(navController = navController )
            }
        }
    }
}