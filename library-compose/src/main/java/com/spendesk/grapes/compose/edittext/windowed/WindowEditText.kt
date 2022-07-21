package com.spendesk.grapes.compose.edittext.windowed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.AnnotatedString
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

private const val DefaultMaxLength = 12
private val ForbiddenCharRegex = "[^\\d]".toRegex()


@Composable
fun WindowEditTextBase(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    maxLength: Int = DefaultMaxLength,
    cursorColor: Color = GrapesTheme.colors.mainWhite,
    textStyle: TextStyle = GrapesTheme.typography.bodyM.copy(letterSpacing = 3.sp, color = GrapesTheme.colors.mainWhite, fontSize = 18.sp)
) {

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
        )
    }
}

private fun sanitizeWindowedEditTextContent(text: String, maxLength: Int): String {
    return text.replace(ForbiddenCharRegex, "").run {
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
                .background(GrapesTheme.colors.mainPrimaryLight),
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
            WindowEditTextBase(
                text = content, onTextChange = { content = it }
            )
        }
    }
}
