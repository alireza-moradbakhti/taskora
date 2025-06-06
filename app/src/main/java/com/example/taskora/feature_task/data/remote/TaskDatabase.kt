package com.example.taskora.feature_task.data.remote

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskora.feature_task.domain.model.TaskEntity
import com.example.taskora.utils.AppConstants

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = AppConstants.TASK_DB
    }
}