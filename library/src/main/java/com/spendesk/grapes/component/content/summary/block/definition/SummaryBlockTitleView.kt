package com.spendesk.grapes.component.content.summary.block.definition

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.SummaryBlockContentTitleBinding
import com.spendesk.grapes.extensions.*

/**
 * @author Vivien Mahe
 * @since 26/07/2021
 */
class SummaryBlockTitleView : ConstraintLayout {

    //region constructors
    constructor(context: Context) : super(context) {
        setupView(null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        setupView(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        setupView(attributeSet)
    }
    //endregion constructors

    data class Configuration(
        val startTitle: CharSequence,
        val middleTitle: CharSequence? = null,
        val endTitle: CharSequence? = null,
        @DrawableRes val drawableEnd: Int = ResourcesCompat.ID_NULL,
        val isActivated: Boolean = false,
        val isEnabled: Boolean = true,
        val isSelected: Boolean = false,
        val showProgressBar: Boolean = false
    )

    var onEndTitleTextClicked: (() -> Unit)? = null
        set(onEndTitleTextClicked) {
            field = onEndTitleTextClicked
            binding.summaryBlockContentTitleEndText.setOnClickListener { field?.invoke() }
        }

    private var binding = SummaryBlockContentTitleBinding.inflate(LayoutInflater.from(context), this, true)

    override fun setActivated(activated: Boolean) {
        super.setActivated(activated)

        // Toggle endTitle text style
        binding.summaryBlockContentTitleEndText.isActivated = activated
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)

        binding.summaryBlockContentTitleEndText.isEnabled = enabled
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)

        binding.summaryBlockContentTitleEndText.isSelected = selected
    }

    fun updateConfiguration(configuration: Configuration) {
        setTitleStartText(configuration.startTitle)
        setTitleMiddleText(configuration.middleTitle)
        setTitleEndText(configuration.endTitle)
        setTitleEndDrawable(configuration.drawableEnd)
        setProgressBarVisibility(configuration.showProgressBar)
        isActivated = configuration.isActivated
        isEnabled = configuration.isEnabled
        isSelected = configuration.isSelected
    }

    fun setTitleStartText(text: CharSequence?) {
        binding.summaryBlockContentTitleStartText.text = text ?: String.empty()
    }

    fun setTitleMiddleText(text: CharSequence?) {
        binding.summaryBlockContentTitleMiddleText.visibleOrInvisible(text != null)
        binding.summaryBlockContentTitleMiddleText.text = text
    }

    fun setTitleEndText(text: CharSequence?) {
        binding.summaryBlockContentTitleEndText.visibleOrGone(text != null)
        binding.summaryBlockContentTitleEndText.text = text
    }

    fun setTitleEndDrawable(drawable: Int) {
        if (drawable != ResourcesCompat.ID_NULL) {
            binding.summaryBlockContentTitleEndImage.visible()
            binding.summaryBlockContentTitleEndImage.setBackgroundResource(drawable)

            setProgressBarVisibility(false)
        } else {
            binding.summaryBlockContentTitleEndImage.gone()
        }
    }

    fun setProgressBarVisibility(show: Boolean) {
        binding.summaryBlockContentTitleEndProgressBar.visibleOrGone(show)
        if (show) setTitleEndDrawable(ResourcesCompat.ID_NULL)
    }

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.SummaryBlockTitleView)) {
                val titleStartText = getString(R.styleable.SummaryBlockTitleView_titleStartText)
                val titleMiddleText = getString(R.styleable.SummaryBlockTitleView_titleMiddleText)
                val titleEndText = getString(R.styleable.SummaryBlockTitleView_titleEndText)
                val titleEndDrawable = getResourceId(R.styleable.SummaryBlockTitleView_titleEndDrawable, ResourcesCompat.ID_NULL)
                recycle()

                setTitleStartText(titleStartText)
                setTitleMiddleText(titleMiddleText)
                setTitleEndText(titleEndText)
                setTitleEndDrawable(titleEndDrawable)
            }
        }
    }
}