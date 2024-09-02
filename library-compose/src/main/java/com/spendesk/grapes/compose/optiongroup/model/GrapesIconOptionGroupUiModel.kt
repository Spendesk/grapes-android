package com.spendesk.grapes.compose.optiongroup.model

import androidx.annotation.DrawableRes

data class GrapesIconOptionGroupUiModel(
    @DrawableRes val imageRes: Int,
    val isSelected: Boolean,
)
