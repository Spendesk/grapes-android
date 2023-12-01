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
    val titleXs: TextStyle,
    val titleS: TextStyle,
    val titleM: TextStyle,
    val titleL: TextStyle,
    val titleXl: TextStyle,
) {
    constructor(
        defaultFontFamily: FontFamily = DefaultGrapesFontFamily,
        bodyXs: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        ),
        bodyS: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
        ),
        bodyRegular: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        bodyM: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
        ),
        bodyL: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
        ),
        bodyXl: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 40.sp,
        ),
        bodyXxl: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 56.sp,
        ),
        titleXs: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
        ),
        titleS: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
        ),
        titleM: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        ),
        titleL: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
        ),
        titleXl: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
        ),
    ) : this(
        bodyXs = bodyXs.withDefaultFontFamily(defaultFontFamily),
        bodyS = bodyS.withDefaultFontFamily(defaultFontFamily),
        bodyRegular = bodyRegular.withDefaultFontFamily(defaultFontFamily),
        bodyM = bodyM.withDefaultFontFamily(defaultFontFamily),
        bodyL = bodyL.withDefaultFontFamily(defaultFontFamily),
        bodyXl = bodyXl.withDefaultFontFamily(defaultFontFamily),
        bodyXxl = bodyXxl.withDefaultFontFamily(defaultFontFamily),
        titleXs = titleXs.withDefaultFontFamily(defaultFontFamily),
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
