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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            GrapesTextInput(
                modifier = Modifier.fillMaxSize(),
                value = emptyTextFieldValue,
                placeholderValue = "This is a placeholder",
                onValueChange = {
                    emptyTextFieldValue = it
                }
            )

            GrapesTextInput(
                modifier = Modifier.fillMaxSize(),
                value = filledTextFieldValue,
                isError = true,
                placeholderValue = "This is a placeholder",
                onValueChange = {
                    filledTextFieldValue = it
                }
            )

            GrapesTextInput(
                modifier = Modifier.fillMaxSize(),
                value = emptyTextFieldValue,
                enabled = false,
                placeholderValue = "This is a disabled placeholder",
                onValueChange = {
                    emptyTextFieldValue = it
                }
            )

            GrapesTextInput(
                modifier = Modifier.fillMaxSize(),
                value = disabledTextFieldValue,
                enabled = false,
                placeholderValue = "This is a placeholder",
                onValueChange = {
                    disabledTextFieldValue = it
                }
            )
        }
    }
}
// endregion: Preview
