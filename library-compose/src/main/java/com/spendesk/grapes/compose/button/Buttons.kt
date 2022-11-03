package com.spendesk.grapes.compose.button

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author : danyboucanova
 * @since : 16/05/2022, Mon
 **/

@Composable
private fun GrapesButton(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit) = {},
    configuration: GrapesButtonConfiguration = GrapesButtonConfiguration.PRIMARY,
    isEnabled: Boolean,
    content: @Composable RowScope.() -> Unit,
) {
    val (buttonColors: ButtonColors, buttonStroke: BorderStroke?) = when (configuration) {
        GrapesButtonConfiguration.PRIMARY -> Pair(primaryButtonColors(), primaryButtonStroke())
        GrapesButtonConfiguration.SECONDARY -> Pair(secondaryButtonColors(), secondaryButtonStroke())
        GrapesButtonConfiguration.TEXT -> Pair(textButtonColors(), textButtonStroke())
        GrapesButtonConfiguration.ALERT -> Pair(alertButtonColors(), alertButtonStroke())
        GrapesButtonConfiguration.WARNING -> Pair(warningButtonColors(), warningButtonStroke())
        GrapesButtonConfiguration.SECONDARY_TEXT -> Pair(secondaryTextButtonColors(), secondaryTextButtonStroke())
    }
    when (configuration) {
        GrapesButtonConfiguration.TEXT, GrapesButtonConfiguration.SECONDARY_TEXT -> {
            TextButton(
                modifier = modifier,
                onClick = onClick,
                colors = buttonColors,
                border = buttonStroke,
                elevation = null,
                shape = GrapesTheme.shapes.small,
                content = content,
                enabled = isEnabled
            )
        }
        else -> {
            Button(
                modifier = modifier,
                onClick = onClick,
                colors = buttonColors,
                border = buttonStroke,
                elevation = null,
                shape = GrapesTheme.shapes.small,
                content = content,
                enabled = isEnabled
            )
        }
    }
}

@Composable
fun GrapesButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit) = {},
    configuration: GrapesButtonConfiguration = GrapesButtonConfiguration.PRIMARY,
    size: GrapesButtonSize = GrapesButtonSize.LARGE,
    isEnabled: Boolean = true
) {
    val textStyle = when (size) {
        GrapesButtonSize.LARGE -> GrapesTheme.typography.titleM
        GrapesButtonSize.SMALL -> GrapesTheme.typography.titleS
    }
    val buttonModifier = when (size) {
        GrapesButtonSize.LARGE -> {
            modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        }
        GrapesButtonSize.SMALL -> {
            modifier.wrapContentSize()
        }
    }

    GrapesButton(
        modifier = buttonModifier,
        onClick = onClick,
        configuration = configuration,
        isEnabled = isEnabled
    ) {
        Text(text, style = textStyle)
    }
}

@Composable
fun GrapesButton(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit) = {},
    configuration: GrapesButtonConfiguration = GrapesButtonConfiguration.PRIMARY,
    size: GrapesButtonSize = GrapesButtonSize.LARGE,
    isEnabled: Boolean = true
) {
    val buttonModifier = when (size) {
        GrapesButtonSize.LARGE -> {
            modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        }
        GrapesButtonSize.SMALL -> {
            modifier.wrapContentSize()
        }
    }

    GrapesButton(
        modifier = buttonModifier,
        onClick = onClick,
        configuration = configuration,
        isEnabled = isEnabled
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "")
    }
}

@Preview("default", "button")
@Preview("dark theme", "button", uiMode = UI_MODE_NIGHT_NO)
@Preview("large font", "button", fontScale = 2f)
@Composable
private fun ButtonPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier.width(400.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            GrapesButton(text = "Mais oui")
            GrapesButton(text = "Mais oui" , configuration = GrapesButtonConfiguration.SECONDARY)
            GrapesButton(text = "Mais oui", configuration = GrapesButtonConfiguration.ALERT)
            GrapesButton(text = "Mais oui", configuration = GrapesButtonConfiguration.WARNING)
            GrapesButton(text = "Mais oui", configuration = GrapesButtonConfiguration.TEXT)
            GrapesButton(text = "Small button", size = GrapesButtonSize.SMALL)
            GrapesButton(text = "Large button", size = GrapesButtonSize.LARGE)
            GrapesButton(icon = R.drawable.ic_delete_return, size = GrapesButtonSize.LARGE)
            GrapesButton(icon = R.drawable.ic_delete_return, size = GrapesButtonSize.SMALL, configuration = GrapesButtonConfiguration.TEXT)
        }
    }
}
