package com.example.taskora.feature_auth.presentation.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskora.feature_auth.domain.usecase.AuthUseCases
import com.example.taskora.feature_auth.domain.usecase.ValidateLoginInputs
import com.example.taskora.feature_auth.domain.util.EmptyEmailException
import com.example.taskora.feature_auth.domain.util.EmptyPasswordException
import com.example.taskora.feature_auth.domain.util.InvalidEmailException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: AuthUseCases,
    private val validateLoginInputs: ValidateLoginInputs
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
                _state.update {
                    it.copy(
                        password = password,
                        passwordError = validatePassword(password)
                    )
                }
            }

            is LoginEvent.Submit -> {
                tryLogin()
            }
        }
    }

    private fun tryLogin() {
        viewModelScope.launch {
            try {
                val email = _state.value.email
                val password = _state.value.password

                validateLoginInputs(email, password)

                _state.update { it.copy(isLoading = true, loginError = null) }

                firebaseAuth.loginUseCase(email, password).onSuccess {
                    _state.update { it.copy(isLoading = false, success = true) }
                }.onFailure { error ->
                    _state.update { it.copy(isLoading = false, loginError = error.message) }
                }

            } catch (e: EmptyEmailException) {
                _state.update { it.copy(emailError = e.message) }
            } catch (e: InvalidEmailException) {
                _state.update { it.copy(emailError = e.message) }
            } catch (e: EmptyPasswordException) {
                _state.update { it.copy(passwordError = e.message) }
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