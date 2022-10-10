package com.spendesk.grapes.compose.template

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.button.GrapesButtonConfiguration
import com.spendesk.grapes.compose.button.GrapesButtonSize
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 06/10/2022
 */

@Composable
private fun AskTemplate(
    middlePart: @Composable () -> Unit,
    approveButton: @Composable () -> Unit,
    declineButton: @Composable () -> Unit
) {
    InformativeTemplate(
        middlePart = middlePart,
        bottomPart = {
            Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingMedium)) {
                approveButton()
                declineButton()
            }
        }
    )
}

@Composable
fun AskTemplate(
    @DrawableRes imageRes: Int,
    title: String,
    subtitle: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onPositiveButtonClicked: () -> Unit = {},
    onNegativeButtonClicked: () -> Unit = {}
) {
    AskTemplate(
        middlePart = {
            Column(
                modifier = Modifier.padding(GrapesTheme.dimensions.paddingMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingNormal)
            ) {
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
            AskTemplate(
                imageRes = R.drawable.ic_success,
                title = "Activate notifications",
                subtitle = "Be always notify on time and never forget to upload a receipt or confirm a request",
                positiveButtonText = "Activate notifications",
                negativeButtonText = "Not now"
            )
        }
    }
}
