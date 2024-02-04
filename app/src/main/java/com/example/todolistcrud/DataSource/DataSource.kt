package com.example.todolistcrud.DataSource

import com.example.todolistcrud.Model.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource {

    private val db = FirebaseFirestore.getInstance()

    private val _alltasks = MutableStateFlow<MutableList<Tasks>>(mutableListOf())
    private val alltasks: StateFlow<MutableList<Tasks>> = _alltasks

    //Function To Save Task Fields: TASK TITLE, DESCRIPTION, PRIORITY LEVEL.
    fun saveTasksMethod(task: String, description: String, priority: Int) {

        val taskMap = hashMapOf(
            "task" to task,
            "description" to description,
            "priority" to priority
        )

        db.collection("task").document(task).set(taskMap).addOnCanceledListener {

        }.addOnFailureListener {

        }

    }

    //Function To Retrieve Tasks Field: TASK TITLE, DESCRIPTION, PRIORITY LEVEL.
    fun recoverTasksMethods(): Flow<MutableList<Tasks>> {

        val listTasks: MutableList<Tasks> = mutableListOf()

        db.collection("task").get().addOnCompleteListener { querySnapshot ->
            if (querySnapshot.isSuccessful) {
                for (document in querySnapshot.result) {
                    val task = document.toObject(Tasks::class.java)
                    listTasks.add(task)
                    _alltasks.value = listTasks
                }
            }
        }
        return alltasks
    }

    //Function Delete Tasks.
    fun deleteTasks(task: String){
        db.collection("task").document(task).delete().addOnCompleteListener{

        }.addOnFailureListener{

        }
    }
}