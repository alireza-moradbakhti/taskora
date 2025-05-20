package com.example.taskora.feature_auth.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskora.utils.AppConstants

@Composable
fun LoginScreen(navController: NavController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "loginScreen",
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController?.navigate(AppConstants.REGISTER_SCREEN_ROUTE)
        }
        ) {
            Text(text = "Go to Register")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    LoginScreen(
        navController = null
    )
}