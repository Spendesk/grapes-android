package com.spendesk.grapes

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.spendesk.grapes.databinding.FragmentBottomSheetInfoBinding
import com.spendesk.grapes.extensions.visibleOrGone
import com.spendesk.grapes.extensions.visibleWithTextOrGone

/**
 * @author danyboucanova
 * @since 3/17/21
 */
open class ActionMessageBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(): ActionMessageBottomSheetDialogFragment = ActionMessageBottomSheetDialogFragment()
    }

    data class Configuration(
        @DrawableRes val imageResourceId: Int,
        val title: CharSequence,
        val description: CharSequence? = null,
        val primaryButtonText: CharSequence? = null,
        val secondaryButtonText: CharSequence? = null,
        val shouldShowHandle: Boolean = true
    )

    // region Observable properties

    protected var onPrimaryButtonClicked: (() -> Unit)? = null
    protected var onSecondaryButtonClicked: (() -> Unit)? = null

    // endregion Observable properties

    private var binding: FragmentBottomSheetInfoBinding? = null
    override fun getTheme(): Int = R.style.BottomSheetDialogStyle // TODO: handle dark theme here.

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        // This is required so when the keyboard pops up, it pushes up this dialog so everything is visible.
        with(dialog) {
            setOnShowListener { dialogInterface ->
                (dialogInterface as BottomSheetDialog).findViewById<FrameLayout>(R.id.design_bottom_sheet)?.let { bottomSheet ->
                    val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

                    bottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_SKIP_COLLAPSED
                    bottomSheetBehavior.skipCollapsed = true
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }

        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBottomSheetInfoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun updateConfiguration(configuration: Configuration) {
        binding?.apply {
            actionMessageBottomSheetImage.setBackgroundResource(configuration.imageResourceId)
            actionMessageBottomSheetTitleText.text = configuration.title

            actionMessageBottomSheetDescriptionText.visibleWithTextOrGone(configuration.description)
            actionMessageBottomSheetPrimaryButton.visibleWithTextOrGone(configuration.primaryButtonText)
            actionMessageBottomSheetSecondaryButton.visibleWithTextOrGone(configuration.secondaryButtonText)
            actionMessageBottomSheetPullView.visibleOrGone(configuration.shouldShowHandle)
        }
    }

    private fun bindView() {
        binding?.apply {
            actionMessageBottomSheetPrimaryButton.setOnClickListener { onPrimaryButtonClicked?.invoke() }
            actionMessageBottomSheetSecondaryButton.setOnClickListener { onSecondaryButtonClicked?.invoke() }
        }
    }
}