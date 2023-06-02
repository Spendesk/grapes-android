package com.spendesk.grapes.compose.template.error

/**
 * @author Kélian CLERC
 * @since 05/06/2023
 */
data class ErrorRetryUiModel(
    val canRetry: Boolean,
    val message: String,
    val onRetryClicked: () -> Unit = {}
)
