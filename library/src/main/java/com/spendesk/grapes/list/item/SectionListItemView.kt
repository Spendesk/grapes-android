package com.spendesk.grapes.list.item

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding
import com.spendesk.grapes.R
import com.spendesk.grapes.extensions.visibleWithTextOrGone
import kotlinx.android.synthetic.main.list_section_item.view.*

/**
 * @author danyboucanova
 * @since 24/06/2021
 */
class SectionListItemView : ConstraintLayout {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    class Configuration(
        @DrawableRes val iconStart: Int,
        val startText: CharSequence,
        val endText: CharSequence,
    )

    enum class Style(val position: Int) {
        PRIMARY(0),
        SECONDARY(1);

        companion object {
            fun fromPosition(position: Int): Style {
                return try {
                    values().first { it.position == position }
                } catch (exception: NoSuchElementException) {
                    getDefault()
                }
            }

            fun getDefault() = PRIMARY
        }
    }

    init {
        View.inflate(context, R.layout.list_section_item, this)

        setPadding(resources.getDimensionPixelOffset(R.dimen.listSectionItemPadding))
    }

    fun updateConfiguration(configuration: Configuration) {
        listSectionItemStartImage.setBackgroundResource(configuration.iconStart)
        listSectionItemStartText.visibleWithTextOrGone(configuration.startText)
        listSectionItemEndText.visibleWithTextOrGone(configuration.endText)
    }
}