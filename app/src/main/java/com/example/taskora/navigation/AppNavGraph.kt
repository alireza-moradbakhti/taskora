package com.example.taskora.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskora.feature_auth.presentation.forget.ForgetPasswordScreen
import com.example.taskora.feature_auth.presentation.login.LoginScreen
import com.example.taskora.feature_auth.presentation.register.RegisterScreen
import com.example.taskora.utils.AppConstants

@Composable
fun AppNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = AppConstants.START_DESTINATION
    ) {
        composable(AppConstants.START_DESTINATION) {
            LoginScreen(navController)
        }
        composable(AppConstants.REGISTER_SCREEN_ROUTE) {
            RegisterScreen(navController)
        }
        composable(AppConstants.FORGET_PASSWORD_ROUTE) {
            ForgetPasswordScreen(navController)
        }
    }

}