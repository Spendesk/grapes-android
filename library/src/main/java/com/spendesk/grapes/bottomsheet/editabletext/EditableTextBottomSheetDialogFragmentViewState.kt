package com.spendesk.grapes.bottomsheet.editabletext

/**
 * @author Vivien Mahe
 * @since 04/10/2021
 */
sealed class EditableTextBottomSheetDialogFragmentViewState {
    class Content(val text: String? = null) : EditableTextBottomSheetDialogFragmentViewState()
    object Error : EditableTextBottomSheetDialogFragmentViewState()
}