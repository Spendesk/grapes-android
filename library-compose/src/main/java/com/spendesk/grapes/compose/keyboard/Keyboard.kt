package com.spendesk.grapes.compose.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.button.GrapesButton
import com.spendesk.grapes.compose.button.GrapesButtonConfiguration
import com.spendesk.grapes.compose.list.Suggestions
import com.spendesk.grapes.compose.list.SuggestionsContent
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 03/11/2022
 */
private val KEYBOARD_NUMBERS = List(10) {
    val content = (it + 1) % 10 // To make sure numbers are 1,2,3,4,5,6,7,8,9 AND 0 and not 0,1,2,3,4,5,6,7,8 and 9
    KeyboardEntry(label = "$content", rawValue = "$content")
}.windowed(size = 3, step = 3, partialWindows = true)

private data class KeyboardEntry(val label: String, val rawValue: String)

@Composable
private fun Keyboard(
    onNewInput: (String) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    topOfKeyboardSlot: @Composable () -> Unit = {},
    extraButtonSlot: @Composable RowScope.() -> Unit = { this.DefaultExtraKeyboardSlot() },
    buttonSlot: @Composable RowScope.(KeyboardEntry) -> Unit = { entry -> this.DefaultKeyboardNumberButton(entry.label, entry.rawValue) { onNewInput(entry.rawValue) } },
    deleteSlot: @Composable RowScope.() -> Unit = { -> this.DefaultDeleteKeyboardSlot(onDelete) },
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        topOfKeyboardSlot()
        KEYBOARD_NUMBERS.forEachIndexed { rowIndex, keyboardEntries ->
            val isLastRow = rowIndex == KEYBOARD_NUMBERS.size - 1
            Row(modifier = Modifier.wrapContentHeight()) {
                if (isLastRow) {
                    extraButtonSlot()
                }

                keyboardEntries.forEach { keyboardEntry ->
                    buttonSlot(keyboardEntry)
                }

                if (isLastRow) {
                    deleteSlot()
                }
            }
        }
    }
}

@Composable
private fun RowScope.DefaultKeyboardNumberButton(
    label: String,
    value: String,
    onClick: (String) -> Unit
) {
    GrapesButton(
        text = label,
        configuration = GrapesButtonConfiguration.TEXT,
        modifier = Modifier.weight(1f),
        onClick = { onClick(value) }
    )
}

@Composable
private fun RowScope.DefaultExtraKeyboardSlot() {
    Spacer(modifier = Modifier.weight(1f))
}

@Composable
private fun RowScope.DefaultDeleteKeyboardSlot(onClick: () -> Unit) {
    GrapesButton(
        icon = R.drawable.ic_delete_return,
        configuration = GrapesButtonConfiguration.TEXT,
        modifier = Modifier.weight(1f),
        onClick = onClick
    )
}

@Composable
fun SuggestionsKeyboard(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    suggestions: List<SuggestionsContent> = listOf()
) {
    var isFromSuggestion by remember { mutableStateOf(false) }

    fun handleInput(input: String, isInputFromSuggestion: Boolean): String {
        return when {
            !isInputFromSuggestion && isFromSuggestion -> input // when existing value is from suggestion, a click on a keyboard button replaces it
            !isInputFromSuggestion && !isFromSuggestion -> {  // when existing value is not from suggestion, a click on a keyboard button has different cases
                when {
                    value.isEmpty() && input == "0" -> value // if input is zero as first char, keep current state
                    value == "0" -> input // ekse if existing value is 0, replaces it with input
                    else -> value + input // else append input to existing value
                }
            }

            else -> input // else if input is from suggestion, replace current value with input
        }
    }

    fun onNewInput(input: String, isInputFromSuggestion: Boolean) {
        val computedOutput = handleInput(input, isInputFromSuggestion)
        isFromSuggestion = isInputFromSuggestion
        onValueChanged(computedOutput)
    }

    Keyboard(
        modifier = modifier,
        onNewInput = { onNewInput(input = it, isInputFromSuggestion = false) },
        onDelete = {
            onValueChanged(value.dropLast(1))
            isFromSuggestion = false
        },
        topOfKeyboardSlot = {
            if (suggestions.isNotEmpty()) {
                Column(modifier.fillMaxWidth()) {
                    Suggestions(
                        suggestions = suggestions,
                        onSuggestionClicked = { onNewInput(input = it.rawValue, isInputFromSuggestion = true) }
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(GrapesTheme.colors.mainNeutralLight)
                    )
                }
            }
        }
    )
}

@Preview(device = "spec:width=1280dp,height=800dp,dpi=480,orientation=portrait")
@Composable
fun KeyboardPreview() {
    var result by remember { mutableStateOf("0") }
    val scrollState = rememberScrollState()
    GrapesTheme {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .width(800.dp), verticalArrangement = Arrangement.spacedBy(32.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No extra button", style = GrapesTheme.typography.titleM)
            Text(text = result, style = GrapesTheme.typography.bodyXxl)
            SuggestionsKeyboard(
                value = result,
                onValueChanged = { result = it.ifEmpty { "0" } },
                suggestions = listOf(
                    SuggestionsContent("$10", "10"),
                    SuggestionsContent("$20", "20"),
                ),
            )
            SuggestionsKeyboard(
                value = result,
                onValueChanged = { result = it.ifEmpty { "0" } },
                modifier = Modifier.width(300.dp),
                suggestions = listOf(
                    SuggestionsContent("$10", "10"),
                    SuggestionsContent("$20", "20"),
                )
            )
        }
    }
}
