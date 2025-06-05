package com.example.taskora.feature_task.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val createdAt: Long,
    val updatedAt: Long,
    val isDeleted : Boolean = false,
)
