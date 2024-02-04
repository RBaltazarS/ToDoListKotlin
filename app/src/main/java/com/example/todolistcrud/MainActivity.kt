package com.example.todolistcrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistcrud.View.SaveTasks
import com.example.todolistcrud.View.TaskList
import com.example.todolistcrud.ui.theme.ToDoListCRUDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListCRUDTheme {

                val navController = rememberNavController()
                
                NavHost(navController = navController, startDestination = "TaskList"){
                    composable(
                        route = "TaskList"
                    ){
                        TaskList(navController)
                    }

                    composable(
                        route = "SaveTasks"
                    ){
                        SaveTasks(navController)
                    }
                }

            }
        }
    }
}

