package com.spendesk.grapes.compose.extensions

import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.model.GrapesConfigurationStatus

object GrapesDefault {

    /**
     * The Grapes design system contains some main configuration typically used to describe a component state.
     *
     * This function tries to match the provided [configurationState] to its icon associated.
     *
     * @return matching icon for a [configurationState] given.
     */
    fun iconFor(configurationState: GrapesConfigurationStatus): Int =
        when (configurationState) {
            GrapesConfigurationStatus.SUCCESS -> R.drawable.ic_success
            GrapesConfigurationStatus.INFORMATION -> R.drawable.ic_information
            GrapesConfigurationStatus.NEUTRAL -> R.drawable.ic_neutral
            GrapesConfigurationStatus.ALERT -> R.drawable.ic_error
            GrapesConfigurationStatus.WARNING -> R.drawable.ic_warning
            GrapesConfigurationStatus.BLOCKED -> R.drawable.ic_block
        }
}