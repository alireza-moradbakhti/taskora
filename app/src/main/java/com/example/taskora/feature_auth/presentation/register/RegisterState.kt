package com.example.taskora.feature_auth.presentation.register

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val fullName: String = "",
    val username: String = "",
    val confirmPassword: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError :String? = null,
    val usernameError: String? = null,
    val fullNameError: String? = null,
    val isLoading: Boolean = false,
    val registrationError: String? = null,
    val success: Boolean = false
)

class EmptyFieldException(message: String) : Exception(message)