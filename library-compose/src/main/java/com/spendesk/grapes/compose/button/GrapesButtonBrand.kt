package com.spendesk.grapes.compose.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 03/03/2023
 */
@Composable
fun GrapesGoogleButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: GrapesButtonState = GrapesButtonState.Enabled,
) {
    val style = GrapesButtonStyleDefaults.google

    GrapesButtonIcon(
        text = label,
        leadingIcon = { BrandLeadingIcon(style, R.drawable.ic_google_logo) },
        buttonStyle = style,
        state = state,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun GrapesMicrosoftButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: GrapesButtonState = GrapesButtonState.Enabled,
) {
    val style = GrapesButtonStyleDefaults.microsoft

    GrapesButtonIcon(
        text = label,
        leadingIcon = { BrandLeadingIcon(style, R.drawable.ic_microsoft_logo) },
        buttonStyle = style,
        state = state,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun GrapesSamlButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: GrapesButtonState = GrapesButtonState.Enabled,
) {
    val style = GrapesButtonStyleDefaults.saml

    GrapesButtonIcon(
        text = label,
        leadingIcon = { BrandLeadingIcon(style, R.drawable.ic_saml_logo) },
        buttonStyle = style,
        state = state,
        onClick = onClick,
        modifier = modifier
    )
}

private val BrandLogoSize = 20.dp
private val BrandLogoContainerBorderRadius = 6.dp

@Composable
private fun BrandLeadingIcon(style: GrapesButtonStyle, @DrawableRes iconRes: Int) {
    Box(
        modifier = Modifier
            .size(style.iconSize)
            .background(GrapesTheme.colors.mainWhite, shape = RoundedCornerShape(BrandLogoContainerBorderRadius)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(BrandLogoSize)
        )
    }
}

@Preview
@Composable
private fun GrapesButtonBrandPreview() {
    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrapesTheme.colors.mainBackground)
                .padding(GrapesTheme.dimensions.spacing3),
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing3)
        ) {
            GrapesGoogleButton(label = "Register to Google", onClick = { /*TODO*/ })
            GrapesMicrosoftButton(label = "Register to Microsoft", onClick = { /*TODO*/ })
            GrapesSamlButton(label = "Register to with SAML SSO", onClick = { /*TODO*/ })
        }
    }
}
