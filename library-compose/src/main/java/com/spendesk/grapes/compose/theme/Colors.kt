package com.spendesk.grapes.compose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * @author : danyboucanova
 * @since : 03/05/2022, Tue
 **/

@Immutable
data class GrapesColors(
    @Deprecated("Grapes color deprecated", ReplaceWith("primaryDark"))
    val mainPrimaryDark: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("primaryNormal"))
    val mainPrimaryNormal: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("primaryLight"))
    val mainPrimaryLight: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("primaryLighter"))
    val mainPrimaryLighter: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("primaryLightest"))
    val mainPrimaryLightest: Color,

    @Deprecated("Use semantic color")
    val mainWhite: Color,
    @Deprecated("Use semantic color")
    val mainBlack: Color,

    @Deprecated("Grapes color deprecated", ReplaceWith("neutralDarker"))
    val mainNeutralDarkest: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("neutralDark"))
    val mainNeutralDarker: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("neutralNormal"))
    val mainNeutralDark: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("neutralLight"))
    val mainNeutralNormal: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("neutralLighter"))
    val mainNeutralLight: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("neutralLightest"))
    val mainNeutralLighter: Color,

    @Deprecated("Grapes color deprecated", ReplaceWith("infoNormal"))
    val mainInfoNormal: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("infoLighter"))
    val mainInfoLighter: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("infoLightest"))
    val mainInfoLightest: Color,

    val google: Color,

    val primaryDark: Color,
    val primaryNormal: Color,
    val primaryLight: Color,
    val primaryLighter: Color,
    val primaryLightest: Color,

    val neutralDarker: Color,
    val neutralDark: Color,
    val neutralNormal: Color,
    val neutralLight: Color,
    val neutralLighter: Color,
    val neutralLightest: Color,

    val infoNormal: Color,
    val infoLighter: Color,
    val infoLightest: Color,

    val successNormal: Color,
    val successLighter: Color,
    val successLightest: Color,

    val warningDark: Color,
    val warningNormal: Color,
    val warningLight: Color,
    val warningLighter: Color,
    val warningLightest: Color,

    val alertDark: Color,
    val alertNormal: Color,
    val alertLight: Color,
    val alertLighter: Color,
    val alertLightest: Color,

    val structureSurface: Color,
    val structureComplementary: Color,
    val structureBackground: Color,

    val isLight: Boolean,
)

// Legacy colors
val mainPrimaryDark = Color(0xFF421896)
val mainPrimaryLighter = Color(0xFFDBD1F4)
val mainBlack = Color(0xFF000000)
val mainNeutralDarkest = Color(0xFF48465E)

val google = Color(0xFF4285F4)

// Colors
val purpleDarker = Color(0xFF17114E)
val purpleDark = Color(0xFF4719A6)
val purpleNormal = Color(0xFF5D21D2)
val purpleLight = Color(0xFF7542D9)
val purpleLighter = Color(0xFFDFD3F6)
val purpleLightest = Color(0xFFF4EFFC)

val grayDarker = Color(0xFF434159)
val grayDark = Color(0xFF706F81)
val grayNormal = Color(0xFFB4B3BD)
val grayLight = Color(0xFFCFCFD5)
val grayLighter = Color(0xFFE6E6E9)
val grayLightest = Color(0xFFF5F5F6)
val graySuperLightest = Color(0xFFF9F9FA)
val grayWhite = Color(0xFFFFFFFF)

val blueNormal = Color(0xFF01799D)
val blueLighter = Color(0xFFB3D7E2)
val blueLightest = Color(0xFFE8F8FD)

val greenNormal = Color(0xFF0B831B)
val greenLight = Color(0xFFB6DABB)
val greenLighter = Color(0xFFDEFCEE)

val orangeDark = Color(0xFF7F4608)
val orangeNormal = Color(0xFFA85D00)
val orangeLight = Color(0xFFB57526)
val orangeLighter = Color(0xFFE5CEB3)
val orangeLightest = Color(0xFFFFF3E5)

val redDark = Color(0xFF9E2208)
val redNormal = Color(0xFFD12D00)
val redLight = Color(0xFFD84D26)
val redLighter = Color(0xFFF1C0B3)
val redLightest = Color(0xFFFDEDE8)
