package com.spendesk.grapes

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.spendesk.grapes.databinding.BucketActionInformativeBinding
import com.spendesk.grapes.extensions.gone
import com.spendesk.grapes.extensions.visible
import com.spendesk.grapes.messages.MessageInlineView

/**
 * @author danyboucanova
 * @since 27/10/2020
 */
class InformativeActionBucketView : BucketView {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    var onButtonClick: (() -> Unit)? = null

    private val binding: BucketActionInformativeBinding = BucketActionInformativeBinding.inflate(LayoutInflater.from(context), this)

    init {
        bindView()
    }

    data class Configuration(
        val title: CharSequence,
        val smallButtonText: CharSequence,
        val subtitleText: CharSequence,
        val shouldShowChip: Boolean,
        val messageContent: MessageInlineView.Style? = null
    )

    fun updateData(configuration: Configuration) {
        with(binding) {
            actionInformativeTitleText.text = configuration.title
            actionInformativeButton.text = configuration.smallButtonText

            if (configuration.shouldShowChip) {
                actionInformativeMessage.visible()
                actionInformativeSubtitleText.gone()
                configuration.messageContent?.let { actionInformativeMessage.updateConfiguration(configuration = MessageInlineView.Configuration(it, configuration.subtitleText)) }
            } else {
                actionInformativeMessage.gone()
                actionInformativeSubtitleText.visible()
                actionInformativeSubtitleText.text = configuration.subtitleText
            }
        }
    }

    private fun bindView() {
        binding.actionInformativeButton.setOnClickListener { onButtonClick?.invoke() }
    }
}