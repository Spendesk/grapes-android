package com.spendesk.grapes.compose.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 06/01/2023, Fri
 **/

@ExperimentalMaterial3Api
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
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {

    val textColor = colors.textColor(enabled).value
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    Column(
        modifier = modifier.width(IntrinsicSize.Min)
    ) {
        GrapesCoreTextField(
            value = value,
            placeholderValue = placeholderValue,
            modifier = Modifier,
            onValueChange = onValueChange,
            enabled = enabled,
            textStyle = mergedTextStyle,
            isError = isError,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            singleLine = singleLine,
            colors = colors,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )

        if (helperText != null && helperText.isNotEmpty()) {
            GrapesHelperText(
                text = helperText,
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                isError = isError
            )
        }
    }
}

@Composable
internal fun GrapesHelperText(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = GrapesTheme.typography.bodyXs,
    isError: Boolean = false,
    colors: GrapesTextFieldColors = GrapesTextFieldDefaults.textFieldColors(),
    contentPadding: PaddingValues = GrapesTextFieldDefaults.textFieldPadding(),
) {
    val textColor = colors.helperTextColor(enabled, isError).value
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    val layoutDirection = LocalLayoutDirection.current

    val topPadding = contentPadding.calculateTopPadding()
    val endPadding = contentPadding.calculateEndPadding(layoutDirection)
    val startPadding = contentPadding.calculateStartPadding(layoutDirection)

    Box(
        modifier = modifier
            .padding(start = startPadding, end = endPadding),
        propagateMinConstraints = true,
    ) {
        Text(
            text = text,
            modifier = Modifier
                .paddingFromBaseline(top = topPadding),
            style = mergedTextStyle,
        )
    }
}

@ExperimentalMaterial3Api
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
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
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
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                container = {
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
