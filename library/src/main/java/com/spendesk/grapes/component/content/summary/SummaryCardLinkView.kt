package com.spendesk.grapes.component.content.summary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.SummaryCardLinkBinding
import com.spendesk.grapes.extensions.gone
import com.spendesk.grapes.extensions.removeDrawables
import com.spendesk.grapes.extensions.setDrawableRight
import com.spendesk.grapes.extensions.visible
import kotlinx.android.synthetic.main.component_simple_entry_item.view.*

/**
 * @author Vivien Mahe
 * @since 24/10/2021
 */
class SummaryCardLinkView : CardView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    data class Configuration(
        val title: CharSequence,
        @DrawableRes val startImage: Int = ResourcesCompat.ID_NULL
    )

    private var binding = SummaryCardLinkBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        View.inflate(context, R.layout.summary_card_link, this)

        setupView()
    }

    fun updateConfiguration(configuration: Configuration) {
        binding.summaryLinkCardTitle.text = configuration.title
        binding.summaryLinkCardImage

        when (configuration.startImage != ResourcesCompat.ID_NULL) {
            true -> with(binding.summaryLinkCardImage) { setImageResource(configuration.startImage); visible() }
            false -> binding.summaryLinkCardImage.gone()
        }
    }

    private fun setupView() {
        radius = resources.getDimension(R.dimen.contentBlockCardRadius)
        cardElevation = resources.getDimension(R.dimen.contentBlockElevation)
    }
}