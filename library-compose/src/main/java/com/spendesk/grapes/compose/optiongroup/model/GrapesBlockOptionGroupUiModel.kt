package com.spendesk.grapes.compose.optiongroup.model

import androidx.annotation.DrawableRes

data class GrapesBlockOptionGroupUiModel(
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String,
    val isSelected: Boolean,
)
