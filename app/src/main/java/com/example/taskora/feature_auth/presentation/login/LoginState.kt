package com.example.taskora.feature_auth.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val loginError: String? = null,
    val success: Boolean = false
)


class InvalidEmailException(message: String = "Invalid email format") : Exception(message)
class EmptyPasswordException(message: String = "Password cannot be empty") : Exception(message)
class EmptyEmailException(message: String = "Email cannot be empty") : Exception(message)

