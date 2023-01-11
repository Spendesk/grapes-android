package com.spendesk.grapes.compose.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 05/01/2023, Thu
 **/
@ExperimentalMaterialApi
@Composable
fun GrapesTextInput(
    value: TextFieldValue,
    placeholderValue: String,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    enabled: Boolean = true,
    textStyle: TextStyle = GrapesTheme.typography.bodyRegular,
    colors: GrapesTextFieldColors = GrapesTextFieldDefaults.textFieldColors(),
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    GrapesBaseTextField(
        value = value,
        placeholderValue = placeholderValue,
        onValueChange = onValueChange,
        modifier = modifier,
        helperText = helperText,
        enabled = enabled,
        textStyle = textStyle,
        colors = colors,
        isError = isError,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        visualTransformation = visualTransformation,
    )
}

// region: Preview

@ExperimentalMaterialApi
@Composable
@Preview
fun PreviewGrapesTextField() {
    var emptyTextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }

    var filledTextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = "This is a text value"
            )
        )
    }

    var disabledTextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = "This is a disabled text value"
            )
        )
    }

    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrapesTheme.colors.mainBackground)
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            GrapesTextInput(
                modifier = Modifier,
                value = emptyTextFieldValue,
                placeholderValue = "This is a placeholder",
                helperText = "This is helper text",
                onValueChange = {
                    emptyTextFieldValue = it
                }
            )

            GrapesTextInput(
                modifier = Modifier,
                value = filledTextFieldValue,
                isError = true,
                placeholderValue = "This is a placeholder",
                helperText = "This is an error helper text",
                onValueChange = {
                    filledTextFieldValue = it
                }
            )

            GrapesTextInput(
                modifier = Modifier,
                value = emptyTextFieldValue,
                enabled = false,
                placeholderValue = "This is a disabled placeholder",
                onValueChange = {
                    emptyTextFieldValue = it
                }
            )

            GrapesTextInput(
                modifier = Modifier,
                value = disabledTextFieldValue,
                enabled = false,
                placeholderValue = "This is a disabled placeholder",
                helperText = "This is disabled helper text",
                onValueChange = {
                    disabledTextFieldValue = it
                }
            )
        }
    }
}
// endregion: Preview
