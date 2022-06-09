package com.spendesk.grapes.compose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.spendesk.grapes.compose.R

/**
 * @author : danyboucanova
 * @since : 03/05/2022, Tue
 **/

private val DefaultGrapesFontFamily = FontFamily(
    Font(R.font.font_raw_gt_america_regular, FontWeight.Normal),
    Font(R.font.font_raw_gt_america_medium, FontWeight.Medium)
)

@Immutable
data class GrapesTypography constructor(
    val bodyXs: TextStyle,
    val bodyS: TextStyle,
    val bodyRegular: TextStyle,
    val bodyM: TextStyle,
    val bodyL: TextStyle,
    val bodyXl: TextStyle,
    val bodyXxl: TextStyle,
    val titleS: TextStyle,
    val titleM: TextStyle,
    val titleL: TextStyle,
    val titleXl: TextStyle,
) {
    constructor(
        defaultFontFamily: FontFamily = DefaultGrapesFontFamily,
        bodyXs: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 12.sp,
        ),
        bodyS: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 14.sp,
        ),
        bodyRegular: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 16.sp,
        ),
        bodyM: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 24.sp,
        ),
        bodyL: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 32.sp,
        ),
        bodyXl: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 40.sp,
        ),
        bodyXxl: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 56.sp,
        ),
        titleS: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 14.sp,
        ),
        titleM: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 16.sp,
        ),
        titleL: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 18.sp,
        ),
        titleXl: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 32.sp,
        ),
    ) : this(
        bodyXs.withDefaultFontFamily(defaultFontFamily),
        bodyS.withDefaultFontFamily(defaultFontFamily),
        bodyRegular.withDefaultFontFamily(defaultFontFamily),
        bodyM.withDefaultFontFamily(defaultFontFamily),
        bodyL.withDefaultFontFamily(defaultFontFamily),
        bodyXl.withDefaultFontFamily(defaultFontFamily),
        bodyXxl.withDefaultFontFamily(defaultFontFamily),
        titleS.withDefaultFontFamily(defaultFontFamily),
        titleM.withDefaultFontFamily(defaultFontFamily),
        titleL.withDefaultFontFamily(defaultFontFamily),
        titleXl.withDefaultFontFamily(defaultFontFamily),
    )
}

internal val LocalGrapesTypography = staticCompositionLocalOf { GrapesTypography() }

private fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle {
    return if (fontFamily != null) this else copy(fontFamily = default)
}