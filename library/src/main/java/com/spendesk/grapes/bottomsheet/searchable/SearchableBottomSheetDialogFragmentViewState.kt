package com.spendesk.grapes.bottomsheet.searchable

import com.spendesk.grapes.list.simple.SimpleListModel

/**
 * @author Vivien Mahe
 * @since 02/08/2021
 */
sealed class SearchableBottomSheetDialogFragmentViewState {
    class Content(val items: List<SimpleListModel>) : SearchableBottomSheetDialogFragmentViewState()
    class Empty(val title: CharSequence, val message: CharSequence? = null, val buttonText: String? = null) : SearchableBottomSheetDialogFragmentViewState()
    object Error : SearchableBottomSheetDialogFragmentViewState()
}