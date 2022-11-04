package com.spendesk.grapes.compose.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.button.GrapesButton
import com.spendesk.grapes.compose.button.GrapesButtonConfiguration
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 01/11/2022
 */

private const val DEFAULT_MAX_LINES = 10

@Composable
fun MultilineEditText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    maxLines: Int = DEFAULT_MAX_LINES,
    enabled: Boolean = true
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = GrapesTheme.typography.bodyRegular.copy(color = GrapesTheme.colors.mainComplementary),
        maxLines = maxLines,
        singleLine = false,
        modifier = modifier,
        enabled = enabled,
        decorationBox = @Composable { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = GrapesTheme.typography.bodyRegular,
                    color = GrapesTheme.colors.mainNeutralDark
                )
            } else {
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
fun MultilineEditTextPreview() {
    var editTextContent by remember {
        mutableStateOf("")
    }

    GrapesTheme {
        Column(
            Modifier
                .fillMaxSize()
                .imePadding()
        ) {
            MultilineEditText(
                value = editTextContent,
                onValueChange = { editTextContent = it },
                placeholder = "test",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(GrapesTheme.dimensions.paddingNormal)
            )
            GrapesButton(text = "Test button", configuration = GrapesButtonConfiguration.PRIMARY)
        }
    }
}
