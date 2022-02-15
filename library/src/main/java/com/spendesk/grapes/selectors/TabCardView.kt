package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.ViewTabCardviewBinding
import com.spendesk.grapes.extensions.gone
import com.spendesk.grapes.extensions.visible

/**
 * @author danyboucanova
 * @since 16/11/2020
 */
class TabCardView : CardView {

    // region constructor

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    // endregion constructor

    /**
     * Configuration model of the [TabCardView]
     *
     * @param text corresponds to the actual title of the Tab item
     * @param badgeNumber corresponds to the number displayed in the badge. A string is necessary here as symbols can be added to it such as "99+".
     */
    class Configuration(
        val text: CharSequence,
        val badgeNumber: CharSequence?,
        val isActivated: Boolean
    )

    private var binding = ViewTabCardviewBinding.inflate(LayoutInflater.from(context), this)

    init {
        // Add ripple effect
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        foreground = ContextCompat.getDrawable(context, outValue.resourceId)

        cardElevation = context.resources.getDimension(R.dimen.tabCardViewRootCardElevation)
        radius = context.resources.getDimension(R.dimen.tabCardViewRootCardRadius)

        setCardBackgroundColor(ContextCompat.getColorStateList(context, R.color.selector_tab_cardview_background))
    }

    fun updateConfiguration(configuration: Configuration) {
        binding.tabCardViewText.isHovered = configuration.isActivated
        isHovered = configuration.isActivated

        setText(configuration.text)
        setBadge(configuration.badgeNumber)
    }

    fun setText(text: CharSequence) {
        binding.tabCardViewText.text = text
    }

    fun setText(@StringRes textId: Int) {
        binding.tabCardViewText.setText(textId)
    }

    fun setBadge(count: CharSequence?) =
        count?.let {
            binding.tabCardViewBadge.visible()
            binding.tabCardViewBadge.text = count.toString()
        } ?: run {
            binding.tabCardViewBadge.gone()
        }
}
