package com.spendesk.grapes.samples.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.spendesk.grapes.samples.entity.HomeTabItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

/**
 * @author danyboucanova
 * @since 12/29/20
 */
@HiltViewModel
class HomeActivityViewModel @Inject constructor() : ViewModel() {

    //region observable properties
    private val updateHomeTabItem = PublishSubject.create<List<HomeTabItem>>()
    fun updateHomeTabItem(): Observable<List<HomeTabItem>> = updateHomeTabItem
    //endregion observable properties

    fun onLifecycleStateChange(lifecycle: Lifecycle.State) =
        when (lifecycle) {
            Lifecycle.State.INITIALIZED -> {
                updateHomeTabItem.onNext(
                    listOf(HomeTabItem.Buttons, HomeTabItem.Cards, HomeTabItem.Selectors)
                )
                // At some point, the whole list should be sent when handling all the views in the app.
                // *HomeTabItem::class.nestedClasses.map { it.objectInstance as HomeTabItem }.toTypedArray()
            }
            else -> Unit // Nothing to do here.
        }
}