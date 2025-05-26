package com.example.taskora.feature_auth.domain.repository

interface FirebaseAuthRepository {

    suspend fun login(email: String, password: String): Result<Unit>

    suspend fun register(email: String, password: String): Result<Unit>

    suspend fun resetPassword(email: String): Result<Unit>

    fun signOut()

    fun currentUser()
}