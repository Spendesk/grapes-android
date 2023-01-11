package com.spendesk.grapes.compose.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 06/01/2023, Fri
 **/

@ExperimentalMaterialApi
@Composable
internal fun GrapesBaseTextField(
    value: TextFieldValue,
    placeholderValue: String,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    enabled: Boolean = true,
    textStyle: TextStyle = GrapesTheme.typography.bodyRegular,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    colors: GrapesTextFieldColors = GrapesTextFieldDefaults.textFieldColors(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val textColor = colors.textColor(enabled).value
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    GrapesCoreTextField(
        value = value,
        placeholderValue = placeholderValue,
        onValueChange = onValueChange,
        modifier = Modifier,
        enabled = enabled,
        textStyle = mergedTextStyle,
        isError = isError,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        colors = colors,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource
    )
}

@ExperimentalMaterialApi
@Composable
private fun GrapesCoreTextField(
    value: TextFieldValue,
    placeholderValue: String,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = TextStyle.Default,
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    colors: GrapesTextFieldColors = GrapesTextFieldDefaults.textFieldColors(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    BasicTextField(
        modifier = modifier
            .defaultMinSize(
                minWidth = GrapesTextFieldDefaults.MinWidth,
                minHeight = GrapesTextFieldDefaults.MinHeight,
            )
            .shadow(
                elevation = GrapesTextFieldDefaults.Elevation,
                shape = GrapesTextFieldDefaults.TextFieldShape,
            )
            .background(
                color = colors.backgroundColor(enabled).value,
                shape = GrapesTextFieldDefaults.TextFieldShape
            ),
        enabled = enabled,
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        cursorBrush = SolidColor(colors.cursorColor(isError).value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = value.text,
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = singleLine,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                contentPadding = GrapesTextFieldDefaults.textFieldPadding(),
                placeholder = {
                    Text(
                        text = placeholderValue,
                        style = GrapesTheme.typography.bodyRegular,
                        color = colors.placeholderColor(enabled = enabled).value,
                    )
                },
                border = {
                    GrapesTextFieldDefaults.BorderBox(
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors
                    )
                }
            )
        }
    )
}
