package com.spendesk.grapes.component.content.summary

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.spendesk.grapes.R
import com.spendesk.grapes.extensions.empty
import com.spendesk.grapes.extensions.visibleOrGone
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
        var endTitle: CharSequence? = null
    )

    var onEndTitleTextClicked: (() -> Unit)? = null
        set(onEndTitleTextClicked) {
            field = onEndTitleTextClicked
            summaryBlockContentTitleEndText.setOnClickListener { field?.invoke() }
        }

    init {
        View.inflate(context, R.layout.summary_block_content_title, this)

        bindView()
    }

    fun updateConfiguration(configuration: Configuration) {
        setTitleStart(configuration.startTitle)
        setTitleMiddle(configuration.middleTitle)
        setTitleEnd(configuration.endTitle)
    }

    fun setTitleStart(text: CharSequence?) {
        summaryBlockContentTitleStartText.text = text ?: String.empty()
    }

    fun setTitleMiddle(text: CharSequence?) {
//        summaryBlockContentTitleEndText.visibleOrGone(text != null)
//        summaryBlockContentTitleEndText.text = text
    }

    fun setTitleEnd(text: CharSequence?) {
        summaryBlockContentTitleEndText.visibleOrGone(text != null)
        summaryBlockContentTitleEndText.text = text
    }

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.SummaryBlockTitleView)) {
                val titleStart = getString(R.styleable.SummaryBlockTitleView_titleStart)
                val titleMiddle = getString(R.styleable.SummaryBlockTitleView_titleMiddle)
                val titleEnd = getString(R.styleable.SummaryBlockTitleView_titleEnd)
                recycle()

                setTitleStart(titleStart)
                setTitleMiddle(titleMiddle)
                setTitleEnd(titleEnd)
            }
        }
    }

    private fun bindView() {
//        summaryBlockContentTitleEndText.setOnClickListener { onEndTitleTextClicked?.invoke() }
    }
}