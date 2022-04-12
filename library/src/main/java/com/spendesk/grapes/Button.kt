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
import com.spendesk.grapes.extensions.colorStateListCompat
import com.spendesk.grapes.extensions.setDrawableLeft
import com.spendesk.grapes.extensions.setRippleDrawable

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
                    colorBackground = R.color.buttonPrimaryBackground,
                    colorBackgroundPressed = R.color.buttonPrimaryBackgroundPressed,
                    colorBackgroundDisabled = R.color.buttonPrimaryBackgroundDisabled,
                    contentColorStateListId = R.color.btn_primary_text,
                    radius = R.dimen.buttonRadius
                )

            Style.SECONDARY ->
                ButtonConfig(
                    colorBackground = R.color.buttonSecondaryBackground,
                    colorBackgroundPressed = R.color.buttonSecondaryBackgroundPressed,
                    colorBackgroundDisabled = R.color.buttonSecondaryBackgroundDisabled,
                    contentColorStateListId = R.color.btn_secondary_text,
                    radius = R.dimen.buttonRadius,
                    stroke = R.dimen.buttonSecondaryBackgroundStroke,
                    strokeColor = R.color.buttonSecondaryBackgroundStroke
                )

            Style.ALERT ->
                ButtonConfig(
                    colorBackground = R.color.buttonAlertBackground,
                    colorBackgroundPressed = R.color.buttonAlertBackgroundPressed,
                    colorBackgroundDisabled = R.color.buttonAlertBackgroundDisabled,
                    contentColorStateListId = R.color.btn_alert_text,
                    radius = R.dimen.buttonRadius,
                    stroke = R.dimen.buttonAlertBackgroundStroke,
                    strokeColor = R.color.buttonAlertBackgroundStroke
                )

            Style.WARNING ->
                ButtonConfig(
                    colorBackground = R.color.buttonWarningBackground,
                    colorBackgroundPressed = R.color.buttonWarningBackgroundPressed,
                    colorBackgroundDisabled = R.color.buttonWarningBackgroundDisabled,
                    contentColorStateListId = R.color.btn_warning_text,
                    radius = R.dimen.buttonRadius
                )
        }.let { buttonConfig ->
            // Set background
            setRippleDrawable(
                buttonConfig.colorBackground,
                buttonConfig.colorBackgroundPressed,
                buttonConfig.colorBackgroundDisabled,
                buttonConfig.radius,
                buttonConfig.stroke,
                buttonConfig.strokeColor
            )

            // Set color to the text of the button
            setTextColor(context.colorStateListCompat(buttonConfig.contentColorStateListId))
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
        // Set basic properties
        includeFontPadding = false
        ellipsize = TextUtils.TruncateAt.MARQUEE
        gravity = Gravity.CENTER
        isClickable = true
        isFocusable = true
        isAllCaps = false
        setSingleLine()

        // Set text font
        if (!isInEditMode) setTypeface(ResourcesCompat.getFont(context, R.font.gt_america_bold), Typeface.NORMAL)

        // Set text size
        when (size) {
            Size.SMALL -> R.dimen.buttonSmallTextSize
            Size.NORMAL -> R.dimen.buttonNormalTextSize
        }.let { buttonTextSizeResId -> setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimensionPixelOffset(buttonTextSizeResId).toFloat()) }

        // Set text
        if (textId != ResourcesCompat.ID_NULL) setText(textId)

        // Set left drawable
        if (drawableStartId != ResourcesCompat.ID_NULL) {
            val paddingHorz = resources.getDimensionPixelSize(R.dimen.buttonDrawablePadding)

            setDrawableLeft(drawableStartId)
            setPadding(paddingHorz, 0, paddingHorz + compoundDrawables.first().bounds.width(), 0)
        }

        // Set style
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
