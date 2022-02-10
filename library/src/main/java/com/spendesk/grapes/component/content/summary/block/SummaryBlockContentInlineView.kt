package com.spendesk.grapes.component.content.summary.block

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.spendesk.grapes.R
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockTitleView
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockView
import com.spendesk.grapes.databinding.SummaryBlockContentApproverBinding
import com.spendesk.grapes.databinding.SummaryBlockContentInlineBinding
import com.spendesk.grapes.list.content.summary.SummaryBlockContentAdapter
import com.spendesk.grapes.list.content.summary.SummaryBlockContentModel
import kotlinx.android.synthetic.main.summary_block_content_inline.view.*

/**
 * @author Vivien Mahe
 * @since 07/01/2021
 */
class SummaryBlockContentInlineView : SummaryBlockView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    data class Configuration(
        override val titleConfiguration: SummaryBlockTitleView.Configuration,
        val items: List<SummaryBlockContentModel.InlineKeyValue>
    ) : SummaryBlockView.Configuration(titleConfiguration)

    private val adapter = SummaryBlockContentAdapter()
    private val binding: SummaryBlockContentInlineBinding = SummaryBlockContentInlineBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.summaryBlockContentInlineList.adapter = adapter
    }

    override fun getSummaryBlockTitleView(): SummaryBlockTitleView = binding.summaryBlockContentInlineTitle

    fun updateConfiguration(configuration: Configuration) {
        super.updateConfiguration(configuration)

        adapter.updateList(configuration.items)
    }
}