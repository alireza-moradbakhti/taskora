package com.example.taskora.feature_auth.domain.usecase

import com.example.taskora.feature_auth.domain.repository.FirebaseAuthRepository

class LoginUseCase(
    private val repository: FirebaseAuthRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.login(email, password)
    }

}