package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.spendesk.grapes.R
import kotlinx.android.synthetic.main.selectors_header_status_indicator.view.*

/**
 * @author danyboucanova
 * @since 10/06/2021
 */
class HeaderStatusIndicator : ConstraintLayout {

    // region constructor

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    // endregion constructor

    class Configuration(
        val pagerNumber: Int
    )

    private val progressBarIds = mutableListOf<Int>()

    init {
        View.inflate(context, R.layout.selectors_header_status_indicator, this)

        setBackgroundColor(ContextCompat.getColor(context, R.color.headerStatusIndicatorBackgroundColor))
    }

    fun updateConfiguration(configuration: Configuration) {
        for (i in 1..configuration.pagerNumber) {
            val progressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)

            with(progressBar) {
                progressDrawable = ContextCompat.getDrawable(context, R.drawable.layer_list_header_status_indicator)
                layoutParams = ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)
                id = View.generateViewId()
                progress = 0
            }
            addView(progressBar)

            progressBarIds.add(progressBar.id)
        }

        selectorsHeaderStatusIndicatorFlow.referencedIds = progressBarIds.toIntArray()
    }

    fun updateStatusIndex(statusIndex: Int) {
        if (progressBarIds.isEmpty()) {
            throw IllegalStateException("You should update the view's configuration before updating the index status")
        }

        progressBarIds.forEachIndexed { index, progressBarIdView ->
            val progressBar = findViewById<ProgressBar>(progressBarIdView)

            if (index <= statusIndex) {
                progressBar.progress = 100
            } else {
                progressBar.progress = 0
            }
        }
    }
}