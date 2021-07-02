package com.spendesk.grapes.selectors

/**
 * @author danyboucanova
 * @since 08/06/2021
 */
sealed class PickerModel(val viewType: PickerViewType) {

    class Label(val id: String, val configuration: PickerLabelTextView.Configuration) : PickerModel(PickerViewType.LABEL)
    class Block(val id: String, val configuration: PickerBlockIconCardView.Configuration) : PickerModel(PickerViewType.BLOCK)
}