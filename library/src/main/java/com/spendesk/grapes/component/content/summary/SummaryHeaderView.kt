package com.spendesk.grapes.component.content.summary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.UserSupplierInlineView
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
        val userSupplierInlineConfiguration: UserSupplierInlineView.Configuration? = null,
        val amount: CharSequence,
        val amountSubtitle: CharSequence? = null,
        val description: CharSequence? = null,
        val descriptionSubtitle: CharSequence? = null,
        val typeConfiguration: MessageInlineView.Configuration,
        val messageBlockConfiguration: MessageBlockView.Configuration? = null
    )

    private val binding: SummaryHeaderBinding = SummaryHeaderBinding.inflate(LayoutInflater.from(context), this)

    init {
        setupView()
    }

    fun updateConfiguration(configuration: Configuration) {
        with(binding) {
            // User & supplier
            with(summaryHeaderExpenseAmountUserSupplier) {
                visibleOrGone(configuration.userSupplierInlineConfiguration != null)
                configuration.userSupplierInlineConfiguration?.let { updateConfiguration(configuration = it) }
            }

            // Amount and description
            summaryHeaderExpenseAmountTitle.text = configuration.amount
            summaryHeaderExpenseAmountSubtitle.visibleWithTextOrGone(configuration.amountSubtitle)
            summaryHeaderExpenseDescriptionTitle.visibleWithTextOrGone(configuration.description)
            summaryHeaderExpenseDescriptionSubtitle.visibleWithTextOrGone(configuration.descriptionSubtitle)

            // Type
            summaryHeaderExpenseTypeMessage.updateConfiguration(configuration = configuration.typeConfiguration)

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