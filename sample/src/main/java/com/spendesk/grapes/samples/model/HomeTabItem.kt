package com.spendesk.grapes.samples.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author danyboucanova
 * @since 12/29/20
 */
sealed class HomeTabItem : Parcelable {

    @Parcelize
    object Colors : HomeTabItem()

    @Parcelize
    object Fonts : HomeTabItem()

    @Parcelize
    object Compose : HomeTabItem()

    @Parcelize
    object Buttons : HomeTabItem()

    @Parcelize
    object Messages : HomeTabItem()

    @Parcelize

    object Inputs : HomeTabItem()

    @Parcelize
    object Selectors : HomeTabItem()

    @Parcelize
    object Avatars : HomeTabItem()

    @Parcelize
    object Cards : HomeTabItem()

    @Parcelize
    object Lists : HomeTabItem()

    @Parcelize
    object Modals : HomeTabItem()

    @Parcelize
    object Contents : HomeTabItem()

    @Parcelize
    object BottomSheets : HomeTabItem()

    @Parcelize
    object Loaders : HomeTabItem()

    @Parcelize
    object Keyboards : HomeTabItem()
}