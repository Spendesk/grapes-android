package com.spendesk.grapes.compose.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 18/04/2023
 */
@Composable
fun GrapesPinTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxNumberOfChars: Int = 4,
    isError: Boolean = false,
    isEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    // The following region is just a copy paste from GrapesBaseTextField to be able to expose a string but to handle TextFieldValue inside the component
    // region String to TextFieldValue converter
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }
    // Holds the latest TextFieldValue that BasicTextField was recomposed with. We couldn't simply
    // pass `TextFieldValue(text = value)` to the CoreTextField because we need to preserve the
    // composition.
    val textFieldValue = textFieldValueState.copy(text = value)

    SideEffect {
        if (textFieldValue.selection != textFieldValueState.selection ||
            textFieldValue.composition != textFieldValueState.composition
        ) {
            textFieldValueState = textFieldValue
        }
    }
    // Last String value that either text field was recomposed with or updated in the onValueChange
    // callback. We keep track of it to prevent calling onValueChange(String) for same String when
    // CoreTextField's onValueChange is called multiple times without recomposition in between.
    var lastTextValue by remember(value) { mutableStateOf(value) }

    SideEffect {
        if (textFieldValueState.text != lastTextValue) {
            textFieldValueState = textFieldValueState.copy(text = value)
        }
    }
    // endregion String to TextFieldValue converter
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = textFieldValueState,
        onValueChange = { newTextFieldValueState ->
            val selectedIndex = computeNextSelection(newTextFieldValueState, maxNumberOfChars)
            // The following region is just a copy paste from GrapesBaseTextField to be able to expose a string but to handle TextFieldValue inside the component
            // region String to TextFieldValue converter
            textFieldValueState = newTextFieldValueState.copy(selection = selectedIndex)

            val stringChangedSinceLastInvocation = lastTextValue != newTextFieldValueState.text
            lastTextValue = newTextFieldValueState.text

            if (stringChangedSinceLastInvocation) {
                onValueChanged(newTextFieldValueState.text.trimToMaxSize(maxNumberOfChars))
            }
            // endregion String to TextFieldValue converter
        },
        enabled = isEnabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        decorationBox = {
            GrapesPinTextFieldDecorationBox(
                maxNumberOfChars = maxNumberOfChars,
                currentlySelectedIndex = textFieldValueState.selection.start,
                lastTextValue = lastTextValue,
                isError = isError,
                isEnabled = isEnabled
            ) { charIndexClicked ->
                textFieldValueState = textFieldValueState.copy(selection = TextRange(charIndexClicked, charIndexClicked + 1))
                focusRequester.requestFocus()
            }
        }
    )
}

private fun computeNextSelection(newTextFieldValueState: TextFieldValue, maxNumberOfChars: Int): TextRange {
    val isInputBiggerThanMaxRange = newTextFieldValueState.text.length >= maxNumberOfChars
    val isSelectionOnLastLetterOfInput = newTextFieldValueState.selection.start == maxNumberOfChars

    return if (isInputBiggerThanMaxRange && isSelectionOnLastLetterOfInput) {
        TextRange(maxNumberOfChars - 1, maxNumberOfChars)
    } else {
        TextRange(newTextFieldValueState.selection.start, newTextFieldValueState.selection.start + 1)
    }
}


private fun String.trimToMaxSize(maxStringSize: Int): String {
    return if (length > maxStringSize) slice(0 until maxStringSize) else this
}


@Preview
@Composable
fun PinTextInputPreview() {
    var content by remember {
        mutableStateOf("")
    }

    GrapesTheme {
        Column(
            modifier = Modifier
                .background(GrapesTheme.colors.structureBackground)
                .fillMaxWidth()
                .padding(GrapesTheme.dimensions.spacing3),
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing3)
        ) {
            GrapesPinTextField("", {})
            GrapesPinTextField("12", {})
            GrapesPinTextField("", {}, isEnabled = false)
            GrapesPinTextField("12", {}, isEnabled = false)
            GrapesPinTextField("", {}, isError = true)
            GrapesPinTextField("12", {}, isError = true, modifier = Modifier.fillMaxWidth())
            GrapesPinTextField(content, { content = it })
            Text(text = "Content from callback $content")
        }
    }
}
