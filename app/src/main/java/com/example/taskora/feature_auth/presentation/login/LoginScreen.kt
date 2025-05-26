package com.example.taskora.feature_auth.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskora.R
import com.example.taskora.feature_auth.presentation.components.AppPasswordField
import com.example.taskora.feature_auth.presentation.components.AppTextField
import com.example.taskora.feature_auth.presentation.components.LoadingButton
import com.example.taskora.utils.AppConstants

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.greeting), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        AppTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(LoginEvent.EmailChanged(it)) },
            label = stringResource(R.string.email),
            isError = state.emailError != null,
            errorMessage = state.emailError,
            trailingIcon = {
                if (state.emailError != null){
                    Icon(
                        imageVector = Icons.Rounded.Error,
                        contentDescription = null,
                        tint = Color.Red,
                    )
                }else if (state.email.isNotBlank()){
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null,
                        tint = Color.Green,
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        AppPasswordField(
            value = state.password,
            onValueChange = { viewModel.onEvent(LoginEvent.PasswordChanged(it)) },
            isError = state.passwordError != null,
            errorMessage = state.passwordError
        )

        Spacer(modifier = Modifier.height(16.dp))

        LoadingButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login),
            isLoading = state.isLoading,
            onClick = { viewModel.onEvent(LoginEvent.Submit) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = { navController.navigate(AppConstants.REGISTER_SCREEN_ROUTE) }) {
            Text(stringResource(R.string.sign_up_desc))
        }
    }

    if (state.success) {
        LaunchedEffect(Unit) {
            navController.navigate(AppConstants.HOME_ROUTE) {
                popUpTo(AppConstants.START_DESTINATION) { inclusive = true }
            }
        }
    }

}