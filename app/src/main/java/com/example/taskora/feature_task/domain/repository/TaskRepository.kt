package com.example.taskora.feature_task.domain.repository

import com.example.taskora.feature_task.domain.model.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<TaskEntity>>

    suspend fun getTaskById(id: Int): TaskEntity?

    suspend fun insertTask(task: TaskEntity)

    suspend fun deleteTask(task: TaskEntity)
}