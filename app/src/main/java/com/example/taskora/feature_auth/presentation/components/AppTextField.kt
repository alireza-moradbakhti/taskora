package com.example.taskora.feature_auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.taskora.R

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            isError = isError,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            trailingIcon = trailingIcon,
            textStyle = TextStyle(
                color = if (isError) colorResource(R.color.error_color_2)
                else colorResource(R.color.black_shade6)
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isError) colorResource(R.color.error_color) else colorResource(R.color.primary_color),
                unfocusedBorderColor = if (isError) colorResource(R.color.error_color) else colorResource(R.color.black_shade4),
                focusedLabelColor = colorResource(R.color.primary_color),
                unfocusedLabelColor = colorResource(R.color.black_shade4),
                errorLabelColor = colorResource(R.color.error_color),
                errorBorderColor = colorResource(R.color.error_color),
                cursorColor = colorResource(R.color.primary_cursor_color),
                focusedTextColor = if (isError) colorResource(R.color.error_color_2) else colorResource(R.color.black_shade6),
                unfocusedTextColor = if (isError) colorResource(R.color.error_color_2) else colorResource(R.color.black_shade6),
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        )
        if (isError && !errorMessage.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = colorResource(R.color.error_color),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}