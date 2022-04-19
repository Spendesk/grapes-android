package com.spendesk.grapes.samples.components.list

import com.spendesk.grapes.samples.components.ColorSampleCardView

/**
 * @author danyboucanova
 * @since 21/03/2022
 */
sealed class ColorSampleBlockModel {

    data class Color(val configuration: ColorSampleCardView.Configuration) : ColorSampleBlockModel()
}