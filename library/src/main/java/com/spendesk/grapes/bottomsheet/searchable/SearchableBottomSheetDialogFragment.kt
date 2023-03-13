package com.spendesk.grapes.bottomsheet.searchable

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.FragmentBottomSheetSearchableBinding
import com.spendesk.grapes.extensions.*
import com.spendesk.grapes.list.simple.SimpleListAdapter
import com.spendesk.grapes.list.simple.SimpleListModel
import java.io.Serializable

/**
 * @author Vivien Mahe
 * @since 05/02/2021
 **/
class SearchableBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(configuration: Configuration) = SearchableBottomSheetDialogFragment().apply {
            isCancelable = false
            arguments = Bundle().apply {
                putSerializable(INTENT_CONFIGURATION, configuration)
            }
        }

        private const val INTENT_CONFIGURATION = "configuration"
        private const val EDITTEXT_TEXT_CHANGED_DELAY = 500L // Milliseconds
    }

    data class Configuration(
        val title: CharSequence,
        val searchInputText: CharSequence? = null,
        val hintText: CharSequence? = null
    ) : Serializable

    private var binding: FragmentBottomSheetSearchableBinding? = null
    private val adapter = SimpleListAdapter()
    private var configuration: Configuration? = null

    // region Observable properties

    var onItemClicked: ((item: SimpleListModel) -> Unit)? = null
    var onSearchInputChanged: ((searchInput: String) -> Unit)? = null
    var onCancelListener: (() -> Unit)? = null

    // endregion Observable properties

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
        binding = FragmentBottomSheetSearchableBinding.inflate(inflater, container, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)
        setupInsets()
        bindView()

        Handler(Looper.getMainLooper()).postDelayed({ view.forceHideKeyboard() }, 50)
    }

    private fun setupInsets() {
        dialog?.window?.decorView?.setOnApplyWindowInsetsListener { _, windowInsets ->
            val bottomPaddingConsideringBottomInsets = WindowInsetsCompat
                .toWindowInsetsCompat(windowInsets)
                .getInsets(WindowInsetsCompat.Type.ime())
                .bottom

            binding?.root?.updatePadding(
                bottom = bottomPaddingConsideringBottomInsets
            )
            windowInsets
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    fun showSearchProgressBar(show: Boolean) {
        binding?.searchableSheetSearchInput?.showProgressBar(show)
    }

    fun updateViewState(viewState: SearchableBottomSheetDialogFragmentViewState) {
        when (viewState) {
            is SearchableBottomSheetDialogFragmentViewState.Content -> {
                adapter.updateList(items = viewState.items)
                binding?.searchableSheetEmptyStateGroup?.gone()
            }

            is SearchableBottomSheetDialogFragmentViewState.Empty -> {
                adapter.updateList(items = listOf())
                binding?.apply {
                    searchableSheetEmptyStateTitleText.text = viewState.title
                    searchableSheetEmptyStateGroup.visible()
                }
            }

            is SearchableBottomSheetDialogFragmentViewState.Error -> Unit
        }
    }

    private fun setupView(view: View) {
        // Makes the BottomSheet take the whole screen height
        view.afterMeasured { layoutParams = layoutParams.apply { height = requireActivity().getHeight() } }

        binding?.apply {
            searchableSheetHeaderTitle.text = configuration?.title
            configuration?.searchInputText?.let { searchableSheetSearchInput.getEditText().setText(it) }
            searchableSheetSearchInput.getEditText().hint = configuration?.hintText

            searchableSheetList.adapter = adapter
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        onCancelListener?.invoke()
        super.onCancel(dialog)
    }

    private fun bindView() {
        binding?.apply {
            searchableSheetHeaderCloseButton.setOnClickListener { dialog?.cancel() }
            searchableSheetSearchInput.getEditText().afterTextChangedWith(EDITTEXT_TEXT_CHANGED_DELAY) { withActivityAttached { runOnUiThread { onSearchInputChanged?.invoke(it.trim()) } } }
        }

        with(adapter) {
            onItemSelected = { _, item -> withActivityAttached { runOnUiThread { onItemClicked?.invoke(item) } } }
        }
    }
}
