package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.SelectorSwitchCardBinding

/**
 * @author danyboucanova
 * @since 20/06/2021
 */
class SwitchCardView : CardView {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //endregion constructors

    var onChecked: ((Boolean) -> Unit)? = null

    data class Configuration(
        val text: CharSequence,
        val isChecked: Boolean = false
    )

    private val binding: SelectorSwitchCardBinding = SelectorSwitchCardBinding.inflate(LayoutInflater.from(context), this)

    init {
        radius = resources.getDimensionPixelOffset(R.dimen.switchCardRadius).toFloat()
        elevation = resources.getDimensionPixelOffset(R.dimen.switchCardElevation).toFloat()

        setOnClickListener { binding.switchCardSwitch.isChecked = !binding.switchCardSwitch.isChecked }
        binding.switchCardSwitch.setOnCheckedChangeListener { _, isChecked -> onChecked?.invoke(isChecked) }
    }

    fun updateConfiguration(configuration: Configuration) {
        binding.switchCardSwitch.isChecked = configuration.isChecked
        binding.switchCardText.text = configuration.text
    }
}
