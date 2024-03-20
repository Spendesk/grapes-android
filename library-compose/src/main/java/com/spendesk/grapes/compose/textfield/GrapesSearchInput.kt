package com.spendesk.grapes.compose.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
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
@OptIn(ExperimentalMaterial3Api::class)
fun GrapesSearchInputPrimary(
    value: String,
    placeholder: String,
    clearContentDescription: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    onClick: (() -> Unit)? = null,
    onKeyboardSearch: (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    GrapesBaseTextField(
        value = value,
        placeholderValue = placeholder,
        onValueChange = onValueChange,
        modifier = modifier,
        helperText = helperText,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = true,
        textStyle = GrapesTheme.typography.bodyL,
        colors = GrapesTextFieldDefaults.textFieldColors(
            backgroundColor = GrapesTheme.colors.structureSurface,
            leadingIconColor = GrapesTheme.colors.neutralDarker,
            trailingIconColor = GrapesTheme.colors.neutralDarker,
            textColor = GrapesTheme.colors.neutralDarker,
            placeholderColor = GrapesTheme.colors.neutralNormal,
            unfocusedBorderColor = GrapesTheme.colors.neutralLight,
            focusedBorderColor = GrapesTheme.colors.neutralNormal,
        ),
        isError = isError,
        onClick = onClick,
        keyboardActions = KeyboardActions(
            onSearch = {
                onKeyboardSearch?.invoke()
                keyboardController?.hide()
            },
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Image(
                        painter = painterResource(R.drawable.ic_clear_round),
                        contentDescription = clearContentDescription,
                    )
                }
            }
        },
        textPadding = GrapesTextFieldDefaults.textFieldPadding(
            bottom = searchInputTextPaddingVertical,
            end = searchInputTextPaddingHorizontal,
            start = searchInputTextPaddingHorizontal,
            top = searchInputTextPaddingVertical,
        ),
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GrapesSearchInputSecondary(
    value: String,
    placeholder: String,
    clearContentDescription: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier,
    helperText: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    onClick: (() -> Unit)? = null,
    onKeyboardSearch: (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    GrapesBaseTextField(
        value = value,
        placeholderValue = placeholder,
        onValueChange = onValueChange,
        modifier = modifier.height(GrapesTheme.dimensions.sizing6),
        helperText = helperText,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = true,
        textStyle = GrapesTheme.typography.bodyL,
        colors = GrapesTextFieldDefaults.textFieldColors(
            backgroundColor = GrapesTheme.colors.structureBackground,
            leadingIconColor = GrapesTheme.colors.neutralNormal,
            trailingIconColor = GrapesTheme.colors.neutralNormal,
            textColor = GrapesTheme.colors.neutralDarker,
            placeholderColor = GrapesTheme.colors.neutralNormal,
            unfocusedBorderColor = GrapesTheme.colors.neutralLight,
            focusedBorderColor = GrapesTheme.colors.neutralNormal,
        ),
        isError = isError,
        onClick = onClick,
        keyboardActions = KeyboardActions(
            onSearch = {
                onKeyboardSearch?.invoke()
                keyboardController?.hide()
            },
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = onClear) {
                    Image(
                        painter = painterResource(R.drawable.ic_clear_round),
                        contentDescription = clearContentDescription,
                    )
                }
            }
        },
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
            GrapesSearchInputPrimary(
                value = "",
                placeholder = "Search",
                clearContentDescription = "",
                onValueChange = {},
                onClear = {},
                leadingIcon = { Icon(painterResource(R.drawable.ic_close), null) },
            )
            GrapesSearchInputPrimary(
                value = "Value",
                placeholder = "Search",
                clearContentDescription = "",
                onValueChange = {},
                onClear = {},
                leadingIcon = { Icon(painterResource(R.drawable.ic_close), null) },
            )
            GrapesSearchInputSecondary(
                value = "",
                placeholder = "Search",
                clearContentDescription = "",
                onValueChange = {},
                onClear = {},
                leadingIcon = { Icon(painterResource(R.drawable.ic_close), null) },
            )
            GrapesSearchInputSecondary(
                value = "Value",
                placeholder = "Search",
                clearContentDescription = "",
                onValueChange = {},
                onClear = {},
                leadingIcon = { Icon(painterResource(R.drawable.ic_close), null) },
            )
        }
    }
}
