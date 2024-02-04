package com.example.todolistcrud.View

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolistcrud.ItemList.TaskItem
import com.example.todolistcrud.R
import com.example.todolistcrud.Repository.RepositoryTasks
import com.example.todolistcrud.ui.theme.White


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskList(navController: NavController) {


    val tasksRepository = RepositoryTasks()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6650a4)),
                title = {
                    Text(
                        text = "Task List",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                }
            )
        },
        containerColor = Color(0XFF707070),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("SaveTasks")
                },
                containerColor = Color(0xFF6650a4),
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = "Button Add Task"
                )
            }
        }
    ) {

        val listTasks = tasksRepository.retrieveTasks().collectAsState(mutableListOf()).value

        LazyColumn {
            itemsIndexed(listTasks) { position, _ ->
                TaskItem(
                    position = position,
                    listTasks = listTasks,
                    context = context,
                    navController = navController
                )
            }
        }
    }
}




