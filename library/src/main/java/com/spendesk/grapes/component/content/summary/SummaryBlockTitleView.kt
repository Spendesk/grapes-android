package com.spendesk.grapes.component.content.summary

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.extensions.empty
import com.spendesk.grapes.extensions.visible
import com.spendesk.grapes.extensions.visibleOrGone
import com.spendesk.grapes.extensions.visibleOrInvisible
import kotlinx.android.synthetic.main.summary_block_content_title.view.*

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

    class Configuration(
        val startTitle: CharSequence,
        var middleTitle: CharSequence? = null,
        var endTitle: CharSequence? = null,
        @DrawableRes val drawableEnd: Int = ResourcesCompat.ID_NULL,
    )

    var onEndTitleTextClicked: (() -> Unit)? = null
        set(onEndTitleTextClicked) {
            field = onEndTitleTextClicked
            summaryBlockContentTitleEndText.setOnClickListener { field?.invoke() }
        }

    init {
        View.inflate(context, R.layout.summary_block_content_title, this)
    }

    fun updateConfiguration(configuration: Configuration) {
        setTitleStartText(configuration.startTitle)
        setTitleMiddleText(configuration.middleTitle)
        setTitleEndText(configuration.endTitle)
    }

    fun setTitleStartText(text: CharSequence?) {
        summaryBlockContentTitleStartText.text = text ?: String.empty()
    }

    fun setTitleMiddleText(text: CharSequence?) {
        summaryBlockContentTitleMiddleText.visibleOrInvisible(text != null)
        summaryBlockContentTitleMiddleText.text = text
    }

    fun setTitleEndText(text: CharSequence?) {
        summaryBlockContentTitleEndText.visibleOrGone(text != null)
        summaryBlockContentTitleEndText.text = text
    }

    fun setTitleEndDrawable(drawable: Int) {
        if (drawable != ResourcesCompat.ID_NULL) {
            summaryBlockContentTitleEndImage.visible()
            summaryBlockContentTitleEndImage.setBackgroundResource(drawable)
        }
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