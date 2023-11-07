package com.spendesk.grapes.compose.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author RomainGF
 * @since 07/11/2023
 **/
private val searchInputTextPaddingVertical = 11.dp
private val searchInputTextPaddingHorizontal = 8.dp

@Composable
@Suppress("LongParameterList")
@OptIn(ExperimentalMaterial3Api::class)
fun GrapesSearchInputSecondary(
    value: String,
    placeholderValue: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    textStyle: TextStyle = GrapesTheme.typography.bodyRegular,
    colors: GrapesTextFieldColors = GrapesTextFieldDefaults.textFieldColors(
        backgroundColor = GrapesTheme.colors.mainNeutralLighter,
        leadingIconColor = GrapesTheme.colors.mainNeutralNormal,
        trailingIconColor = GrapesTheme.colors.mainNeutralNormal,
    ),
    isError: Boolean = false,
    onClick: (() -> Unit)? = null,
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
        modifier = modifier.heightIn(min = 40.dp, max = 40.dp),
        helperText = helperText,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        textStyle = textStyle,
        colors = colors,
        isError = isError,
        onClick = onClick,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        textPadding = GrapesTextFieldDefaults.textFieldPadding(
            bottom = searchInputTextPaddingVertical,
            end = searchInputTextPaddingHorizontal,
            start = searchInputTextPaddingHorizontal,
            top = searchInputTextPaddingVertical,
        ),
    )
}

@Preview
@Composable
private fun Preview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            GrapesSearchInputSecondary(
                value = "",
                placeholderValue = "Search",
                onValueChange = {},
                leadingIcon = { Icon(painterResource(R.drawable.ic_neutral), null) },
                trailingIcon = { Icon(painterResource(R.drawable.ic_add), null) },
            )
            GrapesSearchInputSecondary(
                value = "Value",
                placeholderValue = "Search",
                onValueChange = {},
                leadingIcon = { Icon(painterResource(R.drawable.ic_neutral), null) },
                trailingIcon = { Icon(painterResource(R.drawable.ic_add), null) },
            )
        }
    }
}
