package com.spendesk.grapes

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.annotation.*
import androidx.annotation.IntRange
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.TextViewCompat
import com.spendesk.grapes.databinding.ButtonBinding
import com.spendesk.grapes.extensions.*

/**
 * Implementation of the Grapes Button which handles three different styles: Primary Button, Secondary Button and Alert Button.
 *
 * @author danyboucanova
 * @since 10/09/2020
 */
class Button : CardView {

    // region constructors

    constructor(context: Context) : super(context) {
        setupView(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setupView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setupView(attrs)
    }

    // endregion constructors

    data class Configuration(
        val text: CharSequence,
        val style: Style,
        val loaderType: LoaderType = LoaderType.getDefault(),
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

    enum class LoaderType(val position: Int) {
        NONE(-1),
        HORIZONTAL(0),
        CIRCULAR(1);

        companion object {
            fun fromPosition(position: Int): LoaderType {
                return try {
                    values().first { it.position == position }
                } catch (exception: NoSuchElementException) {
                    getDefault()
                }
            }

            fun getDefault() = NONE
        }
    }

    private lateinit var size: Size

    private var binding = ButtonBinding.inflate(LayoutInflater.from(context), this)

    init {
        isClickable(isClickable = true)
    }

    /**
     * Set the button's height according to its [Size].
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = MeasureSpec.getSize(widthMeasureSpec)

        val desiredHeight = when (size) {
            Size.NORMAL -> resources.getDimensionPixelSize(R.dimen.buttonHeight)
            Size.SMALL -> resources.getDimensionPixelSize(R.dimen.buttonSmallHeight)
        }

        super.onMeasure(
            MeasureSpec.makeMeasureSpec(desiredWidth, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(desiredHeight, MeasureSpec.EXACTLY)
        )
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)

        binding.text.isEnabled = enabled
        binding.container.isEnabled = enabled
    }

    fun updateConfiguration(configuration: Configuration) {
        setText(configuration.text)
        setStyle(configuration.style)
        setLoaderType(configuration.loaderType)
        isEnabled = configuration.isEnabled
    }

    fun setText(text: CharSequence) {
        binding.text.text = text
    }

    fun setText(@StringRes textResId: Int) {
        if (textResId != TypedValue.TYPE_NULL) binding.text.setText(textResId)
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
                    contentTextColorStateList = R.color.btn_primary_text,
                    radius = R.dimen.buttonRadius
                )

            Style.SECONDARY ->
                ButtonConfig(
                    colorBackground = R.color.buttonSecondaryBackground,
                    colorBackgroundPressed = R.color.buttonSecondaryBackgroundPressed,
                    colorBackgroundDisabled = R.color.buttonSecondaryBackgroundDisabled,
                    contentTextColorStateList = R.color.btn_secondary_text,
                    radius = R.dimen.buttonRadius,
                    stroke = R.dimen.buttonSecondaryBackgroundStroke,
                    strokeColor = R.color.buttonSecondaryBackgroundStroke
                )

            Style.ALERT ->
                ButtonConfig(
                    colorBackground = R.color.buttonAlertBackground,
                    colorBackgroundPressed = R.color.buttonAlertBackgroundPressed,
                    colorBackgroundDisabled = R.color.buttonAlertBackgroundDisabled,
                    contentTextColorStateList = R.color.btn_alert_text,
                    radius = R.dimen.buttonRadius,
                    stroke = R.dimen.buttonAlertBackgroundStroke,
                    strokeColor = R.color.buttonAlertBackgroundStroke
                )

            Style.WARNING ->
                ButtonConfig(
                    colorBackground = R.color.buttonWarningBackground,
                    colorBackgroundPressed = R.color.buttonWarningBackgroundPressed,
                    colorBackgroundDisabled = R.color.buttonWarningBackgroundDisabled,
                    contentTextColorStateList = R.color.btn_warning_text,
                    radius = R.dimen.buttonRadius
                )
        }.let { buttonConfig ->
            radius = resources.getDimension(buttonConfig.radius)
            cardElevation = 0f

            // Set background
            binding.container.setRippleDrawable(
                colorId = buttonConfig.colorBackground,
                colorPressedId = buttonConfig.colorBackgroundPressed,
                colorDisableId = buttonConfig.colorBackgroundDisabled,
                radiusId = buttonConfig.radius,
                strokeId = buttonConfig.stroke,
                strokeColorId = buttonConfig.strokeColor
            )

            // Set color to the text of the button
            binding.text.setTextColor(context.colorStateListCompat(buttonConfig.contentTextColorStateList))

            // Set color to the icon if there is one
            if (binding.text.compoundDrawables.isNotEmpty()) {
                TextViewCompat.setCompoundDrawableTintList(binding.text, context.colorStateListCompat(buttonConfig.contentTextColorStateList))
            }

            // Set color to the horizontal progress bar
            with(binding.horizontalProgressBar) {
                setIndicatorColor(context.colorCompat(buttonConfig.colorBackgroundPressed))
                trackColor = Color.TRANSPARENT
                trackCornerRadius = resources.getDimensionPixelSize(R.dimen.bigMargin)
                trackThickness = when (size) {
                    Size.NORMAL -> resources.getDimensionPixelSize(R.dimen.buttonHeight)
                    Size.SMALL -> resources.getDimensionPixelSize(R.dimen.buttonSmallHeight)
                }
            }

            // Set color to the circular progress bar
            binding.circularProgressBar.setIndicatorColor(context.colorCompat(buttonConfig.contentTextColorStateList))
        }
    }

    /**
     * Sets the type of loader to be displayed for this button:
     * - [LoaderType.HORIZONTAL] will display a horizontal progress bar as background of this button, the value can be updated via [updateLoaderProgress].
     * - [LoaderType.CIRCULAR] will display an infinite circular progress bar on the right side of the button.
     * - [LoaderType.NONE] will remove any loader.
     */
    fun setLoaderType(loaderType: LoaderType) {
        with(binding) {
            when (loaderType) {
                LoaderType.HORIZONTAL -> {
                    isClickable(isClickable = false)
                    horizontalProgressBar.visible()
                    circularProgressBar.gone()
                }

                LoaderType.CIRCULAR -> {
                    isClickable(isClickable = false)
                    horizontalProgressBar.gone()
                    circularProgressBar.visible()
                }

                LoaderType.NONE -> {
                    isClickable(isClickable = true)
                    horizontalProgressBar.gone()
                    circularProgressBar.gone()
                }
            }
        }
    }

    /**
     * Updates the progress of the horizontal progress bar, if set using [LoaderType.HORIZONTAL]. The range of the progress is 0 to 100.
     *
     * @param progress Value of the progress bar within the range 0 to 100.
     */
    fun updateLoaderProgress(@IntRange(from = 0, to = 100) progress: Int) {
        binding.horizontalProgressBar.setProgressCompat(progress, true)
    }

    private fun setupView(attributeSet: AttributeSet?) {
        with(context.obtainStyledAttributes(attributeSet, R.styleable.Button)) {
            val buttonSize = Size.fromPosition(getInt(R.styleable.Button_grapesButtonSize, Size.getDefault().position))
            val textId = getResourceId(R.styleable.Button_android_text, TypedValue.TYPE_NULL)
            val drawableStartId = getResourceId(R.styleable.Button_android_drawableStart, TypedValue.TYPE_NULL)
            val enabled = getBoolean(R.styleable.Button_android_enabled, true)
            val buttonStyle = Style.fromPosition(getInt(R.styleable.Button_grapesButtonStyle, Style.getDefault().position))
            val loaderType = LoaderType.fromPosition(getInt(R.styleable.Button_grapesButtonLoaderType, LoaderType.getDefault().position))
            recycle()

            isEnabled = enabled
            size = buttonSize
            setText(textResId = textId)
            configureTextView(drawableStartId = drawableStartId)
            setStyle(buttonStyle = buttonStyle)
            setLoaderType(loaderType = loaderType)
        }
    }

    private fun configureTextView(drawableStartId: Int) {
        with(binding.text) {
            // Set text size depending on the requested Button size
            val buttonTextSize = when (size) {
                Size.SMALL -> resources.getDimensionPixelOffset(R.dimen.buttonSmallTextSize).toFloat()
                Size.NORMAL -> resources.getDimensionPixelOffset(R.dimen.buttonNormalTextSize).toFloat()
            }
            setTextSize(TypedValue.COMPLEX_UNIT_PX, buttonTextSize)

            // Set left drawable if set
            if (drawableStartId != ResourcesCompat.ID_NULL) {
                val paddingHorz = resources.getDimensionPixelSize(R.dimen.buttonDrawablePadding)

                setDrawableLeft(drawableStartId)
                setPadding(paddingHorz, 0, paddingHorz + compoundDrawables.first().bounds.width(), 0)
            }
        }
    }

    private fun isClickable(isClickable: Boolean) {
        this.isClickable = isClickable
        this.isFocusable = isClickable
    }

    private inner class ButtonConfig(
        @ColorRes val colorBackground: Int,
        @ColorRes val colorBackgroundPressed: Int,
        @ColorRes val colorBackgroundDisabled: Int,
        @ColorRes val contentTextColorStateList: Int,
        @DimenRes val radius: Int = 0,
        @DimenRes val stroke: Int = 0,
        @ColorRes val strokeColor: Int = 0
    )
}
