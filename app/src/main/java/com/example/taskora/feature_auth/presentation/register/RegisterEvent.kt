package com.example.taskora.feature_auth.presentation.register

sealed class RegisterEvent {
    data class EmailChanged(val value: String) : RegisterEvent()
    data class PasswordChanged(val value: String) : RegisterEvent()
    data class ConfirmPasswordChanged(val value: String) : RegisterEvent()
    data class UsernameChanged(val value: String) : RegisterEvent()
    data class FullNameChanged(val value: String) : RegisterEvent()
    object Submit : RegisterEvent()
}