package com.example.taskora.feature_auth.presentation.register

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskora.R
import com.example.taskora.feature_auth.presentation.components.AppPasswordField
import com.example.taskora.feature_auth.presentation.components.AppTextField
import com.example.taskora.feature_auth.presentation.components.LoadingButton
import com.example.taskora.utils.AppConstants

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_background),
            contentDescription = "Register Icon",
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.register_header),
            fontSize = 22.sp,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        AppTextField(
            value = uiState.email,
            onValueChange = { viewModel.onEvent(RegisterEvent.EmailChanged(it)) },
            label = stringResource(R.string.email),
            isError = uiState.emailError != null,
            errorMessage = uiState.emailError,
            trailingIcon = {
                if (uiState.emailError != null) {
                    Icon(
                        imageVector = Icons.Rounded.Error,
                        contentDescription = null,
                        tint = Color.Red,
                    )
                } else if (uiState.email.isNotBlank()) {
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

        AppTextField(
            value = uiState.fullName,
            onValueChange = { viewModel.onEvent(RegisterEvent.FullNameChanged(it)) },
            label = stringResource(R.string.full_name),
            isError = uiState.fullNameError != null,
            errorMessage = uiState.fullNameError,
        )

        Spacer(modifier = Modifier.height(12.dp))

        AppTextField(
            value = uiState.username,
            onValueChange = { viewModel.onEvent(RegisterEvent.UsernameChanged(it)) },
            label = stringResource(R.string.username),
            isError = uiState.usernameError != null,
            errorMessage = uiState.usernameError,
            trailingIcon = {
                if (uiState.usernameError != null) {
                    Icon(
                        imageVector = Icons.Rounded.Error,
                        contentDescription = null,
                        tint = Color.Red,
                    )
                } else if (uiState.username.isNotBlank()) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null,
                        tint = Color.Green,
                    )
                }
            },
        )


        Spacer(modifier = Modifier.height(12.dp))

        AppPasswordField(
            value = uiState.password,
            onValueChange = { viewModel.onEvent(RegisterEvent.PasswordChanged(it)) },
            isError = uiState.passwordError != null,
            errorMessage = uiState.passwordError
        )

        Spacer(modifier = Modifier.height(12.dp))

        AppPasswordField(
            value = uiState.confirmPassword,
            label = stringResource(R.string.confirm_password),
            onValueChange = { viewModel.onEvent(RegisterEvent.ConfirmPasswordChanged(it)) },
            isError = uiState.confirmPasswordError != null,
            errorMessage = uiState.confirmPasswordError
        )

        Spacer(modifier = Modifier.height(32.dp))

        LoadingButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.register),
            isLoading = uiState.isLoading,
            onClick = { viewModel.onEvent(RegisterEvent.Submit) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.success) {
            LaunchedEffect(Unit) {
                navController.navigate(AppConstants.HOME_ROUTE) {
                    popUpTo(AppConstants.START_DESTINATION) { inclusive = true }
                }
            }
        }

    }
}