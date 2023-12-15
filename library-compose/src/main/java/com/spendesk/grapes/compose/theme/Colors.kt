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
    @Deprecated("Grapes color deprecated", ReplaceWith("structureComplementary"))
    val mainComplementary: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("structureBackground"))
    val mainBackground: Color,

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

    @Deprecated("Grapes color deprecated", ReplaceWith("successNormal"))
    val mainSuccessNormal: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("successLighter"))
    val mainSuccessLighter: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("successLightest"))
    val mainSuccessLightest: Color,

    @Deprecated("Grapes color deprecated", ReplaceWith("warningDark"))
    val mainWarningDark: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("warningNormal"))
    val mainWarningNormal: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("warningLighter"))
    val mainWarningLighter: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("warningLightest"))
    val mainWarningLightest: Color,

    @Deprecated("Grapes color deprecated", ReplaceWith("alertDark"))
    val mainAlertDark: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("alertNormal"))
    val mainAlertNormal: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("alertLighter"))
    val mainAlertLighter: Color,
    @Deprecated("Grapes color deprecated", ReplaceWith("alertLightest"))
    val mainAlertLightest: Color,

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
val mainWhite = Color(0xFFFFFFFF)
val mainBlack = Color(0xFF000000)
val mainNeutralDarkest = Color(0xFF48465E)

val google = Color(0xFF4285F4)

// Colors
val gunPowder = Color(0xFF434159)
val stormGray = Color(0xFF706F81)
val frenchGray = Color(0xFFB4B3BD)
val ghost = Color(0xFFCFCFD5)
val athensGray = Color(0xFFE6E6E9)
val athensGrayLight = Color(0xFFF5F5F6)

val purpleDarker = Color(0xFF17114E)
val purpleDark = Color(0xFF4719A6)
val purpleNormal = Color(0xFF5D21D2)
val purpleLight = Color(0xFF7542D9)
val purpleLighter = Color(0xFFDFD3F6)
val purpleLightest = Color(0xFFF4EFFC)

val allports = Color(0xFF01799D)
val powderBlue = Color(0xFFB3D7E2)
val foam = Color(0xFFE8F8FD)

val japaneseLaurel = Color(0xFF0B831B)
val surf = Color(0xFFB6DABB)
val scandal = Color(0xFFDEFCEE)

val antiqueBronze = Color(0xFF7F4608)
val chelseaGem = Color(0xFFA85D00)
val marigold = Color(0xFFB57526)
val doubleSpanishWhite = Color(0xFFE5CEB3)
val serenade = Color(0xFFFFF3E5)

val totemPole = Color(0xFF9E2208)
val grenadier = Color(0xFFD12D00)
val punch = Color(0xFFD84D26)
val mandysPink = Color(0xFFF1C0B3)
val provincialPink = Color(0xFFFDEDE8)

val gray = Color(0xFFF9F9FA)
