package com.spendesk.grapes.compose.actionmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : RomainGF
 * @since : 09/10/2023
 **/
private val iconMaxSize = 16.dp
private val verticalInnerPadding = 20.dp
private const val ACTION_TEXT_MAX_LINES = 2

/**
 * ActionMenuItem is a button with an icon and a text.
 *
 * @param text Text to display on the button
 * @param icon Slot for the icon to display on the left of the text. Size is constrained to 16dp.
 * @param onClick Callback when the button is clicked
 * @param modifier Modifier
 * @param enabled Whether the button is enabled or not
 */
@Composable
fun ActionMenuItem(
    text: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = GrapesTheme.colors.neutralLightest,
            contentColor = GrapesTheme.colors.primaryNormal,
            disabledContainerColor = GrapesTheme.colors.neutralLightest,
            disabledContentColor = GrapesTheme.colors.neutralNormal,
        ),
        contentPadding = PaddingValues(
            start = GrapesTheme.dimensions.spacing4,
            end = GrapesTheme.dimensions.spacing3,
            top = verticalInnerPadding,
            bottom = verticalInnerPadding,
        ),
        shape = GrapesTheme.shapes.shape2,
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ActionMenuItemIcon(
                enabled = enabled,
                icon = icon,
            )
            Spacer(Modifier.width(GrapesTheme.dimensions.spacing4))
            Text(
                text = text,
                maxLines = ACTION_TEXT_MAX_LINES,
                overflow = TextOverflow.Ellipsis,
                style = GrapesTheme.typography.titleM,
                modifier = Modifier.weight(1f),
            )
            if (enabled) {
                Spacer(Modifier.width(GrapesTheme.dimensions.spacing3))
                Icon(
                    painter = painterResource(R.drawable.ic_chevron_right),
                    contentDescription = null,
                    tint = GrapesTheme.colors.primaryLighter,
                )
            }
        }
    }
}

@Composable
private fun ActionMenuItemIcon(
    enabled: Boolean,
    icon: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.sizeIn(
            maxWidth = iconMaxSize,
            maxHeight = iconMaxSize,
        ),
    ) {
        val iconContentColor = if (enabled) {
            GrapesTheme.colors.primaryNormal
        } else {
            GrapesTheme.colors.neutralNormal
        }
        CompositionLocalProvider(LocalContentColor provides iconContentColor) {
            icon()
        }
    }
}

@Preview
@Preview(fontScale = 2f)
@Composable
private fun ActionMenuItemPreview() {
    GrapesTheme {
        val icon = @Composable {
            Icon(
                painter = painterResource(R.drawable.ic_neutral),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }
        val text = "Action"
        val longText = "Action with a very long title which will not fit in one line. Event two lines will not be enough"
        val modifier = Modifier.fillMaxWidth()
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ActionMenuItem(
                text = text,
                icon = icon,
                onClick = {},
                modifier = modifier,
            )
            ActionMenuItem(
                text = longText,
                icon = icon,
                onClick = {},
                modifier = modifier,
            )
            ActionMenuItem(
                text = text,
                icon = icon,
                onClick = {},
                enabled = false,
                modifier = modifier,
            )
        }
    }
}
