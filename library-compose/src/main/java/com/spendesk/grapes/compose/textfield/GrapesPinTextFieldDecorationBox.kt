package com.spendesk.grapes.compose.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 19/04/2023
 */
@Composable
fun GrapesPinTextFieldDecorationBox(
    maxNumberOfChars: Int,
    currentlySelectedIndex: Int,
    lastTextValue: String,
    isError: Boolean,
    isEnabled: Boolean,
    onClick: (charIndexClicked: Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(maxNumberOfChars) { charIndex ->
            val isFocused = currentlySelectedIndex == charIndex
            val char = when {
                charIndex >= lastTextValue.length -> ""
                else -> lastTextValue[charIndex].toString()
            }

            PinText(
                modifier = Modifier.padding(horizontal = GrapesTheme.dimensions.paddingXSmall),
                char = char,
                isError = isError,
                isEnabled = isEnabled,
                isFocused = isFocused,
                onClick = { onClick(charIndex) }
            )
        }
    }
}

@Composable
private fun PinText(
    char: String,
    isError: Boolean,
    isEnabled: Boolean,
    isFocused: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(GrapesPintTextFieldDefaults.PinCharWidth)
            .height(GrapesPintTextFieldDefaults.PinCharHeight)
            .background(GrapesTheme.colors.mainWhite, shape = GrapesTheme.shapes.small)
            .border(
                width = GrapesPintTextFieldDefaults.PinCharBorderWidth,
                color = GrapesPintTextFieldDefaults
                    .pinFieldColors()
                    .borderColor(isEnabled = isEnabled, isError = isError, isSelected = isFocused),
                shape = GrapesTheme.shapes.small
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { onClick() }
                )
            }
            .padding(GrapesPintTextFieldDefaults.PinCharPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = char,
            style = GrapesTheme.typography.bodyM,
            color = GrapesPintTextFieldDefaults.pinFieldColors().textColor(isEnabled = isEnabled, isError = isError, isSelected = isFocused),
            textAlign = TextAlign.Center
        )
    }
}
