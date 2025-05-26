package com.example.taskora.feature_auth.domain.usecase

data class AuthUseCases (
    val loginUseCase: LoginUseCase,
    val registerUseCase: RegisterUseCase,
)