package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.spendesk.grapes.R
import kotlinx.android.synthetic.main.selector_switch_card.view.*

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

    class Configuration(
        val text: CharSequence,
        val isChecked: Boolean = false
    )

    init {
        View.inflate(context, R.layout.selector_switch_card, this)

        radius = resources.getDimensionPixelOffset(R.dimen.switchCardRadius).toFloat()
        elevation = resources.getDimensionPixelOffset(R.dimen.switchCardElevation).toFloat()

        setOnClickListener { switchCardSwitch.isChecked = !switchCardSwitch.isChecked }
        switchCardSwitch.setOnCheckedChangeListener { _, isChecked -> onChecked?.invoke(isChecked) }
    }

    fun updateConfiguration(configuration: Configuration) {
        switchCardSwitch.isChecked = configuration.isChecked
        switchCardText.text = configuration.text
    }
}
