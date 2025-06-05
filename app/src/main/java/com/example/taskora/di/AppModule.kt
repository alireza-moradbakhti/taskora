package com.example.taskora.di

import android.app.Application
import androidx.room.Room
import com.example.taskora.feature_auth.data.repository.FirebaseAuthRepositoryImpl
import com.example.taskora.feature_auth.domain.repository.FirebaseAuthRepository
import com.example.taskora.feature_auth.domain.usecase.AuthUseCases
import com.example.taskora.feature_auth.domain.usecase.LoginUseCase
import com.example.taskora.feature_auth.domain.usecase.RegisterUseCase
import com.example.taskora.feature_auth.domain.usecase.ValidateLoginInputs
import com.example.taskora.feature_task.data.remote.TaskDatabase
import com.example.taskora.feature_task.data.repository.TaskRepositoryImpl
import com.example.taskora.feature_task.domain.repository.TaskRepository
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

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(db.taskDao)
    }

}