package com.spendesk.grapes.samples.components.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.spendesk.grapes.samples.databinding.ViewCoverListBinding

/**
 * @author danyboucanova
 * @since 10/03/2022
 */
class ColorSampleListItemView : ConstraintLayout {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    private val binding = ViewCoverListBinding.inflate(LayoutInflater.from(context), this, true)

    private val adapter: ColorSampleAdapter = ColorSampleAdapter()

    class Configuration(
        val items: List<ColorSampleBlockModel>
    )

    init {
        binding.coverListView.adapter = adapter
    }

    fun updateConfiguration(configuration: Configuration) {
        adapter.updateList(configuration.items)
    }
}