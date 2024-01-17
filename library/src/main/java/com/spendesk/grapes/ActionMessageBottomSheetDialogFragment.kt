package com.spendesk.grapes

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.spendesk.grapes.databinding.FragmentBottomSheetInfoBinding
import com.spendesk.grapes.extensions.getHeight
import com.spendesk.grapes.extensions.gone
import com.spendesk.grapes.extensions.visible
import com.spendesk.grapes.extensions.visibleOrGone
import com.spendesk.grapes.extensions.visibleWithTextOrGone
import com.google.android.material.R as MaterialR

/**
 * @author danyboucanova
 * @since 3/17/21
 */
open class ActionMessageBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {

        fun newInstance(): ActionMessageBottomSheetDialogFragment = ActionMessageBottomSheetDialogFragment()

        private const val SCREEN_HEIGHT_PERCENTAGE_THRESHOLD_TO_CROP_MESSAGE = 0.8f
    }

    data class Configuration(
        @DrawableRes val imageResourceId: Int? = null,
        val title: CharSequence,
        val description: CharSequence? = null,
        val primaryButtonText: CharSequence? = null,
        val secondaryButtonText: CharSequence? = null,
        val shouldShowHandle: Boolean = true
    )

    // region Observable properties

    protected var onPrimaryButtonClicked: (() -> Unit)? = null
    protected var onSecondaryButtonClicked: (() -> Unit)? = null

    private var configuration: Configuration? = null

    // endregion Observable properties

    private var binding: FragmentBottomSheetInfoBinding? = null
    override fun getTheme(): Int = R.style.BottomSheetDialogStyle // TODO: handle dark theme here.

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        // This is required so when the keyboard pops up, it pushes up this dialog so everything is visible.
        with(dialog) {
            setOnShowListener { dialogInterface ->
                (dialogInterface as BottomSheetDialog).findViewById<FrameLayout>(MaterialR.id.design_bottom_sheet)?.let { bottomSheet ->
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

        // Configuration could have been updated (and then the variable is set) before view creation.
        // Once the fragment view is created, we have to make sure it is bound with desired configuration
        configuration?.let { updateConfiguration(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun updateConfiguration(configuration: Configuration) {
        this.configuration = configuration
        binding?.apply {
            configuration.imageResourceId?.let { actionMessageBottomSheetImage.setBackgroundResource(it) }
            actionMessageBottomSheetImage.isVisible = configuration.imageResourceId != null

            actionMessageBottomSheetTitleText.text = configuration.title

            actionMessageBottomSheetDescriptionText.visibleWithTextOrGone(configuration.description)
            with(actionMessageBottomSheetPrimaryButton) { configuration.primaryButtonText?.let { visible(); setText(it) } ?: gone() }
            with(actionMessageBottomSheetSecondaryButton) { configuration.secondaryButtonText?.let { visible(); setText(it) } ?: gone() }
            actionMessageBottomSheetPullView.visibleOrGone(configuration.shouldShowHandle)

            actionMessageBottomSheetDescriptionText.apply {
                viewTreeObserver.addOnGlobalLayoutListener(
                    object : OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            val parentHeight = requireActivity().getHeight()
                            val bottomSheetHeight = binding?.root?.height

                            if (bottomSheetHeight != null && bottomSheetHeight.toFloat() / parentHeight > SCREEN_HEIGHT_PERCENTAGE_THRESHOLD_TO_CROP_MESSAGE) {
                                maxLines = measuredHeight / lineHeight
                            } else {
                                maxLines = Int.MAX_VALUE
                            }
                            viewTreeObserver.removeOnGlobalLayoutListener(this)
                        }
                    }
                )
            }
        }
    }

    private fun bindView() {
        binding?.apply {
            actionMessageBottomSheetPrimaryButton.setOnClickListener { onPrimaryButtonClicked?.invoke() }
            actionMessageBottomSheetSecondaryButton.setOnClickListener { onSecondaryButtonClicked?.invoke() }
        }
    }
}
