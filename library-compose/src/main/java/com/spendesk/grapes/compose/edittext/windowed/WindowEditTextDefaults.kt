package com.spendesk.grapes.compose.edittext.windowed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.spendesk.grapes.compose.theme.GrapesTheme

@Immutable
object WindowEditTextDefaults {
    const val DefaultWindowLength = 4
    const val DefaultMaxLength = 12
    internal val ForbiddenCharRegex = "\\D".toRegex()

    @Composable
    fun defaultTextStyle(): TextStyle = GrapesTheme.typography.bodyM.copy(letterSpacing = 3.sp, color = Color.White, fontSize = 18.sp, fontFeatureSettings = "tnum")
}
