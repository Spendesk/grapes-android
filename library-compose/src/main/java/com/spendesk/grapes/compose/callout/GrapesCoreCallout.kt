package com.spendesk.grapes.compose.callout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author jean-philippe
 * @since 17/10/2023, Tuesday
 **/
@Composable
fun GrapesCoreCallout(
    title: String,
    colors: GrapesCalloutColors,
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = GrapesTheme.shapes.small,
        color = colors.containerColor().value,
        contentColor = colors.contentColor().value,
        border = BorderStroke(GrapesCalloutDefaults.borderThickness, colors.borderStrokeColor().value),
    ) {
        Column(
            modifier = Modifier.padding(GrapesTheme.dimensions.spacing3),
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing2),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.spacing2),
            ) {
                ProvideTextStyle(
                    GrapesTheme.typography.titleS.copy(color = colors.titleColor().value),
                ) {
                    if (leadingIcon != null) {
                        Box(
                            modifier = Modifier
                                .size(GrapesCalloutDefaults.titleIconSize)
                                .align(Alignment.CenterVertically),
                            contentAlignment = Alignment.Center,
                        ) {
                            leadingIcon()
                        }
                    }

                    Text(
                        text = title,
                        textAlign = TextAlign.Left,
                    )
                }
            }

            content()
        }
    }
}
