package com.spendesk.grapes.messages

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.R

/**
 * @author Vivien Mahe
 * @since 2020-01-22
 */
@Deprecated("This component is deprecated. Please use the newer one MessageInlineView")
class InfoTipView : AppCompatTextView {

    // region constructor

    constructor(context: Context) : super(context) {
        initView(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.InfoTipView)
        val style = Style.fromPosition(typedArray.getInt(R.styleable.InfoTipView_style, Style.getDefault().position))
        typedArray.recycle()

        val paddingVert = resources.getDimensionPixelOffset(R.dimen.infoTipViewPaddingVert)
        val paddingHorz = resources.getDimensionPixelOffset(R.dimen.infoTipViewPaddingHorz)

        if (isInEditMode.not()) setTypeface(ResourcesCompat.getFont(context, R.font.gt_america), Typeface.NORMAL)
        gravity = Gravity.CENTER
        setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimensionPixelOffset(R.dimen.infoTipViewTextSize).toFloat())
        setPadding(paddingHorz, paddingVert, paddingHorz, paddingVert)
        setStyle(style)
    }

    // endregion constructor

    class Configuration(
        val style: Style,
        val title: CharSequence,
        @DrawableRes val drawableStart: Int? = null,
        @DimenRes val drawableStartPadding: Int? = null
    )

    enum class Style(val position: Int) {
        BRAND(0), // purple
        SUCCESS(1), // green
        WARNING(2), // orange
        WARNING_OPAQUE(3), // warning without alpha
        INFO(4), // blue with alpha
        INFO_OPAQUE(5), // blue without alpha
        INFO_NEUTRAL_OPAQUE(6), // grey without alpha
        ERROR(7), // red
        ERROR_OPAQUE(8); // red without alpha

        companion object {
            fun fromPosition(position: Int): Style {
                return try {
                    values().first { it.position == position }
                } catch (exception: NoSuchElementException) {
                    getDefault()
                }
            }

            fun getDefault() = BRAND
        }
    }

    /**
     * Update the configuration of the view
     *
     * @param configuration corresponds to the [InfoTipView] configuration class
     */
    fun updateConfiguration(configuration: Configuration) {
        setContent(configuration.style, configuration.title)

        // Set drawable left with its padding if they are provide within the configuration
        configuration.drawableStart?.let { setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(context, it), null, null, null) }
        configuration.drawableStartPadding?.let { context.resources.getDimensionPixelOffset(it) }
    }

    /**
     * This method will set the content of the view according to the style and the text passed in parameters
     *
     * @param style corresponds to the style to be set
     * @param textId corresponds to the text to be displayed
     */
    private fun setContent(style: Style, text: CharSequence) {
        setStyle(style)
        setText(text)
    }

    private fun setStyle(style: Style) {
        when (style) {
            Style.BRAND -> Pair(R.color.infoTipViewBrandText, R.drawable.background_info_tip_view_brand)
            Style.SUCCESS -> Pair(R.color.infoTipViewSuccessText, R.drawable.background_info_tip_view_success)
            Style.WARNING -> Pair(R.color.infoTipViewWarningText, R.drawable.background_info_tip_view_warning)
            Style.WARNING_OPAQUE -> Pair(R.color.infoTipViewWarningText, R.drawable.background_info_tip_view_warning_opaque)
            Style.INFO -> Pair(R.color.infoTipViewInfoText, R.drawable.background_info_tip_view_info)
            Style.INFO_OPAQUE -> Pair(R.color.infoTipViewInfoText, R.drawable.background_info_tip_view_info_opaque)
            Style.INFO_NEUTRAL_OPAQUE -> Pair(R.color.infoTipViewInfoNeutralOpaqueText, R.drawable.background_info_tip_view_info_neutral)
            Style.ERROR -> Pair(R.color.infoTipViewErrorText, R.drawable.background_info_tip_view_error)
            Style.ERROR_OPAQUE -> Pair(R.color.infoTipViewErrorOpaqueText, R.drawable.background_info_tip_view_error_opaque)
        }.let {
            setTextColor(ContextCompat.getColor(context, it.first))
            background = ContextCompat.getDrawable(context, it.second)
        }
    }
}