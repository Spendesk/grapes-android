package com.spendesk.grapes.component.content.summary.noneditable

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.spendesk.grapes.R
import com.spendesk.grapes.component.content.summary.SummaryBlockView
import com.spendesk.grapes.list.content.summary.SummaryBlockContentAdapter
import com.spendesk.grapes.list.content.summary.SummaryBlockContentModel
import kotlinx.android.synthetic.main.summary_block_content_approver.view.*

/**
 * @author Vivien Mahe
 * @since 07/01/2021
 */
class SummaryBlockContentApproverView : SummaryBlockView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    class Configuration(
        val title: CharSequence,
        val items: List<SummaryBlockContentModel.ApproverStatus>
    )

    private val adapter = SummaryBlockContentAdapter()

    init {
        View.inflate(context, R.layout.summary_block_content_approver, this)

        summaryBlockContentApproverList.adapter = adapter
    }

    fun updateConfiguration(configuration: Configuration) {
        summaryBlockContentApproverTitle.text = configuration.title
        adapter.updateList(configuration.items)
    }
}