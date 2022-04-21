package com.spendesk.grapes.samples.home.fragments.list

import com.spendesk.grapes.samples.components.list.ColorSampleListItemView

/**
 * @author danyboucanova
 * @since 10/03/2022
 */
sealed class ColorsBlockModel(val viewType: ColorsBlockViewType) {

    data class Section(val text: CharSequence) : ColorsBlockModel(ColorsBlockViewType.SECTION)
    data class Color(val configuration: ColorSampleListItemView.Configuration) : ColorsBlockModel(ColorsBlockViewType.COLOR_LIST)
}