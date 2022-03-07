package com.spendesk.grapes.component.content.summary.block

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.databinding.ViewResponsibilityCenterGaugeLegendBinding

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

    private val binding: ViewResponsibilityCenterGaugeLegendBinding = ViewResponsibilityCenterGaugeLegendBinding.inflate(LayoutInflater.from(context), this, true)

    fun updateConfiguration(configuration: Configuration) {
        with(binding) {
            responsibilityCenterGaugeLegendImage.setBackgroundResource(configuration.resourceId)
            responsibilityCenterGaugeLegendTitle.text = configuration.title
            responsibilityCenterGaugeLegendSubtitle.text = configuration.subtitle
        }
    }
}