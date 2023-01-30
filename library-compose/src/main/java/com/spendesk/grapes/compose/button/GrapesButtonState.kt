package com.spendesk.grapes.compose.button

/**
 * @author jean-philippe
 * @since 26/01/2023, Thursday
 **/
sealed class GrapesButtonState {
    object Enabled : GrapesButtonState()
    object Disabled : GrapesButtonState()
    object ShowCircularIndicator : GrapesButtonState()
}
