package com.spendesk.grapes.samples.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spendesk.grapes.samples.model.HomeTabItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author danyboucanova
 * @since 12/29/20
 */
@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    //region observable properties
    private val _updateHomeTabItem: MutableStateFlow<List<HomeTabItem>> = MutableStateFlow(listOf())
    val updateHomeTabItem: StateFlow<List<HomeTabItem>> = _updateHomeTabItem.asStateFlow()

    //endregion observable properties

    fun onLifecycleStateChange(lifecycle: Lifecycle.State) {
        when (lifecycle) {
            Lifecycle.State.INITIALIZED -> {
                val items = listOf(
                    HomeTabItem.Colors,
                    HomeTabItem.Fonts,
                    HomeTabItem.Buttons,
                    HomeTabItem.Compose,
                    HomeTabItem.Messages,
                    HomeTabItem.Cards,
                    HomeTabItem.Selectors,
                    HomeTabItem.Inputs,
                    HomeTabItem.Lists,
                    HomeTabItem.Contents,
                    HomeTabItem.BottomSheets,
                    HomeTabItem.Loaders,
                    HomeTabItem.Keyboards
                )
                viewModelScope.launch {
                    _updateHomeTabItem.emit(items)
                }
                // At some point, the whole list should be sent when handling all the views in the app.
                // *HomeTabItem::class.nestedClasses.map { it.objectInstance as HomeTabItem }.toTypedArray()
            }

            else -> Unit // Nothing to do here.
        }
    }
}
