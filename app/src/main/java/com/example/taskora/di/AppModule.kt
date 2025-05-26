package com.example.taskora.di

import com.example.taskora.feature_auth.data.repository.FirebaseAuthRepositoryImpl
import com.example.taskora.feature_auth.domain.repository.FirebaseAuthRepository
import com.example.taskora.feature_auth.domain.usecase.AuthUseCases
import com.example.taskora.feature_auth.domain.usecase.LoginUseCase
import com.example.taskora.feature_auth.domain.usecase.RegisterUseCase
import com.example.taskora.feature_auth.domain.usecase.ValidateLoginInputs
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuthRepository(auth: FirebaseAuth): FirebaseAuthRepository {
        return FirebaseAuthRepositoryImpl(auth)
    }

    @Provides
    @Singleton
    fun provideValidateLoginInputs(): ValidateLoginInputs {
        return ValidateLoginInputs()
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(repository: FirebaseAuthRepository): AuthUseCases {
        return AuthUseCases(
            loginUseCase = LoginUseCase(repository),
            registerUseCase = RegisterUseCase(repository)
        )
    }

}