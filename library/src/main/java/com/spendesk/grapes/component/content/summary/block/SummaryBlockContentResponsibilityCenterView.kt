package com.spendesk.grapes.component.content.summary.block

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.spendesk.grapes.component.content.summary.block.SummaryBlockContentResponsibilityCenterGaugeView.Constants.MAX_GAUGES
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockTitleView
import com.spendesk.grapes.component.content.summary.block.definition.SummaryBlockView
import com.spendesk.grapes.databinding.SummaryBlockContentResponsibilityCenterBinding
import com.spendesk.grapes.extensions.visibleOrGone

/**
 * @author danyboucanova
 * @since 2/1/21
 */
class SummaryBlockContentResponsibilityCenterView : SummaryBlockView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    companion object Constants {
        const val MAX_LEGENDS = MAX_GAUGES
    }

    data class Configuration(
        override val titleConfiguration: SummaryBlockTitleView.Configuration,
        val description: CharSequence,
        val gaugeViewConfiguration: SummaryBlockContentResponsibilityCenterGaugeView.Configuration,
        val legendConfiguration: List<SummaryBlockContentResponsibilityCenterGaugeLegendView.Configuration> = ArrayList(),
        val showLegend: Boolean = false
    ) : SummaryBlockView.Configuration(titleConfiguration)

    private val binding: SummaryBlockContentResponsibilityCenterBinding = SummaryBlockContentResponsibilityCenterBinding.inflate(LayoutInflater.from(context), this, true)

    override fun getSummaryBlockTitleView(): SummaryBlockTitleView = binding.summaryBlockContentResponsibilityCenterTitle

    fun updateConfiguration(configuration: Configuration) {
        super.updateConfiguration(configuration)

        binding.summaryBlockContentResponsibilityCenterDescription.text = configuration.description

        // Handle Gauges
        binding.summaryBlockContentResponsibilityCenterGaugeView.updateConfiguration(configuration.gaugeViewConfiguration)

        // Handle Legend
        showLegend(configuration.showLegend)
        handleLegend(configuration.legendConfiguration)
    }

    fun showLegend(visibility: Boolean) = binding.summaryBlockContentResponsibilityCenterLegendFlow.visibleOrGone(visibility)

    private fun handleLegend(legendConfiguration: List<SummaryBlockContentResponsibilityCenterGaugeLegendView.Configuration>) {
        if (legendConfiguration.size > MAX_LEGENDS) {
            throw IllegalArgumentException("Error: Too many legends (size: ${legendConfiguration.size}) given in argument. This view cannot handle more than: $MAX_LEGENDS legends.")
        }

        // Configure each legend
        with(binding) {
            val legendViews =
                listOf(summaryBlockContentResponsibilityCenterLegendFlowPrimary, summaryBlockContentResponsibilityCenterLegendFlowSecondary, summaryBlockContentResponsibilityCenterLegendFlowTertiary)

            legendConfiguration.forEachIndexed { index, legend ->
                summaryBlockContentResponsibilityCenterLegendFlow.referencedIds += legendViews[index].id
                legendViews[index].updateConfiguration(legend)
            }
        }
    }
}