package com.spendesk.grapes.component.content.summary.block

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import kotlinx.android.synthetic.main.view_responsibility_center_gauge_legend.view.*

/**
 * @author danyboucanova
 * @since 2/16/21
 */
class SummaryBlockContentResponsibilityCenterGaugeLegendView : ConstraintLayout {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    data class Configuration(
        val title: CharSequence,
        val subtitle: CharSequence,
        val resourceId: Int = ResourcesCompat.ID_NULL
    )

    init {
        View.inflate(context, R.layout.view_responsibility_center_gauge_legend, this)
    }

    fun updateConfiguration(configuration: Configuration) {
        responsibilityCenterGaugeLegendImage.setBackgroundResource(configuration.resourceId)
        responsibilityCenterGaugeLegendTitle.text = configuration.title
        responsibilityCenterGaugeLegendSubtitle.text = configuration.subtitle
    }
}