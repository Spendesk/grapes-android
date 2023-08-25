package com.spendesk.grapes.samples.home.fragments

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.components.list.ColorSampleBlockModel
import com.spendesk.grapes.samples.components.ColorSampleCardView
import com.spendesk.grapes.samples.components.list.ColorSampleListItemView
import com.spendesk.grapes.samples.core.components.translator.Translator
import com.spendesk.grapes.samples.home.fragments.list.ColorsBlockModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
    private val _updateColorsItem: MutableStateFlow<List<ColorsBlockModel>> = MutableStateFlow(listOf())
    val updateColorsItem: StateFlow<List<ColorsBlockModel>> = _updateColorsItem.asStateFlow()

    // endregion observable properties

    fun onLifecycleStateChange(lifecycle: Lifecycle.State) =
        when (lifecycle) {
            Lifecycle.State.INITIALIZED,
            Lifecycle.State.CREATED -> updateColorsList()
            else -> Unit // Nothing else to do here.
        }

    private fun updateColorsList() {
        viewModelScope.launch {
            _updateColorsItem.emit(
                listOf(
                    ColorsBlockModel.Section(translator.getString(R.string.colorsPrimary)),
                    ColorsBlockModel.Color(
                        ColorSampleListItemView.Configuration(
                            items = listOf(
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainPrimaryDark, translator.getString(R.string.colorsPrimaryDark))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainPrimaryNormal, translator.getString(R.string.colorsPrimaryNormal))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainPrimaryLight, translator.getString(R.string.colorsPrimaryLight))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainPrimaryLighter, translator.getString(R.string.colorsPrimaryLighter))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainPrimaryLightest, translator.getString(R.string.colorsPrimaryLightest)))
                            )
                        )
                    ),
                    ColorsBlockModel.Section(translator.getString(R.string.colorsInfo)),
                    ColorsBlockModel.Color(
                        ColorSampleListItemView.Configuration(
                            items = listOf(
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainInfoNormal, translator.getString(R.string.colorsInfoNormal))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainInfoLighter, translator.getString(R.string.colorsInfoLighter))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainInfoLightest, translator.getString(R.string.colorsInfoLightest)))
                            )
                        )
                    ),
                    ColorsBlockModel.Section(translator.getString(R.string.colorsSuccess)),
                    ColorsBlockModel.Color(
                        ColorSampleListItemView.Configuration(
                            items = listOf(
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainSuccessNormal, translator.getString(R.string.colorsSuccessNormal))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainSuccessLighter, translator.getString(R.string.colorsSuccessLighter))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainSuccessLightest, translator.getString(R.string.colorsSuccessLightest)))
                            )
                        )
                    ),
                    ColorsBlockModel.Section(translator.getString(R.string.colorsWarning)),
                    ColorsBlockModel.Color(
                        ColorSampleListItemView.Configuration(
                            items = listOf(
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainWarningDark, translator.getString(R.string.colorsWarningDark))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainWarningNormal, translator.getString(R.string.colorsWarningNormal))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainWarningLighter, translator.getString(R.string.colorsWarningLighter))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainWarningLightest, translator.getString(R.string.colorsWarningLightest)))
                            )
                        )
                    ),
                    ColorsBlockModel.Section(translator.getString(R.string.colorsAlert)),
                    ColorsBlockModel.Color(
                        ColorSampleListItemView.Configuration(
                            items = listOf(
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainAlertDark, translator.getString(R.string.colorsAlertDark))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainAlertNormal, translator.getString(R.string.colorsAlertNormal))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainAlertLighter, translator.getString(R.string.colorsAlertLighter))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainAlertLightest, translator.getString(R.string.colorsAlertLightest)))
                            )
                        )
                    ),
                    ColorsBlockModel.Section(translator.getString(R.string.colorsNeutral)),
                    ColorsBlockModel.Color(
                        ColorSampleListItemView.Configuration(
                            items = listOf(
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainNeutralDarkest, translator.getString(R.string.colorsNeutralDarkest))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainNeutralDarker, translator.getString(R.string.colorsNeutralDarker))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainNeutralDark, translator.getString(R.string.colorsNeutralDark))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainNeutralNormal, translator.getString(R.string.colorsNeutralNormal))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainNeutralLight, translator.getString(R.string.colorsNeutralLight))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainNeutralLighter, translator.getString(R.string.colorsNeutralLighter))),
                            )
                        )
                    ),
                    ColorsBlockModel.Section(translator.getString(R.string.colorsStructural)),
                    ColorsBlockModel.Color(
                        ColorSampleListItemView.Configuration(
                            items = listOf(
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainWhite, translator.getString(R.string.colorsStructuralWhite))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainBlack, translator.getString(R.string.colorsStructuralBlack))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainComplementary, translator.getString(R.string.colorsStructuralComplementary))),
                                ColorSampleBlockModel.Color(ColorSampleCardView.Configuration(R.color.mainBackground, translator.getString(R.string.colorsStructuralBackground))),
                            )
                        )
                    )
                )
            )
        }
    }
}
