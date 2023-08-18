package com.spendesk.grapes.compose.listitem

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 18/08/2023
 **/
@Composable
fun GrapesInlineInformationItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Text(
            text = title,
            color = GrapesTheme.colors.mainNeutralDarker,
            style = GrapesTheme.typography.bodyRegular,
        )
        Spacer(Modifier.weight(1f))
        Text(
            text = value,
            style = GrapesTheme.typography.bodyRegular,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    GrapesTheme {
        GrapesInlineInformationItem(
            title = "Subscription owner",
            value = "Toto Tata",
        )
    }
}
