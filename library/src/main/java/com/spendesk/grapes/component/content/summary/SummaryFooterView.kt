package com.spendesk.grapes.component.content.summary

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.spendesk.grapes.R
import com.spendesk.grapes.extensions.visibleWithTextOrGone
import kotlinx.android.synthetic.main.summary_footer.view.*

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

    init {
        View.inflate(context, R.layout.summary_footer, this)
    }

    fun updateConfiguration(configuration: Configuration) {
        summaryFooterTitle.text = configuration.title
        summaryFooterSubtitle.visibleWithTextOrGone(configuration.subtitle)
    }
}