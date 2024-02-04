package com.example.todolistcrud.ItemList

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.todolistcrud.Model.Tasks
import com.example.todolistcrud.R
import com.example.todolistcrud.Repository.RepositoryTasks
import com.example.todolistcrud.ui.theme.Black
import com.example.todolistcrud.ui.theme.RadioButtonGreenSelected
import com.example.todolistcrud.ui.theme.RadioButtonRedSelected
import com.example.todolistcrud.ui.theme.RadioButtonYellowSelected
import com.example.todolistcrud.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TaskItem(
    position: Int,
    listTasks: MutableList<Tasks>,
    context: Context,
    navController: NavController
) {

    //  Retrieving All 3 Main Elements That Are Inside The TO DO LIST Element.
    val titleTask = listTasks[position].task
    val descriptionTask = listTasks[position].description
    val priority = listTasks[position].priority

    //Validity To Delete Task.
    val scope = rememberCoroutineScope()
    val tasksRepository = RepositoryTasks()

    fun dialogDelete() {

        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Delete Task")
            .setMessage("Want To Delete Task?")
            .setPositiveButton("Yes") { _, _ ->

                tasksRepository.deleteTasks(titleTask.toString())

                scope.launch(Dispatchers.Main) {
                    listTasks.removeAt(position)
                    navController.navigate("TaskList")
                    Toast.makeText(context, "Task Delete!", Toast.LENGTH_SHORT).show()
                }

            }
            .setNegativeButton("No") { _, _ ->

            }
            .show()
    }

    // Validity To Identify The Priority Level Of Each Task.
    val priorityLevel: String = when (priority) {
        0 -> {
            "No Priority"
        }

        1 -> {
            "Low Priority"
        }

        2 -> {
            "Medium Priority"
        }

        else -> {
            "High Priority"
        }
    }

    //Validation For Color Of Each Priority.
    val color = when (priority) {
        0 -> {
            Color.Black
        }

        1 -> {
            RadioButtonGreenSelected
        }

        2 -> {
            RadioButtonYellowSelected
        }

        else -> {
            RadioButtonRedSelected
        }
    }

    Card(
        colors = CardDefaults.cardColors(
            contentColor = Black,
            containerColor = White,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {
            val (txtTitle, txtDescription, txtPriority, cardPriority, btnDelete) = createRefs()

            Text(
                text = titleTask.toString(),
                modifier = Modifier.constrainAs(txtTitle) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = descriptionTask.toString(),
                modifier = Modifier.constrainAs(txtDescription) {
                    top.linkTo(txtTitle.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = priorityLevel,
                modifier = Modifier.constrainAs(txtPriority) {
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )

            Card(
                colors = CardDefaults.cardColors(color),
                modifier = Modifier
                    .size(25.dp)
                    .constrainAs(cardPriority) {
                        top.linkTo(txtDescription.bottom, margin = 10.dp)
                        start.linkTo(txtPriority.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    }
            ) {

            }
            IconButton(
                onClick = {
                    dialogDelete()
                },
                modifier = Modifier.constrainAs(btnDelete) {
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(cardPriority.end, margin = 30.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )
            {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = "Icon Delete"
                )
            }
        }
    }
}


