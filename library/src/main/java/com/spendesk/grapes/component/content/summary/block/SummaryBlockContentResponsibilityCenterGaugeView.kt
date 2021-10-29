package com.spendesk.grapes.component.content.summary.block

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.extensions.setDrawable
import com.spendesk.grapes.extensions.visible
import kotlinx.android.synthetic.main.view_responsibility_center_gauge.view.*

/**
 * @author danyboucanova
 * @since 2/1/21
 */
class SummaryBlockContentResponsibilityCenterGaugeView : ConstraintLayout {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    companion object Constants {
        const val MAX_GAUGES = 3
    }

    data class Configuration(
        val gauges: List<Gauge>,
        @FloatRange(from = 0.0, to = 1.0) val threshold: Float? = null
    )

    class Gauge(
        @DrawableRes val resourceId: Int = ResourcesCompat.ID_NULL,
        @FloatRange(from = 0.0, to = 1.0) val ratio: Float
    )

    init {
        View.inflate(context, R.layout.view_responsibility_center_gauge, this)
    }

    fun updateConfiguration(configuration: Configuration) {
        if (configuration.gauges.size > MAX_GAUGES) {
            throw IllegalArgumentException("Error: Too many gauges (size: ${configuration.gauges.size}) given in argument. This view cannot handle more than: $MAX_GAUGES gauges.")
        }

        val gaugeViews = listOf(responsibilityCenterGaugePrimaryView, responsibilityCenterGaugeSecondaryView, responsibilityCenterGaugeTertiaryView)
        val guidelineViews = listOf(responsibilityCenterGaugePrimaryGuideline, responsibilityCenterGaugeSecondaryGuideline, responsibilityCenterGaugeTertiaryGuideline)

        // Handle Gauges
        configuration.gauges.forEachIndexed { index, gauge ->
            gaugeViews[index].visible()
            gaugeViews[index].setBackgroundResource(gauge.resourceId)
            guidelineViews[index].setGuidelinePercent(gauge.ratio)
        }

        // Handle Threshold
        configuration.threshold?.let {
            responsibilityCenterGaugeLimitView.visible()
            responsibilityCenterGaugeLimitGuideline.setGuidelinePercent(it)
            responsibilityCenterGaugeLimitView.setDrawable(colorId = R.color.responsibilityCenterGaugeViewLimitBackground, radiusId = R.dimen.responsibilityCenterGaugeLimitRadius)
        }
    }
}