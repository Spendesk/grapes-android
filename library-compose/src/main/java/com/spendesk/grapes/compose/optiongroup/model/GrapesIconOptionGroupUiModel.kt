package com.spendesk.grapes.compose.optiongroup.model

import androidx.annotation.DrawableRes

data class GrapesIconOptionGroupUiModel(
    val id: String,
    @DrawableRes val imageRes: Int,
    val isSelected: Boolean,
)
