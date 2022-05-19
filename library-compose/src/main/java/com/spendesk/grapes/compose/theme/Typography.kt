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
    val BodyXs: TextStyle,
    val BodyS: TextStyle,
    val BodyRegular: TextStyle,
    val BodyM: TextStyle,
    val BodyL: TextStyle,
    val BodyXl: TextStyle,
    val BodyXxl: TextStyle,
    val titleS: TextStyle,
    val titleM: TextStyle,
    val titleL: TextStyle,
    val titleXl: TextStyle,
) {
    constructor(
        defaultFontFamily: FontFamily = DefaultGrapesFontFamily,
        BodyXs: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 12.sp,
        ),
        BodyS: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 14.sp,
        ),
        BodyRegular: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 16.sp,
        ),
        BodyM: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 24.sp,
        ),
        BodyL: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 32.sp,
        ),
        BodyXl: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 40.sp,
        ),
        BodyXxl: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 56.sp,
        ),
        TitleS: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 14.sp,
        ),
        TitleM: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 16.sp,
        ),
        TitleL: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 18.sp,
        ),
        TitleXl: TextStyle = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 32.sp,
        ),
    ) : this(
        BodyXs.withDefaultFontFamily(defaultFontFamily),
        BodyS.withDefaultFontFamily(defaultFontFamily),
        BodyRegular.withDefaultFontFamily(defaultFontFamily),
        BodyM.withDefaultFontFamily(defaultFontFamily),
        BodyL.withDefaultFontFamily(defaultFontFamily),
        BodyXl.withDefaultFontFamily(defaultFontFamily),
        BodyXxl.withDefaultFontFamily(defaultFontFamily),
        TitleS.withDefaultFontFamily(defaultFontFamily),
        TitleM.withDefaultFontFamily(defaultFontFamily),
        TitleL.withDefaultFontFamily(defaultFontFamily),
        TitleXl.withDefaultFontFamily(defaultFontFamily),
    )
}

internal val LocalGrapesTypography = staticCompositionLocalOf { GrapesTypography() }


private fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle {
    return if (fontFamily != null) this else copy(fontFamily = default)
}