package com.spendesk.grapes.compose.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 24/02/2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderValue: String,
    showPasswordContentDescription: String,
    hidePasswordContentDescription: String,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    GrapesTextInput(
        modifier = modifier,
        value = value,
        placeholderValue = placeholderValue,
        onValueChange = onValueChange,
        helperText = helperText,
        enabled = enabled,
        isError = isError,
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
            val description = if (isPasswordVisible) hidePasswordContentDescription else showPasswordContentDescription

            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(imageVector = icon, contentDescription = description)
            }
        }
    )
}

@Preview
@Composable
private fun PasswordTextInputPreview() {
    var passwordContent by remember {
        mutableStateOf("")
    }

    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), Arrangement.spacedBy(16.dp)
        ) {
            PasswordTextInput(
                value = passwordContent,
                onValueChange = { passwordContent = it },
                placeholderValue = "Enter your password",
                showPasswordContentDescription = "Show password",
                hidePasswordContentDescription = "Hide password"
            )
        }
    }
}
