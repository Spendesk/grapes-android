package com.spendesk.grapes

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.databinding.BucketActionInformativeBinding
import com.spendesk.grapes.extensions.gone
import com.spendesk.grapes.extensions.removeDrawables
import com.spendesk.grapes.extensions.setDrawableLeft
import com.spendesk.grapes.extensions.visible
import com.spendesk.grapes.messages.MessageInlineView

/**
 * @author danyboucanova
 * @since 27/10/2020
 */
open class InformativeActionBucketView : BucketView {

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
        @ColorRes val titleColor: Int = ResourcesCompat.ID_NULL,
        val subtitleText: CharSequence,
        @ColorRes val subtitleColor: Int = ResourcesCompat.ID_NULL,
        val smallButtonText: CharSequence,
        val messageInlineStyle: MessageInlineView.Style? = null
    )

    fun updateConfiguration(configuration: Configuration) {
        with(binding) {
            actionInformativeTitleText.text = configuration.title
            configuration.titleColor
                .takeIf { it != ResourcesCompat.ID_NULL }
                ?.let { actionInformativeTitleText.setTextColor(ContextCompat.getColor(context, it)) }
            actionInformativeButton.setText(configuration.smallButtonText)

            if (configuration.messageInlineStyle != null) {
                actionInformativeMessageInline.visible()
                actionInformativeSubtitleText.gone()
                actionInformativeMessageInline.updateConfiguration(configuration = MessageInlineView.Configuration(configuration.messageInlineStyle, configuration.subtitleText))
            } else {
                actionInformativeMessageInline.gone()
                actionInformativeSubtitleText.visible()
                actionInformativeSubtitleText.text = configuration.subtitleText
            }
        }
    }

    fun setTitle(title: CharSequence) {
        binding.actionInformativeTitleText.text = title
    }

    private fun bindView() {
        binding.actionInformativeButton.setOnClickListener { onButtonClick?.invoke() }
    }

    private fun updateSubtitleDrawable(textView: TextView, drawableResId: Int) =
        when (drawableResId) {
            0 -> textView.removeDrawables()
            else -> textView.setDrawableLeft(drawableResId)
        }
}