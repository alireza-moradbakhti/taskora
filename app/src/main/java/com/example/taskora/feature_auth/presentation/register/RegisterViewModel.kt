package com.example.taskora.feature_auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskora.feature_auth.domain.usecase.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: AuthUseCases,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailChanged -> {
                _uiState.value = _uiState.value.copy(email = event.value)
                val emailError = validateEmail(event.value)
                _uiState.value = if (emailError != null) {
                    _uiState.value.copy(emailError = emailError)
                } else {
                    _uiState.value.copy(emailError = null)
                }
            }

            is RegisterEvent.PasswordChanged -> {
                _uiState.value = _uiState.value.copy(password = event.value)
                val passwordError = validatePassword(event.value)
                _uiState.value = if (passwordError != null) {
                    _uiState.value.copy(passwordError = passwordError)
                } else {
                    _uiState.value.copy(passwordError = null)
                }
            }

            is RegisterEvent.ConfirmPasswordChanged -> {
                _uiState.value = _uiState.value.copy(confirmPassword = event.value)
                val confirmPasswordError =
                    validateConfirmPassword(event.value, _uiState.value.password)
                _uiState.value = if (confirmPasswordError != null) {
                    _uiState.value.copy(confirmPasswordError = confirmPasswordError)
                } else {
                    _uiState.value.copy(confirmPasswordError = null)
                }
            }

            is RegisterEvent.UsernameChanged -> {
                _uiState.value = _uiState.value.copy(username = event.value)
                val usernameError = validateUsername(event.value)
                _uiState.value = if (usernameError != null) {
                    _uiState.value.copy(usernameError = usernameError)
                } else {
                    _uiState.value.copy(usernameError = null)
                }
            }

            is RegisterEvent.FullNameChanged -> {
                _uiState.value = _uiState.value.copy(fullName = event.value)
                val fullNameError = if (event.value.isBlank()) "Full name cannot be empty" else null
                _uiState.value = if (fullNameError != null) {
                    _uiState.value.copy(fullNameError = fullNameError)
                } else {
                    _uiState.value.copy(fullNameError = null)
                }
            }

            is RegisterEvent.Submit -> {
                registerUser()
            }
        }
    }

    private fun registerUser() {
        viewModelScope.launch {
            val email = _uiState.value.email
            val password = _uiState.value.password
            _uiState.value = _uiState.value.copy(isLoading = true, registrationError = null)

            registerUseCase.registerUseCase(email, password).also { result ->
                if (result.isSuccess) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        registrationError = null,
                        success = true
                    )
                } else {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        registrationError = result.exceptionOrNull()?.message
                            ?: "Registration failed",
                        success = false
                    )
                }
            }
        }
    }


    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email cannot be empty"
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Invalid email format"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return if (password.length < 6) "Password must be at least 6 characters"
        else if (password.isBlank()) "Password cannot be empty"
        else null
    }

    private fun validateConfirmPassword(confirmPassword: String, password: String): String? {
        return if (confirmPassword != password) "Passwords do not match"
        else null
    }


    private fun validateUsername(username: String): String? {
        val usernamePattern = Pattern.compile("^[a-zA-Z0-9_]{3,20}$")

        return when {
            username.isBlank() -> "Username cannot be empty"
            username.length < 3 -> "Username must be at least 3 characters"
            !usernamePattern.matcher(username)
                .matches() -> "username only can contain letters and numbers"

            else -> null
        }
    }

}