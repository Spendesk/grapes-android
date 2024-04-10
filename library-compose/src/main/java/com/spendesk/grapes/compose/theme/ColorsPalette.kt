package com.spendesk.grapes.compose.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * @author : danyboucanova
 * @since : 18/05/2022, Wed
 **/
@Suppress("LongParameterList", "LongMethod")
fun lightColorsPalette(
    legacyPrimaryDark: Color = mainPrimaryDark,
    legacyPrimaryLighter: Color = mainPrimaryLighter,
    primaryDark: Color = purpleDark,
    primaryNormal: Color = purpleNormal,
    primaryLight: Color = purpleLight,
    primaryLighter: Color = purpleLighter,
    primaryLightest: Color = purpleLightest,

    white: Color = grayWhite,
    black: Color = mainBlack,
    surface: Color = white,
    complementary: Color = purpleDarker,
    background: Color = graySuperLightest,

    legacyNeutralDarkest: Color = mainNeutralDarkest,
    neutralDarker: Color = grayDarker,
    neutralLightest: Color = grayLightest,
    neutralDark: Color = grayDark,
    neutralNormal: Color = grayNormal,
    neutralLight: Color = grayLight,
    neutralLighter: Color = grayLighter,

    infoNormal: Color = blueNormal,
    infoLighter: Color = blueLighter,
    infoLightest: Color = blueLightest,

    successNormal: Color = greenNormal,
    successLighter: Color = greenLight,
    successLightest: Color = greenLighter,

    warningDark: Color = orangeDark,
    warningNormal: Color = orangeNormal,
    warningLight: Color = orangeLight,
    warningLighter: Color = orangeLighter,
    warningLightest: Color = orangeLightest,

    alertDark: Color = redDark,
    alertNormal: Color = redNormal,
    alertLight: Color = redLight,
    alertLighter: Color = redLighter,
    alertLightest: Color = redLightest,
): GrapesColors = GrapesColors(
    mainPrimaryDark = legacyPrimaryDark,
    mainPrimaryNormal = primaryNormal,
    mainPrimaryLight = primaryLight,
    mainPrimaryLighter = legacyPrimaryLighter,
    mainPrimaryLightest = primaryLightest,
    mainWhite = white,
    mainBlack = black,
    mainNeutralDarkest = legacyNeutralDarkest,
    mainNeutralDarker = neutralDark,
    mainNeutralDark = neutralNormal,
    mainNeutralNormal = neutralLight,
    mainNeutralLight = neutralLighter,
    mainNeutralLighter = neutralLightest,
    mainInfoNormal = infoNormal,
    mainInfoLighter = infoLighter,
    mainInfoLightest = infoLightest,
    google = google,
    primaryDark = primaryDark,
    primaryNormal = primaryNormal,
    primaryLight = primaryLight,
    primaryLighter = primaryLighter,
    primaryLightest = primaryLightest,
    neutralDarker = neutralDarker,
    neutralDark = neutralDark,
    neutralNormal = neutralNormal,
    neutralLight = neutralLight,
    neutralLighter = neutralLighter,
    neutralLightest = neutralLightest,
    infoNormal = infoNormal,
    infoLighter = infoLighter,
    infoLightest = infoLightest,
    successNormal = successNormal,
    successLighter = successLighter,
    successLightest = successLightest,
    warningDark = warningDark,
    warningNormal = warningNormal,
    warningLight = warningLight,
    warningLighter = warningLighter,
    warningLightest = warningLightest,
    alertDark = alertDark,
    alertNormal = alertNormal,
    alertLight = alertLight,
    alertLighter = alertLighter,
    alertLightest = alertLightest,
    structureSurface = surface,
    structureComplementary = complementary,
    structureBackground = background,
    isLight = true,
)

internal val LocalGrapesColors = staticCompositionLocalOf { lightColorsPalette() }
