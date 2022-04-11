package com.spendesk.grapes.component.content.summary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.component.SimpleEntryItemView
import com.spendesk.grapes.databinding.SummaryHeaderBinding
import com.spendesk.grapes.extensions.gone
import com.spendesk.grapes.extensions.visible
import com.spendesk.grapes.extensions.visibleOrGone
import com.spendesk.grapes.extensions.visibleWithTextOrGone
import com.spendesk.grapes.messages.MessageBlockView
import com.spendesk.grapes.messages.MessageInlineView

/**
 * @author Vivien Mahe
 * @since 07/01/2021
 */
class SummaryHeaderView : ConstraintLayout {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    data class Configuration(
        val simpleEntryItemViewConfiguration: SimpleEntryItemView.Configuration? = null,
        val amount: CharSequence? = null,
        val amountSubtitle: CharSequence? = null,
        val description: CharSequence? = null,
        val descriptionSubtitle: CharSequence? = null,
        val typeConfiguration: MessageInlineView.Configuration? = null,
        val messageBlockConfiguration: MessageBlockView.Configuration? = null
    )

    private val binding: SummaryHeaderBinding = SummaryHeaderBinding.inflate(LayoutInflater.from(context), this)

    init {
        setupView()
    }

    fun updateConfiguration(configuration: Configuration) {
        with(binding) {
            // User & supplier
            with(summaryHeaderExpenseAmountSimpleEntryView) {
                visibleOrGone(configuration.simpleEntryItemViewConfiguration != null)
                configuration.simpleEntryItemViewConfiguration?.let { updateConfiguration(configuration = it) }
            }

            // Amount and description
            summaryHeaderExpenseAmountTitle.visibleWithTextOrGone(configuration.amount)
            summaryHeaderExpenseAmountSubtitle.visibleWithTextOrGone(configuration.amountSubtitle)
            summaryHeaderExpenseDescriptionTitle.visibleWithTextOrGone(configuration.description)
            summaryHeaderExpenseDescriptionSubtitle.visibleWithTextOrGone(configuration.descriptionSubtitle)

            // Type
            with(summaryHeaderExpenseTypeMessage) {
                visibleOrGone(configuration.typeConfiguration != null)
                configuration.typeConfiguration?.let { updateConfiguration(it) }
            }

            // Deny reason
            configuration.messageBlockConfiguration
                ?.let {
                    summaryHeaderExpenseDenyReasonBlock.visible()
                    summaryHeaderExpenseDenyReasonBlock.updateConfiguration(configuration = it)
                }
                ?: run {
                    summaryHeaderExpenseDenyReasonBlock.gone()
                }
        }
    }

    private fun setupView() {
        setBackgroundColor(ContextCompat.getColor(context, R.color.summaryHeaderExpenseBackground))
    }
}