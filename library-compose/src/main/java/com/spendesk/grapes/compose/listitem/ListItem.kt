package com.spendesk.grapes.compose.listitem

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.spendesk.grapes.compose.icons.Size
import com.spendesk.grapes.compose.icons.StatusInformationIcon
import com.spendesk.grapes.compose.model.GrapesConfigurationStatus

/**
 * @author Kélian CLERC
 * @since 06/12/2022
 */

sealed interface ListItemConfiguration {
    data class IconAction(
        val title: String,
        @DrawableRes val icon: Int,
        val status: GrapesConfigurationStatus,
        val description: String? = null
    ) : ListItemConfiguration
}

@Composable
fun ListItem(
    configuration: ListItemConfiguration,
    modifier: Modifier = Modifier
) {
    when (configuration) {
        is ListItemConfiguration.IconAction -> IconAction(iconActionConfiguration = configuration, modifier)
    }
}
