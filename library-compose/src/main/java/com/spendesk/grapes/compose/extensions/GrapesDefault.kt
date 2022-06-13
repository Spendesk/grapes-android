package com.spendesk.grapes.compose.extensions

import com.spendesk.grapes.compose.R
import com.spendesk.grapes.compose.model.GrapesConfigurationState

object GrapesDefault {

    /**
     * The Grapes design system contains some main configuration typically used to describe a component state.
     *
     * This function tries to match the provided [configurationState] to its icon associated.
     *
     * @return matching icon for a [configurationState] given.
     */
    fun iconFor(configurationState: GrapesConfigurationState): Int =
        when (configurationState) {
            GrapesConfigurationState.SUCCESS -> R.drawable.ic_success
            GrapesConfigurationState.INFORMATION -> R.drawable.ic_information
            GrapesConfigurationState.NEUTRAL -> R.drawable.ic_neutral
            GrapesConfigurationState.ALERT -> R.drawable.ic_error
            GrapesConfigurationState.WARNING -> R.drawable.ic_warning
            GrapesConfigurationState.BLOCKED -> R.drawable.ic_block
        }
}