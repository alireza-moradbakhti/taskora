package com.example.taskora.feature_auth.presentation.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                val email = event.value
                _state.update { it.copy(email = email, emailError = validateEmail(email)) }
            }

            is LoginEvent.PasswordChanged -> {
                val password = event.value
                _state.update { it.copy(password = password, passwordError = validatePassword(password)) }
            }

            is LoginEvent.Submit -> {
                tryLogin()
            }
        }
    }

    private fun tryLogin() {
        val currentState = _state.value

        val emailError = validateEmail(currentState.email)
        val passwordError = validatePassword(currentState.password)

        if (emailError != null || passwordError != null) {
            _state.update {
                it.copy(
                    emailError = emailError,
                    passwordError = passwordError
                )
            }
            return
        }

        _state.update { it.copy(isLoading = true, loginError = null) }

        firebaseAuth.signInWithEmailAndPassword(currentState.email, currentState.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _state.update { it.copy(isLoading = false, success = true) }
                } else {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            loginError = task.exception?.localizedMessage ?: "Login failed"
                        )
                    }
                }
            }
    }


    private fun validateEmail(email: String): String? {
        return if (email.isBlank()) "Email cannot be empty"
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) "Invalid email format"
        else null
    }

    private fun validatePassword(password: String): String? {
        return if (password.isBlank()) "Password cannot be empty"
        else null
    }
}