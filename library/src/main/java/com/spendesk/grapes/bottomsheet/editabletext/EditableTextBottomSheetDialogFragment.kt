package com.spendesk.grapes.bottomsheet.editabletext

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.FragmentBottomSheetEditableTextBinding
import com.spendesk.grapes.extensions.*
import java.io.Serializable

/**
 * @author Vivien Mahe
 * @since 04/10/2021
 **/
class EditableTextBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(configuration: Configuration) = EditableTextBottomSheetDialogFragment().apply {
            isCancelable = false
            arguments = Bundle().apply {
                putSerializable(INTENT_CONFIGURATION, configuration)
            }
        }

        private const val INTENT_CONFIGURATION = "configuration"
        private const val EDITTEXT_TEXT_CHANGED_DELAY = 50L // Milliseconds
    }

    class Configuration(
        val title: String,
        val hintText: String? = null,
        val buttonText: String? = null
    ) : Serializable

    private var binding: FragmentBottomSheetEditableTextBinding? = null
    private var configuration: Configuration? = null
    private var editTextValue: String? = null

    //region Observable properties

    var onValidateButtonClicked: ((text: String) -> Unit)? = null
    var onCancelListener: (() -> Unit)? = null

    //endregion Observable properties

    override fun getTheme(): Int = R.style.BottomSheetDialogStyle // TODO: handle dark theme here.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            configuration = it.getSerializable(INTENT_CONFIGURATION) as Configuration
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        // This is required so when the keyboard pops up, it pushes up this dialog so everything is visible.
        with(dialog) {
            setOnShowListener { dialogInterface ->
                (dialogInterface as BottomSheetDialog).findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)?.let { bottomSheet ->
                    with(BottomSheetBehavior.from(bottomSheet)) {
                        saveFlags = BottomSheetBehavior.SAVE_SKIP_COLLAPSED
                        skipCollapsed = true
                        state = BottomSheetBehavior.STATE_EXPANDED
                        isDraggable = false

                        addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                            override fun onStateChanged(bottomSheet: View, newState: Int) {
                                if (newState == BottomSheetBehavior.STATE_DRAGGING) state = BottomSheetBehavior.STATE_EXPANDED
                            }

                            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                        })
                    }
                }
            }
        }

        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBottomSheetEditableTextBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        bindView()
    }

    override fun onDestroyView() {
        binding?.textInputEditText?.hideKeyboard()
        binding = null
        super.onDestroyView()
    }

    override fun onCancel(dialog: DialogInterface) {
        onCancelListener?.invoke()
        super.onCancel(dialog)
    }

    fun updateViewState(viewState: EditableTextBottomSheetDialogFragmentViewState) {
        when (viewState) {
            is EditableTextBottomSheetDialogFragmentViewState.Content -> {
                // Save the text for later, because the binding might be null (the onCreateView() method might be not called yet)
                editTextValue = viewState.text
            }

            is EditableTextBottomSheetDialogFragmentViewState.Error -> Unit
        }
    }

    private fun setupView() {
        binding?.apply {
            headerTitle.text = configuration?.title ?: String.empty()
            validateButton.setText(configuration?.buttonText ?: String.empty())
            textInputEditText.setTextAndPositionCursorEnd(editTextValue ?: String.empty())
            textInputEditText.hint = configuration?.hintText ?: String.empty()
            updateValidateButton()
        }

        Handler(Looper.getMainLooper()).postDelayed({ binding?.textInputEditText?.showSoftKeyboard() }, EDITTEXT_TEXT_CHANGED_DELAY)
    }

    private fun bindView() {
        binding?.apply {
            headerCloseButton.setOnClickListener { dialog?.cancel() }
            validateButton.setOnClickListener { withActivityAttached { runOnUiThread { onValidateButtonClicked?.invoke(textInputEditText.text.toString().trim()) } } }
            textInputEditText.onTextChanged { withActivityAttached { runOnUiThread { updateValidateButton() } } }
        }
    }

    private fun updateValidateButton() {
        binding?.validateButton?.isEnabled = binding?.textInputEditText?.text?.isNotEmpty() ?: false
    }
}
