package com.example.todolistcrud.View

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolistcrud.Components.ButtonComponent
import com.example.todolistcrud.Components.TextBox
import com.example.todolistcrud.Constants.Constants
import com.example.todolistcrud.Repository.RepositoryTasks
import com.example.todolistcrud.ui.theme.RadioButtonGreenDisable
import com.example.todolistcrud.ui.theme.RadioButtonGreenSelected
import com.example.todolistcrud.ui.theme.RadioButtonRedDisable
import com.example.todolistcrud.ui.theme.RadioButtonRedSelected
import com.example.todolistcrud.ui.theme.RadioButtonYellowDisable
import com.example.todolistcrud.ui.theme.RadioButtonYellowSelected
import com.example.todolistcrud.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveTasks(navController: NavController) {


    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val repositoryTasksMethod = RepositoryTasks()


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF6650a4)),
                title = {
                    Text(
                        text = "Save Tasks",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                }
            )
        }
    ) {

        //Status Initial Title And Description BOX.
        var titleTask by remember {
            mutableStateOf("")
        }

        var descriptionTask by remember {
            mutableStateOf("")
        }


        //Priority Level Radio Button.
        var noPriority by remember {
            mutableStateOf(false)
        }

        var lowPriority by remember {
            mutableStateOf(false)
        }

        var mediumPriority by remember {
            mutableStateOf(false)
        }

        var highPriority by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            TextBox(
                value = titleTask,
                onValueChange = { titleTask = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 100.dp, 20.dp, 0.dp),
                label = "Title Task",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )

            TextBox(
                value = descriptionTask,
                onValueChange = { descriptionTask = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                label = "Description Task",
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Priority Level")

                RadioButton(
                    selected = lowPriority,
                    onClick = {
                        lowPriority = !lowPriority
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioButtonGreenDisable,
                        selectedColor = RadioButtonGreenSelected
                    )
                )

                RadioButton(
                    selected = mediumPriority,
                    onClick = {
                        mediumPriority = !mediumPriority
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioButtonYellowDisable,
                        selectedColor = RadioButtonYellowSelected
                    )
                )

                RadioButton(
                    selected = highPriority,
                    onClick = {
                        highPriority = !highPriority
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioButtonRedDisable,
                        selectedColor = RadioButtonRedSelected
                    )
                )
            }

            ButtonComponent(
                onClick = {

                    var message = true

                    scope.launch(Dispatchers.IO) {
                        if (titleTask.isEmpty()) {
                            message = false
                        } else if (titleTask.isNotEmpty() && descriptionTask.isNotEmpty() && lowPriority) {
                            repositoryTasksMethod.repositoryTasksMethod(
                                titleTask,
                                descriptionTask,
                                Constants.LOW_PRIORITY
                            )
                            message = true
                        } else if (titleTask.isNotEmpty() && descriptionTask.isNotEmpty() && mediumPriority) {
                            repositoryTasksMethod.repositoryTasksMethod(
                                titleTask,
                                descriptionTask,
                                Constants.MEDIUM_PRIORITY
                            )
                            message = true
                        } else if (titleTask.isNotEmpty() && descriptionTask.isNotEmpty() && highPriority) {
                            repositoryTasksMethod.repositoryTasksMethod(
                                titleTask,
                                descriptionTask,
                                Constants.HIGH_PRIORITY
                            )
                            message = true
                        } else if(titleTask.isNotEmpty() && descriptionTask.isNotEmpty() && noPriority){
                            repositoryTasksMethod.repositoryTasksMethod(
                                titleTask,
                                descriptionTask,
                                Constants.NO_PRIORITY
                            )
                            message = true
                        }else if(titleTask.isNotEmpty() && lowPriority){
                            repositoryTasksMethod.repositoryTasksMethod(titleTask, descriptionTask, Constants.LOW_PRIORITY)
                            message = true
                        }else if(titleTask.isNotEmpty() && mediumPriority){
                            repositoryTasksMethod.repositoryTasksMethod(titleTask, descriptionTask, Constants.MEDIUM_PRIORITY)
                            message = true
                        }else if(titleTask.isNotEmpty() && highPriority){
                            repositoryTasksMethod.repositoryTasksMethod(titleTask, descriptionTask, Constants.HIGH_PRIORITY)
                            message = true
                        }else{
                            repositoryTasksMethod.repositoryTasksMethod(titleTask, descriptionTask, Constants.NO_PRIORITY)
                            message = true
                        }
                    }

                    scope.launch(Dispatchers.Main) {
                        if (message) {
                            Toast.makeText(context, "Task Saved Successfully.", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Mandatory Title.", Toast.LENGTH_SHORT).show()
                        }
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(20.dp),
                text = "Save"
            )
        }
    }
}