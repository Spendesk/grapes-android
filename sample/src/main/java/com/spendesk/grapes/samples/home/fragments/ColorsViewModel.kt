package com.spendesk.grapes.samples.home.fragments

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.components.CoverBlockModel
import com.spendesk.grapes.samples.components.CoverCardView
import com.spendesk.grapes.samples.components.CoverListItemView
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
class ColorsViewModel @Inject constructor() : ViewModel() {

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
                ColorsBlockModel.Section("Primary"),
                ColorsBlockModel.Cover(
                    CoverListItemView.Configuration(
                        items = listOf(
                            CoverBlockModel.Cover(CoverCardView.Configuration(R.color.mainPrimaryDark, "Primary Dark"))
                        )
                    )
                )
            )
        )

}