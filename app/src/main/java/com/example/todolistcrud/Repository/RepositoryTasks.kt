package com.example.todolistcrud.Repository

import com.example.todolistcrud.DataSource.DataSource
import com.example.todolistcrud.Model.Tasks
import kotlinx.coroutines.flow.Flow

class RepositoryTasks {

    private val dataSource = DataSource()

    fun repositoryTasksMethod(task: String, description: String, priority: Int) {
        dataSource.saveTasksMethod(task, description, priority)
    }

    fun retrieveTasks(): Flow<MutableList<Tasks>> {
        return dataSource.recoverTasksMethods()
    }

    fun deleteTasks(task: String){
        dataSource.deleteTasks(task)
    }
}