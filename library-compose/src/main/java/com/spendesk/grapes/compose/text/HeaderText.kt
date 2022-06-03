package com.spendesk.grapes.compose.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * TODO: This is a test component, please implement a correct one here
 * @author Kélian CLERC
 * @since 02/06/2022
 */
@Composable
fun HeaderText(content: String) {
    Text(text = content, color = GrapesTheme.colors.mainNeutralLight)
}
