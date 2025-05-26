package com.example.taskora.feature_auth.domain.usecase

import android.util.Patterns
import com.example.taskora.feature_auth.domain.repository.FirebaseAuthRepository
import com.example.taskora.feature_auth.presentation.register.EmptyFieldException

class RegisterUseCase(
    private val repository: FirebaseAuthRepository
) {

    @Throws(EmptyFieldException::class)
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw EmptyFieldException("Email is not valid")
        } else repository.register(email, password)
    }
}