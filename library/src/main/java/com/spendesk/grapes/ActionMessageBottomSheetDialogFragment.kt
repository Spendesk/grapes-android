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
import com.spendesk.grapes.extensions.visibleWithTextOrGone
import kotlinx.android.synthetic.main.fragment_bottom_sheet_info.*

/**
 * @author danyboucanova
 * @since 3/17/21
 */
open class ActionMessageBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(): ActionMessageBottomSheetDialogFragment = ActionMessageBottomSheetDialogFragment()
    }

    class Configuration(
        @DrawableRes val imageResourceId: Int,
        val title: CharSequence,
        val description: CharSequence? = null,
        val primaryButtonText: CharSequence? = null,
        val secondaryButtonText: CharSequence? = null
    )

    protected var onPrimaryButtonClicked: (() -> Unit)? = null
    protected var onSecondaryButtonClicked: (() -> Unit)? = null

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_bottom_sheet_info, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
    }

    override fun getTheme(): Int {
        return R.style.BottomSheetDialogStyle // TODO: handle dark theme here.
    }

    fun updateConfiguration(configuration: Configuration) {
        actionMessageBottomSheetImage.setBackgroundResource(configuration.imageResourceId)
        actionMessageBottomSheetTitleText.text = configuration.title

        actionMessageBottomSheetDescriptionText.visibleWithTextOrGone(configuration.description)
        actionMessageBottomSheetPrimaryButton.visibleWithTextOrGone(configuration.primaryButtonText)
        actionMessageBottomSheetSecondaryButton.visibleWithTextOrGone(configuration.secondaryButtonText)
    }

    fun setPrimaryButtonEnabled(isEnabled: Boolean) {
        actionMessageBottomSheetPrimaryButton.isEnabled = isEnabled
    }

    fun setSecondaryButtonEnabled(isEnabled: Boolean) {
        actionMessageBottomSheetSecondaryButton.isEnabled = isEnabled
    }

    private fun bindView() {
        actionMessageBottomSheetPrimaryButton.setOnClickListener { onPrimaryButtonClicked?.invoke() }
        actionMessageBottomSheetSecondaryButton.setOnClickListener { onSecondaryButtonClicked?.invoke() }
    }
}