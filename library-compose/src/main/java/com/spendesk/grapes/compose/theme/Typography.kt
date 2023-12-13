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
    val bodyS: TextStyle,
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
        bodyS: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        ),
        bodyM: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
        ),
        bodyL: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        bodyXl: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        ),
        bodyXxl: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 40.sp
        ),
        titleS: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
        ),
        titleM: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
        ),
        titleL: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        ),
        titleXl: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
        ),
    ) : this(
        bodyS = bodyS.withDefaultFontFamily(defaultFontFamily),
        bodyM = bodyM.withDefaultFontFamily(defaultFontFamily),
        bodyL = bodyL.withDefaultFontFamily(defaultFontFamily),
        bodyXl = bodyXl.withDefaultFontFamily(defaultFontFamily),
        bodyXxl = bodyXxl.withDefaultFontFamily(defaultFontFamily),
        titleS = titleS.withDefaultFontFamily(defaultFontFamily),
        titleM = titleM.withDefaultFontFamily(defaultFontFamily),
        titleL = titleL.withDefaultFontFamily(defaultFontFamily),
        titleXl = titleXl.withDefaultFontFamily(defaultFontFamily),
    )
}

internal val LocalGrapesTypography = staticCompositionLocalOf { GrapesTypography() }

private fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle {
    return if (fontFamily != null) this else copy(fontFamily = default)
}
