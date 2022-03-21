package com.spendesk.grapes.samples.components

/**
 * @author danyboucanova
 * @since 21/03/2022
 */
sealed class CoverBlockModel {

    data class Cover(val configuration: CoverCardView.Configuration) : CoverBlockModel()
}