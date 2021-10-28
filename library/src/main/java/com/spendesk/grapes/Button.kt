package com.spendesk.grapes

import android.content.Context
import android.graphics.Rect
import android.graphics.Typeface
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat
import com.spendesk.grapes.extensions.colorStateListCompat
import com.spendesk.grapes.extensions.setDrawableLeft
import com.spendesk.grapes.extensions.setRippleDrawable
import java.util.*

/**
 * Implementation of the Grapes Button which handles three different styles: Primary Button, Secondary Button and Alert Button.
 *
 * @author danyboucanova
 * @since 10/09/2020
 */
class Button : AppCompatTextView {

    //region constructors
    constructor(context: Context) : super(context) {
        initButton(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initButton(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initButton(attrs)
    }
    //endregion constructors

    data class Configuration(
        val text: CharSequence,
        val style: Style,
        val isEnabled: Boolean = true
    )

    enum class Style(val position: Int) {
        PRIMARY(0), // brand and white text
        SECONDARY(1), // white and white text
        ALERT(2), // white and red text
        WARNING(3); // orange and white text

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

    enum class Size(val position: Int) {
        NORMAL(0), // normal button
        SMALL(1); // small button

        companion object {
            fun fromPosition(position: Int): Size {
                return try {
                    values().first { it.position == position }
                } catch (exception: NoSuchElementException) {
                    getDefault()
                }
            }

            fun getDefault() = NORMAL
        }
    }

    private lateinit var size: Size
    private val bounds = Rect()

    /**
     * Set the button's height according to its [Size]
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredHeight = when (size) {
            Size.NORMAL -> resources.getDimensionPixelSize(R.dimen.buttonHeight)
            Size.SMALL -> resources.getDimensionPixelSize(R.dimen.buttonSmallHeight)
        }
        val desiredWidth = when (size) {
            Size.NORMAL -> MeasureSpec.getSize(widthMeasureSpec)
            Size.SMALL -> {
                paint.getTextBounds(text.toString(), 0, text.length, bounds)
                resources.getDimensionPixelSize(R.dimen.buttonSmallPaddingStart) + bounds.width() + resources.getDimensionPixelSize(R.dimen.buttonSmallPaddingEnd)
            }
        }

        super.onMeasure(
            MeasureSpec.makeMeasureSpec(desiredWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(desiredHeight, MeasureSpec.EXACTLY)
        )
    }

    fun updateConfiguration(configuration: Configuration) {
        text = configuration.text
        setStyle(configuration.style)
        isEnabled = configuration.isEnabled
    }

    /**
     * Set dynamically the style of the button
     *
     * @param buttonStyle style of the button
     */
    fun setStyle(buttonStyle: Style) {
        when (buttonStyle) {
            Style.PRIMARY ->
                ButtonConfig(
                    R.color.buttonPrimaryBackground,
                    R.color.buttonPrimaryBackgroundPressed,
                    R.color.buttonPrimaryBackgroundDisabled,
                    R.color.btn_primary_text,
                    R.dimen.buttonRadius
                )
            Style.SECONDARY ->
                ButtonConfig(
                    R.color.buttonSecondaryBackground,
                    R.color.buttonSecondaryBackgroundPressed,
                    R.color.buttonSecondaryBackgroundDisabled,
                    R.color.btn_secondary_text,
                    R.dimen.buttonRadius,
                    R.dimen.buttonSecondaryBackgroundStroke,
                    R.color.buttonSecondaryBackgroundStroke
                )
            Style.ALERT ->
                ButtonConfig(
                    R.color.buttonAlertBackground,
                    R.color.buttonAlertBackgroundPressed,
                    R.color.buttonAlertBackgroundDisabled,
                    R.color.btn_alert_text,
                    R.dimen.buttonRadius,
                    R.dimen.buttonAlertBackgroundStroke,
                    R.color.buttonAlertBackgroundStroke
                )
            Style.WARNING ->
                ButtonConfig(
                    R.color.buttonWarningBackground,
                    R.color.buttonWarningBackgroundPressed,
                    R.color.buttonWarningBackgroundDisabled,
                    R.color.btn_warning_text,
                    R.dimen.buttonRadius
                )
        }.let { buttonConfig ->
            // set background
            setRippleDrawable(
                buttonConfig.colorBackground,
                buttonConfig.colorBackgroundPressed,
                buttonConfig.colorBackgroundDisabled,
                buttonConfig.radius,
                buttonConfig.stroke,
                buttonConfig.strokeColor
            )
            // set color to the text of the button
            setTextColor(context.colorStateListCompat(buttonConfig.contentColorStateListId))
            // set color to the icon if there is one
            if (compoundDrawables.isNotEmpty()) {
                TextViewCompat.setCompoundDrawableTintList(this, context.colorStateListCompat(buttonConfig.contentColorStateListId))
            }
        }
    }

    private fun initButton(attributeSet: AttributeSet?) {
        with(context.obtainStyledAttributes(attributeSet, R.styleable.Button)) {
            size = Size.fromPosition(getInt(R.styleable.Button_grapesButtonSize, Size.getDefault().position))

            setupView(
                textId = getResourceId(R.styleable.Button_android_text, TypedValue.TYPE_NULL),
                drawableStartId = getResourceId(R.styleable.Button_android_drawableStart, TypedValue.TYPE_NULL),
                buttonStyle = Style.fromPosition(getInt(R.styleable.Button_grapesButtonStyle, Style.getDefault().position))
            )
            recycle()
        }
    }

    private fun setupView(textId: Int, drawableStartId: Int, buttonStyle: Style) {
        includeFontPadding = false
        ellipsize = TextUtils.TruncateAt.MARQUEE
        setSingleLine()

        if (!isInEditMode) setTypeface(ResourcesCompat.getFont(context, R.font.gt_america_bold), Typeface.NORMAL)
        val buttonTextSize = when (size) {
            Size.SMALL -> resources.getDimensionPixelOffset(R.dimen.buttonSmallTextSize).toFloat()
            Size.NORMAL -> resources.getDimensionPixelOffset(R.dimen.buttonNormalTextSize).toFloat()
        }
        setTextSize(TypedValue.COMPLEX_UNIT_PX, buttonTextSize)

        gravity = Gravity.CENTER
        isClickable = true
        isFocusable = true
        isAllCaps = false

        if (textId != ResourcesCompat.ID_NULL) setText(textId)
        if (drawableStartId != ResourcesCompat.ID_NULL) {
            val paddingHorz = resources.getDimensionPixelSize(R.dimen.buttonDrawablePadding)

            setDrawableLeft(drawableStartId)
            setPadding(paddingHorz, 0, paddingHorz + compoundDrawables.first().bounds.width(), 0)
        }
        setStyle(buttonStyle)
    }

    private inner class ButtonConfig(
        @ColorRes val colorBackground: Int,
        @ColorRes val colorBackgroundPressed: Int,
        @ColorRes val colorBackgroundDisabled: Int,
        @ColorRes val contentColorStateListId: Int,
        @DimenRes val radius: Int = 0,
        @DimenRes val stroke: Int = 0,
        @ColorRes val strokeColor: Int = 0
    )
}
