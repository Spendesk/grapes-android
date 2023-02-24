package com.spendesk.grapes.compose.theme

import androidx.compose.runtime.Immutable
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
    val isLight: Boolean
)

val mainPrimaryDark = Color(0xFF421896)
val mainPrimaryNormal = Color(0xFF5D21D2)
val mainPrimaryLight = Color(0xFF7542D9)
val mainPrimaryLighter = Color(0xFFDBD1F4)
val mainPrimaryLightest = Color(0xFFF4EFFC)

val mainWhite = Color(0xFFFFFFFF)
val mainBlack = Color(0xFF000000)
val mainComplementary = Color(0xFF17114E)
val mainBackground = Color(0xFFF7F7F8)

val mainNeutralDarkest = Color(0xFF48465E)
val mainNeutralDarker = Color(0xFF706F81)
val mainNeutralDark = Color(0xFFB4B3BD)
val mainNeutralNormal = Color(0xFFCFCFD5)
val mainNeutralLight = Color(0xFFE6E6E9)
val mainNeutralLighter = Color(0xFFF5F5F6)

val mainInfoNormal = Color(0xFF01799D)
val mainInfoLighter = Color(0xFFB3D7E2)
val mainInfoLightest = Color(0xFFE8F8FD)

val mainSuccessNormal = Color(0xFF0B831B)
val mainSuccessLighter = Color(0xFFB6DABB)
val mainSuccessLightest = Color(0xFFDEFCEE)

val mainWarningDark = Color(0xFF7F4608)
val mainWarningNormal = Color(0xFFA85D00)
val mainWarningLighter = Color(0xFFE5CEB3)
val mainWarningLightest = Color(0xFFFFF3E5)

val mainAlertDark = Color(0xFF9E2208)
val mainAlertNormal = Color(0xFFD12D00)
val mainAlertLighter = Color(0xFFF1C0B3)
val mainAlertLightest = Color(0xFFFDEDE8)
