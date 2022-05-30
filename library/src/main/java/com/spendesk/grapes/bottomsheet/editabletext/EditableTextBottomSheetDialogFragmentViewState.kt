package com.spendesk.spendesk.presentation.view.bottomsheet.editabletext

/**
 * @author Vivien Mahe
 * @since 04/10/2021
 */
sealed class EditableTextBottomSheetDialogFragmentViewState {
    class Content(val text: CharSequence? = null) : EditableTextBottomSheetDialogFragmentViewState()
    object Error : EditableTextBottomSheetDialogFragmentViewState()
}