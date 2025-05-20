package com.example.taskora.feature_auth.domain.usecase

import android.util.Patterns
import com.example.taskora.feature_auth.domain.util.*

class ValidateLoginInputs {

    @Throws(EmptyEmailException::class, InvalidEmailException::class, EmptyPasswordException::class)
    operator fun invoke(email: String, password: String) {
        if (email.isBlank()) throw EmptyEmailException()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) throw InvalidEmailException()
        if (password.isBlank()) throw EmptyPasswordException()
    }
}