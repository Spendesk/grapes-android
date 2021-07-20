package com.spendesk.grapes.list.content.summary

import com.spendesk.grapes.extensions.empty
import com.spendesk.grapes.list.content.summary.item.ApproverStatusItemView
import com.spendesk.grapes.list.content.summary.item.InlineKeyValueItemView

/**
 * @author danyboucanova
 * @since 1/14/21
 */
sealed class SummaryBlockContentModel(val viewType: SummaryBlockContentViewType) {
    class ApproverStatus(val id: String = String.empty(), val configuration: ApproverStatusItemView.Configuration) : SummaryBlockContentModel(SummaryBlockContentViewType.APPROVER_STATUS)
    class InlineKeyValue(val id: String = String.empty(), val configuration: InlineKeyValueItemView.Configuration) : SummaryBlockContentModel(SummaryBlockContentViewType.INLINE_KEY_VALUE)
}