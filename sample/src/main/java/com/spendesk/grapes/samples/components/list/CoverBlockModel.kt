package com.spendesk.grapes.samples.components.list

import com.spendesk.grapes.samples.components.CoverCardView

/**
 * @author danyboucanova
 * @since 21/03/2022
 */
sealed class CoverBlockModel {

    data class Cover(val configuration: CoverCardView.Configuration) : CoverBlockModel()
}