package com.spendesk.grapes.template.header

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.spendesk.grapes.MessageBlockView
import com.spendesk.grapes.MessageInlineView
import com.spendesk.grapes.R
import com.spendesk.grapes.UserSupplierInlineView
import com.spendesk.grapes.extensions.gone
import com.spendesk.grapes.extensions.visible
import com.spendesk.grapes.extensions.visibleOrGone
import com.spendesk.grapes.extensions.visibleWithTextOrGone
import kotlinx.android.synthetic.main.summary_header.view.*

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

    class Configuration(
        val userSupplierInlineConfiguration: UserSupplierInlineView.Configuration? = null,
        val amount: CharSequence,
        val amountSubtitle: CharSequence? = null,
        val description: CharSequence,
        val descriptionSubtitle: CharSequence? = null,
        val typeConfiguration: MessageInlineView.Configuration,
        val messageBlockConfiguration: MessageBlockView.Configuration? = null
    )

    init {
        View.inflate(context, R.layout.summary_header, this)

        setupView()
    }

    fun updateConfiguration(configuration: Configuration) {
        // User & supplier
        with(summaryHeaderExpenseAmountUserSupplier) {
            visibleOrGone(configuration.userSupplierInlineConfiguration != null)
            configuration.userSupplierInlineConfiguration?.let { updateConfiguration(configuration = it) }
        }

        // Amount and description
        summaryHeaderExpenseAmountTitle.text = configuration.amount
        summaryHeaderExpenseAmountSubtitle.visibleWithTextOrGone(configuration.amountSubtitle)
        summaryHeaderExpenseDescriptionTitle.text = configuration.description
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

    private fun setupView() {
        setBackgroundColor(ContextCompat.getColor(context, R.color.templateHeaderExpenseBackground))
    }
}