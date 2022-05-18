package com.spendesk.grapes.compose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * @author : danyboucanova
 * @since : 03/05/2022, Tue
 **/

@Immutable
data class GrapesColors(
    val mainPrimaryDark: Color,
    val mainPrimaryNormal: Color,
    val mainPrimaryLight: Color,
    val mainPrimaryLighter: Color,
    val mainPrimaryLightest: Color,

    val mainWhite: Color,
    val mainBlack: Color,
    val mainComplementary: Color,
    val mainBackground: Color,

    val mainNeutralDarkest: Color,
    val mainNeutralDarker: Color,
    val mainNeutralDark: Color,
    val mainNeutralNormal: Color,
    val mainNeutralLight: Color,
    val mainNeutralLighter: Color,

    val mainInfoNormal: Color,
    val mainInfoLighter: Color,
    val mainInfoLightest: Color,

    val mainSuccessNormal: Color,
    val mainSuccessLighter: Color,
    val mainSuccessLightest: Color,

    val mainWarningDark: Color,
    val mainWarningNormal: Color,
    val mainWarningLighter: Color,
    val mainWarningLightest: Color,

    val mainAlertDark: Color,
    val mainAlertNormal: Color,
    val mainAlertLighter: Color,
    val mainAlertLightest: Color,
)

fun lightColorsPalette(
    primaryDark: Color = mainPrimaryDark,
    primaryNormal: Color = mainPrimaryNormal,
    primaryLight: Color = mainPrimaryLight,
    primaryLighter: Color = mainPrimaryLighter,
    primaryLightest: Color = mainPrimaryLightest,

    white: Color = mainWhite,
    black: Color = mainBlack,
    complementary: Color = mainComplementary,
    background: Color = mainBackground,

    neutralDarkest: Color = mainNeutralDarkest,
    neutralDarker: Color = mainNeutralDarker,
    neutralDark: Color = mainNeutralDark,
    neutralNormal: Color = mainNeutralNormal,
    neutralLight: Color = mainNeutralLight,
    neutralLighter: Color = mainNeutralLighter,

    infoNormal: Color = mainInfoNormal,
    infoLighter: Color = mainInfoLighter,
    infoLightest: Color = mainInfoLightest,

    successNormal: Color = mainSuccessNormal,
    successLighter: Color = mainSuccessLighter,
    successLightest: Color = mainSuccessLightest,

    warningDark: Color = mainWarningDark,
    warningNormal: Color = mainWarningNormal,
    warningLighter: Color = mainWarningLighter,
    warningLightest: Color = mainWarningLightest,

    alertDark: Color = mainAlertDark,
    alertNormal: Color = mainAlertNormal,
    alertLighter: Color = mainAlertLighter,
    alertLightest: Color = mainAlertLightest,
): GrapesColors = GrapesColors(
    mainPrimaryDark = primaryDark,
    mainPrimaryNormal = primaryNormal,
    mainPrimaryLight = primaryLight,
    mainPrimaryLighter = primaryLighter,
    mainPrimaryLightest = primaryLightest,
    mainWhite = white,
    mainBlack = black,
    mainComplementary = complementary,
    mainBackground = background,
    mainNeutralDarkest = neutralDarkest,
    mainNeutralDarker = neutralDarker,
    mainNeutralDark = neutralDark,
    mainNeutralNormal = neutralNormal,
    mainNeutralLight = neutralLight,
    mainNeutralLighter = neutralLighter,
    mainInfoNormal = infoNormal,
    mainInfoLighter = infoLighter,
    mainInfoLightest = infoLightest,
    mainSuccessNormal = successNormal,
    mainSuccessLighter = successLighter,
    mainSuccessLightest = successLightest,
    mainWarningDark = warningDark,
    mainWarningNormal = warningNormal,
    mainWarningLighter = warningLighter,
    mainWarningLightest = warningLightest,
    mainAlertDark = alertDark,
    mainAlertNormal = alertNormal,
    mainAlertLighter = alertLighter,
    mainAlertLightest = alertLightest
)

internal val LocalColors = staticCompositionLocalOf { lightColorsPalette() }

private val mainPrimaryDark = Color(0xFF421896)
private val mainPrimaryNormal = Color(0xFF5D21D2)
private val mainPrimaryLight = Color(0xFF7542D9)
private val mainPrimaryLighter = Color(0xFFDBD1F4)
private val mainPrimaryLightest = Color(0xFFF4EFFC)

private val mainWhite = Color(0xFFFFFFFF)
private val mainBlack = Color(0xFF000000)
private val mainComplementary = Color(0xFF17114E)
private val mainBackground = Color(0xFFF7F7F8)

private val mainNeutralDarkest = Color(0xFF48465E)
private val mainNeutralDarker = Color(0xFF706F81)
private val mainNeutralDark = Color(0xFFB4B3BD)
private val mainNeutralNormal = Color(0xFFCFCFD5)
private val mainNeutralLight = Color(0xFFE6E6E9)
private val mainNeutralLighter = Color(0xFFF5F5F6)

private val mainInfoNormal = Color(0xFF01799D)
private val mainInfoLighter = Color(0xFFB3D7E2)
private val mainInfoLightest = Color(0xFFE8F8FD)

private val mainSuccessNormal = Color(0xFF0B831B)
private val mainSuccessLighter = Color(0xFFB6DABB)
private val mainSuccessLightest = Color(0xFFDEFCEE)

private val mainWarningDark = Color(0xFF7F4608)
private val mainWarningNormal = Color(0xFFA85D00)
private val mainWarningLighter = Color(0xFFE5CEB3)
private val mainWarningLightest = Color(0xFFFFF3E5)

private val mainAlertDark = Color(0xFF9E2208)
private val mainAlertNormal = Color(0xFFD12D00)
private val mainAlertLighter = Color(0xFFF1C0B3)
private val mainAlertLightest = Color(0xFFFDEDE8)