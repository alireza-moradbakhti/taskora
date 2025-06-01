package com.example.taskora.feature_auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
    ) {

        Spacer(Modifier.height(24.dp))
        Image(
            painter = painterResource(R.drawable.ic_login),
            contentDescription = "login icon",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(10.dp)),
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.sign_in),
            style = MaterialTheme.typography.headlineMedium,
            color = colorResource(R.color.primary_color),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        AppTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(LoginEvent.EmailChanged(it)) },
            label = stringResource(R.string.email),
            isError = state.emailError != null,
            errorMessage = state.emailError,
            trailingIcon = {
                if (state.emailError != null) {
                    Icon(
                        imageVector = Icons.Rounded.Error,
                        contentDescription = null,
                        tint = colorResource(R.color.error_color),
                    )
                } else if (state.email.isNotBlank()) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null,
                        tint = colorResource(R.color.primary_color),
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

        Spacer(modifier = Modifier.height(24.dp))

        LoadingButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.login),
            isLoading = state.isLoading,
            onClick = { viewModel.onEvent(LoginEvent.Submit) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.sign_up_desc),
                color = colorResource(R.color.black_shade_6),
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = stringResource(R.string.sign_up),
                color = colorResource(R.color.secondary_dark_color),
                modifier = Modifier.clickable {
                    navController.navigate(AppConstants.REGISTER_SCREEN_ROUTE)
                })

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