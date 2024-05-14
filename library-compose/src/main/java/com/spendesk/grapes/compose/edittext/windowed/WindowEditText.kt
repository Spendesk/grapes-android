package com.spendesk.grapes.compose.edittext.windowed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 04/07/2022
 */

@Composable
fun WindowEditText(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    windowLength: Int = WindowEditTextDefaults.DefaultWindowLength,
    maxLength: Int = WindowEditTextDefaults.DefaultMaxLength,
    cursorColor: Color = Color.White,
    textStyle: TextStyle = WindowEditTextDefaults.defaultTextStyle(),
    hintChar: Char = '0',
    separatorChar: Char = '-',
) {
    val hint = buildAnnotatedString {
        withStyle(textStyle.toSpanStyle().copy(color = textStyle.color.copy(alpha = 0.2f))) {
            append(hintChar)
        }
    }

    val separator = buildAnnotatedString {
        withStyle(textStyle.toSpanStyle().copy(letterSpacing = 38.sp)) {
            append(separatorChar)
        }
    }

    WindowEditTextBase(
        text = text,
        onTextChange = onTextChange,
        modifier = modifier
            .padding(GrapesTheme.dimensions.spacing3)
            .fillMaxWidth()
            .background(GrapesTheme.colors.primaryDark, RoundedCornerShape(12.dp))
            .padding(vertical = GrapesTheme.dimensions.spacing3),
        windowLength = windowLength,
        maxLength = maxLength,
        cursorColor = cursorColor,
        textStyle = textStyle,
        hint = hint,
        separator = separator
    )
}

@Composable
internal fun WindowEditTextBase(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    windowLength: Int = WindowEditTextDefaults.DefaultWindowLength,
    maxLength: Int = WindowEditTextDefaults.DefaultMaxLength,
    cursorColor: Color = Color.White,
    textStyle: TextStyle = WindowEditTextDefaults.defaultTextStyle(),
    hint: AnnotatedString = AnnotatedString("0"),
    separator: AnnotatedString = AnnotatedString("-"),
) {

    val visualTransformation = remember(windowLength, maxLength, hint, separator, textStyle) {
        WindowedVisualTransformation(windowLength = windowLength, originalStringMaxLength = maxLength, separation = separator, hintAnnotatedString = hint)
    }
    val formattedText = sanitizeWindowedEditTextContent(text, maxLength)

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        BasicTextField(
            value = formattedText,
            onValueChange = { newValue ->
                onTextChange(sanitizeWindowedEditTextContent(newValue, maxLength))
            },
            textStyle = textStyle,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, autoCorrect = false),
            cursorBrush = SolidColor(cursorColor),
            visualTransformation = visualTransformation
        )
    }
}

private fun sanitizeWindowedEditTextContent(text: String, maxLength: Int): String {
    return text.replace(WindowEditTextDefaults.ForbiddenCharRegex, "").run {
        if (this.length > maxLength) {
            this.substring(0 until maxLength)
        } else {
            this
        }
    }
}

@Preview
@Composable
fun WindowEditTextPreview() {
    GrapesTheme {
        var content by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrapesTheme.colors.primaryLight),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Try to select this text to paste it in editText", color = GrapesTheme.colors.mainWhite)
                Spacer(modifier = Modifier.size(4.dp))
                SelectionContainer {
                    Text(text = "1234-5678-9101", color = GrapesTheme.colors.mainWhite)
                }
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = "Text In edit text")
                Text(text = content)
            }
            WindowEditText(
                text = content, onTextChange = { content = it }
            )
        }
    }
}
