package com.spendesk.grapes.compose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * @author : danyboucanova
 * @since : 03/05/2022, Tue
 **/

@Immutable
data class GrapesColors(
    @Deprecated("Use semantic color")
    val mainWhite: Color,

    val google: Color,

    @Deprecated("Use the following design token according to the context: borderHover, backgroundPrimaryBrandPressed or contentBrandPressed")
    val primaryDark: Color,
    @Deprecated("No longer used for now")
    val primaryNormal: Color,
    @Deprecated("No longer used for now")
    val primaryLight: Color,
    @Deprecated("No longer used for now")
    val primaryLighter: Color,
    @Deprecated("Use the following design token according to the context: backgroundSecondaryBrandDefault, backgroundSecondaryHoverDefault")
    val primaryLightest: Color,

    @Deprecated("No longer used for now")
    val neutralDarker: Color,
    @Deprecated("Use the following design token according to the context: contentSecondary, contentTertiary")
    val neutralNormal: Color,
    @Deprecated("No longer used")
    val neutralDark: Color,
    @Deprecated("Use the following design token: contentDisabled")
    val neutralLight: Color,
    @Deprecated("Use the following design token according to the context: borderDefault, backgroundPressed")
    val neutralLighter: Color,
    @Deprecated("Use the following design token: backgroundDisabled")
    val neutralLightest: Color,

    @Deprecated("Use the following design token according to the context: borderInfo, backgroundPrimaryInfoDefault or contentInfoDefault")
    val infoNormal: Color,
    @Deprecated("This token is no longer used")
    val infoLighter: Color,
    @Deprecated("Use the following design token: backgroundSecondaryInfoPressed")
    val infoLightest: Color,

    @Deprecated("Use the following design token according to the context: borderSuccess, backgroundPrimarySuccessDefault, backgroundPrimarySuccessHover or contentSuccessDefault")
    val successNormal: Color,
    @Deprecated("Use the following design token: borderSecondarySuccessPressed")
    val successLighter: Color,
    @Deprecated("Use the following design token according to the context: backgroundSecondarySuccessDefault or backgroundSecondarySuccessHover")
    val successLightest: Color,

    @Deprecated("Use the following design token: backgroundPrimaryWarningPressed")
    val warningDark: Color,
    @Deprecated("Use the following design token according to the context: borderWarning, backgroundPrimaryWarningDefault or contentWarningDefault")
    val warningNormal: Color,
    @Deprecated("No longer used")
    val warningLight: Color,
    @Deprecated("Use one of the following new design token instead: backgroundSecondaryWarningDefault or Hover")
    val warningLighter: Color,
    @Deprecated("Use the following design token: backgroundSecondaryWarningPressed")
    val warningLightest: Color,

    @Deprecated("Use the following design token: backgroundPrimaryAlertPressed")
    val alertDark: Color,
    @Deprecated("Use the following design token according to the context: borderAlert, backgroundPrimaryAlertDefault or contentAlertDefault")
    val alertNormal: Color,
    @Deprecated("No longer used")
    val alertLight: Color,
    @Deprecated("Use the following design token: backgroundSecondaryAlertPressed")
    val alertLighter: Color,
    @Deprecated("Use the following design token according to the context: backgroundSecondaryAlertDefault, backgroundSecondaryAlertHover")
    val alertLightest: Color,

    @Deprecated("Use the following design token according to the context: backgroundPrimary, contentComplementary")
    val structureSurface: Color,
    @Deprecated("Use the following design token instead: contentPrimary")
    val structureComplementary: Color,
    @Deprecated("Use the following design token according to the context: borderSecondary, backgroundHover or backgroundReadOnly")
    val structureBackground: Color,

    val isLight: Boolean,

    // UI-Revamp new design tokens:
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val backgroundTertiary: Color,
    val backgroundHover: Color,
    val backgroundPressed: Color,
    val backgroundSelected: Color,
    val backgroundDisabled: Color,
    val backgroundReadOnly: Color,

    val backgroundPrimaryBrandDefault: Color,
    val backgroundPrimaryBrandHover: Color,
    val backgroundPrimaryBrandPressed: Color,

    val backgroundPrimaryInfoDefault: Color,
    val backgroundPrimaryInfoHover: Color,
    val backgroundPrimaryInfoPressed: Color,

    val backgroundPrimarySuccessDefault: Color,
    val backgroundPrimarySuccessHover: Color,
    val backgroundPrimarySuccessPressed: Color,

    val backgroundPrimaryWarningDefault: Color,
    val backgroundPrimaryWarningHover: Color,
    val backgroundPrimaryWarningPressed: Color,

    val backgroundPrimaryAlertDefault: Color,
    val backgroundPrimaryAlertHover: Color,
    val backgroundPrimaryAlertPressed: Color,

    val backgroundSecondaryBrandDefault: Color,
    val backgroundSecondaryBrandHover: Color,
    val backgroundSecondaryBrandPressed: Color,

    val backgroundSecondaryInfoDefault: Color,
    val backgroundSecondaryInfoHover: Color,
    val backgroundSecondaryInfoPressed: Color,

    val backgroundSecondarySuccessDefault: Color,
    val backgroundSecondarySuccessHover: Color,
    val backgroundSecondarySuccessPressed: Color,

    val backgroundSecondaryWarningDefault: Color,
    val backgroundSecondaryWarningHover: Color,
    val backgroundSecondaryWarningPressed: Color,

    val backgroundSecondaryAlertDefault: Color,
    val backgroundSecondaryAlertHover: Color,
    val backgroundSecondaryAlertPressed: Color,

    val contentPrimary: Color,
    val contentSecondary: Color,
    val contentTertiary: Color,
    val contentComplementary: Color,
    val contentSelected: Color,
    val contentDisable: Color,

    val contentBrandDefault: Color,
    val contentBrandHover: Color,
    val contentBrandPressed: Color,

    val contentInfoDefault: Color,
    val contentSuccessDefault: Color,
    val contentWarningDefault: Color,
    val contentAlertDefault: Color,

    val borderDefault: Color,
    val borderHover: Color,
    val borderSelected: Color,
    val borderInfo: Color,
    val borderSuccess: Color,
    val borderWarning: Color,
    val borderAlert: Color,
)

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

// UI-Revamp
val apricot10 = Color(0xFFF8EEE9)
val apricot20 = Color(0xFFFACFB8)
val apricot30 = Color(0xFFF0B494)
val apricot40 = Color(0xFFECA179)
val apricot50 = Color(0xFFE78855)
val apricot60 = Color(0xFFDB611F)
val apricot70 = Color(0xFFB54509)
val apricot80 = Color(0xFF9C3B08)
val apricot90 = Color(0xFF833206)
val apricot100 = Color(0xFF412301)

val blue10 = Color(0xFFEFF1FE)
val blue20 = Color(0xFFDAE0FF)
val blue30 = Color(0xFFC1CAFB)
val blue40 = Color(0xFFB3BFFA)
val blue50 = Color(0xFF9696EE)
val blue60 = Color(0xFF7D7DDE)
val blue70 = Color(0xFF5D5DC2)
val blue80 = Color(0xFF4A4ABA)
val blue90 = Color(0xFF4040AA)
val blue100 = Color(0xFF222263)

val carbon3 = Color(0xFFF8F8F8)
val carbon5 = Color(0xFFF3F4F4)
val carbon7 = Color(0xFFEFEFEF)
val carbon10 = Color(0xFFE8E8E8)
val carbon15 = Color(0xFFDDDDDD)
val carbon20 = Color(0xFFD1D1D1)
val carbon30 = Color(0xFFBABBBB)
val carbon40 = Color(0xFFA3A4A4)
val carbon50 = Color(0xFF8C8D8D)
val carbon60 = Color(0xFF757676)
val carbon70 = Color(0xFF5E5F5F)
val carbon80 = Color(0xFF474949)
val carbon90 = Color(0xFF303232)
val carbon100 = Color(0xFF191B1B)

val emerald10 = Color(0xFFE9F6EC)
val emerald20 = Color(0xFFC7EACE)
val emerald30 = Color(0xFF9BDAA7)
val emerald40 = Color(0xFF91D69F)
val emerald50 = Color(0xFF56B369)
val emerald60 = Color(0xFF3A984C)
val emerald70 = Color(0xFF2D763C)
val emerald80 = Color(0xFF266432)
val emerald90 = Color(0xFF1F5129)
val emerald100 = Color(0xFF073310)

val lightForest = Color(0xFFF0F5E6)
val brightForest = Color(0xFFD7E8B2)
val midForest = Color(0xFFB9D67A)
val deepForest = Color(0xFF576E26)
val darkForest = Color(0xFF213000)

val lightGrolive = Color(0xFFF3F4EF)
val brightGrolive = Color(0xFFE0E2D7)
val midGrolive = Color(0xFFCACEB9)
val deepGrolive = Color(0xFF626555)
val darkGrolive = Color(0xFF2B2C26)

val lightLemon = Color(0xFFF5F5E6)
val brightLemon = Color(0xFFE6E8AB)
val softLemon = Color(0xFFCED25D)
val deepLemon = Color(0xFF686427)
val darkLemon = Color(0xFF2E2C00)

val lightOcean = Color(0xFFE5F5F5)
val brightOcean = Color(0xFFC0ECEC)
val softOcean = Color(0xFF7DD5D5)
val deepOcean = Color(0xFF176E6E)
val darkOcean = Color(0xFF003333)

val lightPeach = Color(0xFFFAF2F1)
val brightPeach = Color(0xFFFFD4CA)
val softPeach = Color(0xFFF4AB9B)
val deepPeach = Color(0xFFA4422D)
val darkPeach = Color(0xFF551507)

val lightPink = Color(0xFF845AED)
val brightPink = Color(0xFF7136ED)
val midPink = Color(0xFF601DD3)
val deepPink = Color(0xFF4F03BA)
val darkPink = Color(0xFF331E61)

val purple10 = Color(0xFFF3F0FF)
val purple20 = Color(0xFFE4DDFF)
val purple30 = Color(0xFFDAD1FF)
val purple40 = Color(0xFFC5B6FF)
val purple50 = Color(0xFF9276F0)
val purple60 = Color(0xFF845AED)
val purple70 = Color(0xFF7136ED)
val purple80 = Color(0xFF601DD3)
val purple90 = Color(0xFF4F03BA)
val purple100 = Color(0xFF331E61)

val raspberry10 = Color(0xFFFAF0F3)
val raspberry20 = Color(0xFFF9D7DF)
val raspberry30 = Color(0xFFFBBCCB)
val raspberry40 = Color(0xFFFAABBF)
val raspberry50 = Color(0xFFF07F9B)
val raspberry60 = Color(0xFFE05779)
val raspberry70 = Color(0xFFBF3255)
val raspberry80 = Color(0xFFAA2C4B)
val raspberry90 = Color(0xFF962742)
val raspberry100 = Color(0xFF5C051B)

val white = Color(0xFFFFFFFF)
