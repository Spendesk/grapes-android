package com.spendesk.grapes.compose.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.icons.GrapesIcon
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 05/01/2023, Thu
 **/
@ExperimentalMaterial3Api
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
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
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
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}

@ExperimentalMaterial3Api
@Composable
fun GrapesTextInput(
    value: String,
    placeholderValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    enabled: Boolean = true,
    textStyle: TextStyle = GrapesTheme.typography.bodyRegular,
    colors: GrapesTextFieldColors = GrapesTextFieldDefaults.textFieldColors(),
    isError: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
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
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}

// region: Preview

@ExperimentalMaterial3Api
@Composable
@Preview
fun PreviewGrapesTextField() {

    var isEnabled by remember { mutableStateOf(true) }

    var showLeadingIcon by remember { mutableStateOf(false) }
    var showTrailingIcon by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = ""
            )
        )
    }
    var helperText by remember { mutableStateOf("") }

    // Option States
    var hasTextValue by remember { mutableStateOf(false) }
    var hasHelperText by remember { mutableStateOf(false) }
    var canToggleError by remember { mutableStateOf(isEnabled) }

    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrapesTheme.colors.mainBackground)
                .verticalScroll(rememberScrollState())
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
        ) {

            Column(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
                    .background(GrapesTheme.colors.mainNeutralLight)
                    .padding(8.dp)


            ) {
                PreviewOptionsRow(
                    options = {
                        PreviewRowOptionSwitch(
                            label = "Show text input value",
                            isChecked = hasTextValue,
                            onCheckedChange = { isChecked ->
                                hasTextValue = isChecked
                                textFieldValue = TextFieldValue(
                                    text = "This is a text input value".takeIf { isChecked }.orEmpty()
                                )
                            }
                        )

                        PreviewRowOptionSwitch(
                            label = "Show helper text",
                            isChecked = hasHelperText,
                            onCheckedChange = { isChecked ->
                                hasHelperText = isChecked
                                helperText = "Helper text".takeIf { isChecked }.orEmpty()
                            }
                        )
                    }
                )

                PreviewOptionsRow(
                    options = {
                        PreviewRowOptionSwitch(
                            label = "Is Enabled",
                            isChecked = isEnabled,
                            onCheckedChange = { isChecked ->
                                canToggleError = isChecked
                                if (isChecked.not() && isError) {
                                    isError = false
                                }

                                isEnabled = isChecked
                            }
                        )

                        PreviewRowOptionSwitch(
                            label = "Is Error",
                            isEnable = canToggleError,
                            isChecked = isError,
                            onCheckedChange = { isChecked -> isError = isChecked }
                        )
                    }
                )

                PreviewOptionsRow(
                    options = {
                        PreviewRowOptionSwitch(
                            label = "Leading Icon",
                            isChecked = showLeadingIcon,
                            onCheckedChange = { isChecked -> showLeadingIcon = isChecked }
                        )

                        PreviewRowOptionSwitch(
                            label = "Trailing Icon",
                            isChecked = showTrailingIcon,
                            onCheckedChange = { isChecked -> showTrailingIcon = isChecked }
                        )
                    }
                )
            }

            // ----
            GrapesTextInput(
                modifier = Modifier.fillMaxWidth(),
                value = textFieldValue,
                placeholderValue = "This is a placeholder",
                enabled = isEnabled,
                helperText = helperText,
                isError = isError,
                onValueChange = { textFieldValue = it },
                leadingIcon = { if (showLeadingIcon) GrapesIcon(icon = R.drawable.ic_block, Modifier.size(18.dp)) },
                trailingIcon = { if (showTrailingIcon) GrapesIcon(icon = R.drawable.ic_block, Modifier.size(18.dp)) }
            )
        }
    }
}

@Composable
private fun PreviewOptionsRow(
    options: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        options()
    }
}

@Composable
private fun PreviewRowOptionSwitch(
    label: String,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    isChecked: Boolean = false,
    onCheckedChange: (isChecked: Boolean) -> Unit = {},
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .toggleable(
                role = Role.Switch,
                enabled = isEnable,
                value = isChecked,
                onValueChange = onCheckedChange,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            text = label,
            style = GrapesTheme.typography.bodyRegular,
        )
        Switch(
            modifier = Modifier,
            checked = isChecked,
            enabled = isEnable,
            onCheckedChange = null
        )
    }
}

// endregion: Preview
