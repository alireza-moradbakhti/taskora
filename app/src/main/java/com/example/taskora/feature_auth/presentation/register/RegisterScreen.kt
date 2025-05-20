package com.example.taskora.feature_auth.presentation.register

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
fun RegisterScreen(navController: NavController?) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(text = "Register", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController?.navigate(AppConstants.START_DESTINATION)
        }) {
            Text(text = "Already have account?")
        }
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = {
            navController?.navigate(AppConstants.FORGET_PASSWORD_ROUTE)
        }) {
            Text(text = "forget pass?")
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRegister() {
    RegisterScreen(
        null
    )
}