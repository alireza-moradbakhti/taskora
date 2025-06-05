package com.example.taskora.feature_task.data.repository

import com.example.taskora.feature_task.data.remote.TaskDao
import com.example.taskora.feature_task.domain.model.TaskEntity
import com.example.taskora.feature_task.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao,
) : TaskRepository {

    override fun getTasks(): Flow<List<TaskEntity>> {
        return dao.getTasks()
    }

    override suspend fun getTaskById(id: Int): TaskEntity? {
        return dao.getTasksById(id)
    }

    override suspend fun insertTask(task: TaskEntity) {
        return dao.insertTask(task)
    }

    override suspend fun deleteTask(task: TaskEntity) {
        return dao.deleteTask(task)
    }


}