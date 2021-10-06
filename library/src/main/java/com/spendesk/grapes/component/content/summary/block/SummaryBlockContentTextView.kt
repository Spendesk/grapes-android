package com.spendesk.grapes.component.content.summary.block

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.spendesk.grapes.R
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockTitleView
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockView
import com.spendesk.grapes.extensions.visibleOrGone
import kotlinx.android.synthetic.main.summary_block_content_text.view.*

/**
 * @author Vivien Mahe
 * @since 07/01/2021
 */
class SummaryBlockContentTextView : SummaryBlockView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    class Configuration(
        titleConfiguration: SummaryBlockTitleView.Configuration,
        val value: CharSequence? = null
    ) : SummaryBlockView.Configuration(titleConfiguration)

    init {
        View.inflate(context, R.layout.summary_block_content_text, this)
    }

    override fun getSummaryBlockTitleView(): SummaryBlockTitleView = summaryBlockContentTextTitle

    fun updateConfiguration(configuration: Configuration) {
        super.updateConfiguration(configuration)

        summaryBlockContentValueText.visibleOrGone(configuration.value != null)
        configuration.value?.let { summaryBlockContentValueText.text = it }
    }
}