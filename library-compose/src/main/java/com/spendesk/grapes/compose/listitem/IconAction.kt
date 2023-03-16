package com.spendesk.grapes.compose.listitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.bucket.GrapesBucket
import com.spendesk.grapes.compose.icons.Size
import com.spendesk.grapes.compose.icons.StatusInformationIcon
import com.spendesk.grapes.compose.model.GrapesConfigurationStatus
import com.spendesk.grapes.compose.theme.GrapesTheme

/**
 * @author Kélian CLERC
 * @since 07/12/2022
 */

@Composable
internal fun IconAction(iconActionConfiguration: ListItemConfiguration.IconAction, modifier: Modifier = Modifier) {
    val titleColor = when (iconActionConfiguration.status) {
        GrapesConfigurationStatus.ALERT -> GrapesTheme.colors.mainAlertNormal
        else -> GrapesTheme.colors.mainComplementary
    }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingLarge)
    ) {

        StatusInformationIcon(
            icon = iconActionConfiguration.icon,
            configuration = iconActionConfiguration.status,
            size = Size.M,
            hasBorder = false
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(GrapesTheme.dimensions.paddingXSmall)
        ) {
            Text(text = iconActionConfiguration.title, style = GrapesTheme.typography.titleM, color = titleColor)
            iconActionConfiguration.description?.let {
                Text(text = it, style = GrapesTheme.typography.bodyS, color = GrapesTheme.colors.mainNeutralDarker)
            }
        }
    }
}

@Preview
@Composable
private fun IconActionPreview() {
    val alertConfiguration = ListItemConfiguration.IconAction(
        title = "Title",
        icon = R.drawable.ic_success,
        status = GrapesConfigurationStatus.ALERT,
        description = "Description"
    )

    val infoConfigurationWithoutDescription = ListItemConfiguration.IconAction(
        title = "Title",
        icon = R.drawable.ic_success,
        status = GrapesConfigurationStatus.INFORMATION
    )

    val infoConfigurationWithDescription = ListItemConfiguration.IconAction(
        title = "Title",
        icon = R.drawable.ic_success,
        status = GrapesConfigurationStatus.INFORMATION,
        description = "Test description"
    )

    GrapesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconAction(iconActionConfiguration = alertConfiguration)
            IconAction(iconActionConfiguration = infoConfigurationWithoutDescription)
            GrapesBucket(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                IconAction(
                    modifier = Modifier
                        .fillMaxWidth(),
                    iconActionConfiguration = infoConfigurationWithDescription
                )
            }
        }
    }
}
