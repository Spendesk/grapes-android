package com.spendesk.grapes.compose.molecule

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.button.GrapesButtonConfiguration
import com.spendesk.grapes.compose.button.GrapesButtonSize
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 28/09/2022
 */
@Composable
private fun AskScreen(
    middlePart: @Composable () -> Unit,
    approveButton: @Composable () -> Unit,
    declineButton: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(GrapesTheme.colors.mainPrimaryDark, GrapesTheme.colors.mainPrimaryNormal)))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        middlePart()
        Spacer(modifier = Modifier.weight(1f))
        approveButton()
        Spacer(modifier = Modifier.height(8.dp))
        declineButton()
    }
}

@Composable
fun AskScreen(
    @DrawableRes imageRes: Int,
    title: String,
    subtitle: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onPositiveButtonClicked: () -> Unit = {},
    onNegativeButtonClicked: () -> Unit = {}
) {
    AskScreen(
        middlePart = {
            Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Image(painter = painterResource(id = imageRes), contentDescription = null)
                Text(text = title, style = GrapesTheme.typography.titleXl, color = GrapesTheme.colors.mainWhite, textAlign = TextAlign.Center)
                Text(text = subtitle, style = GrapesTheme.typography.bodyRegular, color = GrapesTheme.colors.mainWhite, textAlign = TextAlign.Center)
            }
        },
        approveButton = {
            com.spendesk.grapes.compose.button.GrapesButton(
                text = positiveButtonText,
                size = GrapesButtonSize.LARGE,
                configuration = GrapesButtonConfiguration.SECONDARY,
                onClick = onPositiveButtonClicked
            )
        },
        declineButton = {
            com.spendesk.grapes.compose.button.GrapesButton(
                text = negativeButtonText,
                size = GrapesButtonSize.LARGE,
                configuration = GrapesButtonConfiguration.SECONDARY_TEXT,
                onClick = onNegativeButtonClicked
            )
        }
    )
}

@Preview
@Composable
fun AskScreenPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AskScreen(
                imageRes = com.spendesk.grapes.compose.R.drawable.ic_success,
                title = "Activate notifications",
                subtitle = "Be always notify on time and never forget to upload a receipt or confirm a request",
                positiveButtonText = "Activate notifications",
                negativeButtonText = "Not now"
            )
        }
    }
}
