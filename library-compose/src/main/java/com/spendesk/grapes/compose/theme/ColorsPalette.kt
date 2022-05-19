package com.spendesk.grapes.compose.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * @author : danyboucanova
 * @since : 18/05/2022, Wed
 **/

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