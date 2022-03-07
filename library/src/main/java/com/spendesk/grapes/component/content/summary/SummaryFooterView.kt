package com.spendesk.grapes.component.content.summary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.spendesk.grapes.databinding.SummaryFooterBinding
import com.spendesk.grapes.extensions.visibleWithTextOrGone

/**
 * @author Vivien Mahe
 * @since 24/10/2021
 */
class SummaryFooterView : ConstraintLayout {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    data class Configuration(
        val title: CharSequence,
        val subtitle: CharSequence? = null,
    )

    private val binding: SummaryFooterBinding = SummaryFooterBinding.inflate(LayoutInflater.from(context), this)

    fun updateConfiguration(configuration: Configuration) {
        binding.summaryFooterTitle.text = configuration.title
        binding.summaryFooterSubtitle.visibleWithTextOrGone(configuration.subtitle)
    }
}