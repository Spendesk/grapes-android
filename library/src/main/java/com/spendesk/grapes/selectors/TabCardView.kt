package com.spendesk.grapes.selectors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.StringRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.spendesk.grapes.R
import com.spendesk.grapes.extensions.addRippleEffect
import com.spendesk.grapes.extensions.gone
import com.spendesk.grapes.extensions.visible
import kotlinx.android.synthetic.main.view_tab_cardview.view.*

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

    init {
        View.inflate(context, R.layout.view_tab_cardview, this)

        addRippleEffect()

        cardElevation = context.resources.getDimension(R.dimen.tabCardViewRootCardElevation)
        radius = context.resources.getDimension(R.dimen.tabCardViewRootCardRadius)

        setCardBackgroundColor(ContextCompat.getColorStateList(context, R.color.selector_tab_cardview_background))
    }

    fun updateConfiguration(configuration: Configuration) {
        tabCardViewText.isHovered = configuration.isActivated
        isHovered = configuration.isActivated

        setText(configuration.text)
        setBadge(configuration.badgeNumber)
    }

    fun setText(text: CharSequence) {
        tabCardViewText.text = text
    }

    fun setText(@StringRes textId: Int) {
        tabCardViewText.setText(textId)
    }

    fun setBadge(count: CharSequence?) =
        count?.let {
            tabCardViewBadge.visible()
            tabCardViewBadge.text = count.toString()
        } ?: run {
            tabCardViewBadge.gone()
        }
}
