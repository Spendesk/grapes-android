package com.spendesk.grapes.list.simple.item

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import com.spendesk.grapes.R
import com.spendesk.grapes.extensions.visible
import com.spendesk.grapes.extensions.visibleWithTextOrGone
import kotlinx.android.synthetic.main.list_section_item.view.*

/**
 * @author danyboucanova
 * @since 24/06/2021
 */
class SectionListItemView : ConstraintLayout {

    //region constructors

    constructor(context: Context) : super(context) {
        setupView(null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        setupView(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        setupView(attributeSet)
    }

    //endregion constructors

    class Configuration(
        @DrawableRes val iconStart: Int = ResourcesCompat.ID_NULL,
        val startText: CharSequence? = null,
        val endText: CharSequence? = null,
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
        if (configuration.iconStart != ResourcesCompat.ID_NULL) {
            listSectionItemStartImage.visible()
            listSectionItemStartImage.setBackgroundResource(configuration.iconStart)
        }
        listSectionItemStartText.visibleWithTextOrGone(configuration.startText)
        listSectionItemEndText.visibleWithTextOrGone(configuration.endText)
    }

    fun setStyle(style: Style) =
        when (style) {
            Style.PRIMARY -> setBackgroundColor(ContextCompat.getColor(context, R.color.listSectionListItemViewPrimaryBackgroundColor))
            Style.SECONDARY -> setBackgroundColor(ContextCompat.getColor(context, R.color.listSectionListItemViewSecondaryBackgroundColor))
        }

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.SectionListItemView)) {
                val style = Style.fromPosition(getInt(R.styleable.SectionListItemView_sectionListItemViewStyle, Style.getDefault().position))
                recycle()

                setStyle(style)
            }
        }
    }
}