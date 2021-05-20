package com.spendesk.grapes

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import com.spendesk.grapes.extensions.setDrawable
import com.spendesk.grapes.extensions.setDrawableLeft


/**
 * @author danyboucanova
 * @since 26/10/2020
 */
class MessageInlineView : AppCompatTextView {

    //region constructors
    constructor(context: Context) : super(context) {
        initView(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(attrs)
    }
    //endregion constructors

    init {
        setupView()
    }

    class Configuration(
        val style: Style,
        val title: CharSequence,
        @DrawableRes val drawableStartId: Int? = null
    )

    enum class Style(val position: Int) {
        NEUTRAL(0), // gray
        SUCCESS(1), // green
        ALERT(2), // red
        WARNING(3), // orange
        INFO(4); // blue

        companion object {
            fun fromPosition(position: Int): Style {
                return try {
                    values().first { it.position == position }
                } catch (exception: NoSuchElementException) {
                    getDefault()
                }
            }

            fun getDefault() = NEUTRAL
        }
    }

    /**
     * This method will set the content of the view according to the style and the text passed in parameters.
     */
    fun updateConfiguration(configuration: Configuration) {
        setStyle(configuration.style)
        setTitle(configuration.title)
        configuration.drawableStartId?.let { setTitleDrawable(it) }
    }

    /**
     * This method will set the style of the view.
     *
     * @param style corresponds to the style to be set
     */
    fun setStyle(style: Style) {
        when (style) {
            Style.NEUTRAL -> ColorConfiguration(R.color.mainNeutralDarkest, R.color.mainNeutralLighter, R.color.mainNeutralNormal)
            Style.SUCCESS -> ColorConfiguration(R.color.mainSuccessNormal, R.color.mainSuccessLightest, R.color.mainSuccessLighter)
            Style.ALERT -> ColorConfiguration(R.color.mainAlertNormal, R.color.mainAlertLightest, R.color.mainAlertLighter)
            Style.WARNING -> ColorConfiguration(R.color.mainWarningNormal, R.color.mainWarningLightest, R.color.mainWarningLighter)
            Style.INFO -> ColorConfiguration(R.color.mainInfoNormal, R.color.mainInfoLightest, R.color.mainInfoLighter)
        }.let { configuration ->
            setTextColor(ContextCompat.getColor(context, configuration.textColor))
            setDrawable(colorId = configuration.backgroundColor, radiusId = R.dimen.messageInlineRadius, strokeSizeId = R.dimen.messageInlineStrokeSize, strokeColorId = configuration.strokeColor)
        }
    }

    fun setTitle(text: CharSequence) {
        this.text = text
    }

    fun setTitleDrawable(drawableStartId: Int) {
        if (drawableStartId != ResourcesCompat.ID_NULL) {
            compoundDrawablePadding = resources.getDimensionPixelSize(R.dimen.messageInlineTextDrawablePadding)

            val drawableSize = resources.getDimensionPixelOffset(R.dimen.messageInlineTextDrawableSize)
            // Convert drawable to a bitmap sized accordingly to be set as drawableStart in the textView.
            // The Bitmap is then converted again to a drawable but now having the right size which will not push the bounds and the size of the whole view.
            val drawableBitmap = ContextCompat.getDrawable(context, drawableStartId)?.toBitmap(drawableSize, drawableSize)
            setDrawableLeft(drawableBitmap?.toDrawable(resources))
        }
    }

    private fun initView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.MessageInlineView)) {
                val textId = getResourceId(R.styleable.MessageInlineView_android_text, TypedValue.TYPE_NULL)
                val drawableStartId = getResourceId(R.styleable.MessageInlineView_android_drawableStart, TypedValue.TYPE_NULL)
                val style = Style.fromPosition(getInt(R.styleable.MessageInlineView_messageStyle, Style.getDefault().position))
                recycle()

                if (textId != ResourcesCompat.ID_NULL) setTitle(context.getString(textId))
                setTitleDrawable(drawableStartId)
                setStyle(style)
            }
        }
    }

    private fun setupView() {
        if (isInEditMode.not()) setTypeface(ResourcesCompat.getFont(context, R.font.gt_america_bold), Typeface.NORMAL)

        gravity = Gravity.CENTER

        val paddingVert = resources.getDimensionPixelOffset(R.dimen.messageInlinePaddingVert)
        val paddingHorz = resources.getDimensionPixelOffset(R.dimen.messageInlinePaddingHorz)
        setPadding(paddingHorz, paddingVert, paddingHorz, paddingVert)
    }

    private inner class ColorConfiguration(
        @ColorRes val textColor: Int,
        @ColorRes val backgroundColor: Int,
        @ColorRes val strokeColor: Int
    )
}