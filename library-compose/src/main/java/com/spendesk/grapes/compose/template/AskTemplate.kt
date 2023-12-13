package com.spendesk.grapes.compose.template

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.button.GrapesButton
import com.spendesk.grapes.compose.button.GrapesButtonStyleDefaults
import com.spendesk.grapes.compose.template.molecule.InformativeComponent
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 06/10/2022
 */
@Composable
private fun AskTemplate(
    header: @Composable () -> Unit,
    title: @Composable () -> Unit,
    description: @Composable () -> Unit,
    callToAction: @Composable () -> Unit,
) {
    InformativeComponent(
        middlePart = {
            Column(
                modifier = Modifier.padding(GrapesTheme.dimensions.spacing2),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing3)
            ) {
                header()
                title()
                description()
            }
        },
        bottomPart = callToAction
    )
}

@Composable
fun AskTemplate(
    @DrawableRes headerImageRes: Int? = null,
    title: String,
    description: String?,
    callToAction: @Composable () -> Unit,
) {
    val header = @Composable {
        if (headerImageRes != null) {
            Image(painter = painterResource(id = headerImageRes), contentDescription = null)
        }
    }

    val titleText = @Composable {
        Text(
            text = title,
            style = GrapesTheme.typography.titleXl.copy(fontSize = 32.sp),
            color = GrapesTheme.colors.mainWhite,
            textAlign = TextAlign.Center
        )
    }

    val descriptionText = @Composable {
        if (description != null) {
            Text(
                text = description,
                style = GrapesTheme.typography.bodyRegular,
                color = GrapesTheme.colors.mainWhite,
                textAlign = TextAlign.Center
            )
        }
    }

    AskTemplate(
        header = header,
        title = titleText,
        description = descriptionText,
        callToAction = callToAction
    )
}

@Preview
@Composable
fun Preview() {
    val cta = @Composable {
        Column(verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingMedium)) {
            GrapesButton(
                text = "Main button",
                buttonStyle = GrapesButtonStyleDefaults.secondary
            )

            GrapesButton(
                text = "Secondary button",
                buttonStyle = GrapesButtonStyleDefaults.text
            )
        }
    }

    GrapesTheme {
        AskTemplate(
            headerImageRes = R.drawable.ic_google_logo,
            title = "Hello, i'm a great title",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            callToAction = cta
        )
    }
}
