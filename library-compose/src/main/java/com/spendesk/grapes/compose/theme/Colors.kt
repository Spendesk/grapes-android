package com.spendesk.grapes.compose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.airbnb.android.showkase.annotation.ShowkaseColor

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
    val isLight: Boolean,

    val google: Color,
)

@ShowkaseColor("PrimaryDark", group = "Light theme")
val mainPrimaryDark = Color(0xFF421896)

@ShowkaseColor("PrimaryNormal", group = "Light theme")
val mainPrimaryNormal = Color(0xFF5D21D2)

@ShowkaseColor("PrimaryLight", group = "Light theme")
val mainPrimaryLight = Color(0xFF7542D9)

@ShowkaseColor("PrimaryLighter", group = "Light theme")
val mainPrimaryLighter = Color(0xFFDBD1F4)

@ShowkaseColor("PrimaryLightest", group = "Light theme")
val mainPrimaryLightest = Color(0xFFF4EFFC)


@ShowkaseColor("White", group = "Light theme")
val mainWhite = Color(0xFFFFFFFF)

@ShowkaseColor("Black", group = "Light theme")
val mainBlack = Color(0xFF000000)

@ShowkaseColor("Complementary", group = "Light theme")
val mainComplementary = Color(0xFF17114E)

@ShowkaseColor("Background", group = "Light theme")
val mainBackground = Color(0xFFF7F7F8)


@ShowkaseColor("NeutralDarkest", group = "Light theme")
val mainNeutralDarkest = Color(0xFF48465E)

@ShowkaseColor("NeutralDarker", group = "Light theme")
val mainNeutralDarker = Color(0xFF706F81)

@ShowkaseColor("NeutralDark", group = "Light theme")
val mainNeutralDark = Color(0xFFB4B3BD)

@ShowkaseColor("NeutralNormal", group = "Light theme")
val mainNeutralNormal = Color(0xFFCFCFD5)

@ShowkaseColor("NeutralLight", group = "Light theme")
val mainNeutralLight = Color(0xFFE6E6E9)

@ShowkaseColor("NeutralLighter", group = "Light theme")
val mainNeutralLighter = Color(0xFFF5F5F6)


@ShowkaseColor("InfoNormal", group = "Light theme")
val mainInfoNormal = Color(0xFF01799D)

@ShowkaseColor("InfoLighter", group = "Light theme")
val mainInfoLighter = Color(0xFFB3D7E2)

@ShowkaseColor("InfoLightest", group = "Light theme")
val mainInfoLightest = Color(0xFFE8F8FD)


@ShowkaseColor("SuccessNormal", group = "Light theme")
val mainSuccessNormal = Color(0xFF0B831B)

@ShowkaseColor("SuccessLighter", group = "Light theme")
val mainSuccessLighter = Color(0xFFB6DABB)

@ShowkaseColor("SuccessLightest", group = "Light theme")
val mainSuccessLightest = Color(0xFFDEFCEE)


@ShowkaseColor("WarningDark", group = "Light theme")
val mainWarningDark = Color(0xFF7F4608)

@ShowkaseColor("WarningNormal", group = "Light theme")
val mainWarningNormal = Color(0xFFA85D00)

@ShowkaseColor("WarningLighter", group = "Light theme")
val mainWarningLighter = Color(0xFFE5CEB3)

@ShowkaseColor("WarningLightest", group = "Light theme")
val mainWarningLightest = Color(0xFFFFF3E5)


@ShowkaseColor("AlertDark", group = "Light theme")
val mainAlertDark = Color(0xFF9E2208)

@ShowkaseColor("AlertNormal", group = "Light theme")
val mainAlertNormal = Color(0xFFD12D00)

@ShowkaseColor("AlertLighter", group = "Light theme")
val mainAlertLighter = Color(0xFFF1C0B3)

@ShowkaseColor("AlertLightest", group = "Light theme")
val mainAlertLightest = Color(0xFFFDEDE8)

val google = Color(0xFF4285F4)
