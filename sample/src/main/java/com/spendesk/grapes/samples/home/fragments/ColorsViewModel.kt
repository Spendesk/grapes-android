package com.spendesk.grapes.samples.home.fragments

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.components.list.CoverBlockModel
import com.spendesk.grapes.samples.components.CoverCardView
import com.spendesk.grapes.samples.components.list.CoverListItemView
import com.spendesk.grapes.samples.core.components.translator.Translator
import com.spendesk.grapes.samples.home.fragments.list.ColorsBlockModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

/**
 * @author danyboucanova
 * @since 21/03/2022
 */
@HiltViewModel
class ColorsViewModel @Inject constructor(
    private val translator: Translator
) : ViewModel() {

    // region observable properties

    private val updateColorsItem = BehaviorSubject.create<List<ColorsBlockModel>>()
    fun updateColorsItem(): Observable<List<ColorsBlockModel>> = updateColorsItem

    // endregion observable properties

    fun onLifecycleStateChange(lifecycle: Lifecycle.State) =
        when (lifecycle) {
            Lifecycle.State.INITIALIZED,
            Lifecycle.State.CREATED -> updateColorsList()
            else -> Unit // Nothing else to do here.
        }

    private fun updateColorsList() =
        updateColorsItem.onNext(
            listOf(
                ColorsBlockModel.Section(translator.getString(R.string.colorsPrimary)),
                ColorsBlockModel.Cover(
                    CoverListItemView.Configuration(
                        items = listOf(
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainPrimaryDark, translator.getString(R.string.colorsPrimaryDark))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainPrimaryNormal, translator.getString(R.string.colorsPrimaryNormal))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainPrimaryLight, translator.getString(R.string.colorsPrimaryLight))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainPrimaryLighter, translator.getString(R.string.colorsPrimaryLighter))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainPrimaryLightest, translator.getString(R.string.colorsPrimaryLightest)))
                        )
                    )
                ),
                ColorsBlockModel.Section(translator.getString(R.string.colorsInfo)),
                ColorsBlockModel.Cover(
                    CoverListItemView.Configuration(
                        items = listOf(
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainInfoNormal, translator.getString(R.string.colorsInfoNormal))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainInfoLighter, translator.getString(R.string.colorsInfoLighter))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainInfoLightest, translator.getString(R.string.colorsInfoLightest)))
                        )
                    )
                ),
                ColorsBlockModel.Section(translator.getString(R.string.colorsSuccess)),
                ColorsBlockModel.Cover(
                    CoverListItemView.Configuration(
                        items = listOf(
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainSuccessNormal, translator.getString(R.string.colorsSuccessNormal))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainSuccessLighter, translator.getString(R.string.colorsSuccessLighter))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainSuccessLightest, translator.getString(R.string.colorsSuccessLightest)))
                        )
                    )
                ),
                ColorsBlockModel.Section(translator.getString(R.string.colorsWarning)),
                ColorsBlockModel.Cover(
                    CoverListItemView.Configuration(
                        items = listOf(
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainWarningDark, translator.getString(R.string.colorsWarningDark))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainWarningNormal, translator.getString(R.string.colorsWarningNormal))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainWarningLighter, translator.getString(R.string.colorsWarningLighter))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainWarningLightest, translator.getString(R.string.colorsWarningLightest)))
                        )
                    )
                ),
                ColorsBlockModel.Section(translator.getString(R.string.colorsAlert)),
                ColorsBlockModel.Cover(
                    CoverListItemView.Configuration(
                        items = listOf(
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainAlertDark, translator.getString(R.string.colorsAlertDark))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainAlertNormal, translator.getString(R.string.colorsAlertNormal))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainAlertLighter, translator.getString(R.string.colorsAlertLighter))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainAlertLightest, translator.getString(R.string.colorsAlertLightest)))
                        )
                    )
                ),
                ColorsBlockModel.Section(translator.getString(R.string.colorsNeutral)),
                ColorsBlockModel.Cover(
                    CoverListItemView.Configuration(
                        items = listOf(
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainNeutralDarkest, translator.getString(R.string.colorsNeutralDarkest))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainNeutralDarker, translator.getString(R.string.colorsNeutralDarker))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainNeutralDark, translator.getString(R.string.colorsNeutralDark))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainNeutralNormal, translator.getString(R.string.colorsNeutralNormal))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainNeutralLight, translator.getString(R.string.colorsNeutralLight))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainNeutralLighter, translator.getString(R.string.colorsNeutralLighter))),
                        )
                    )
                ),
                ColorsBlockModel.Section(translator.getString(R.string.colorsStructural)),
                ColorsBlockModel.Cover(
                    CoverListItemView.Configuration(
                        items = listOf(
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.white, translator.getString(R.string.colorsStructuralWhite))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainComplementary, translator.getString(R.string.colorsStructuralComplementary))),
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainBackground, translator.getString(R.string.colorsStructuralBackground))),
                        )
                    )
                )
            )
        )
}