package com.spendesk.grapes

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import com.spendesk.grapes.extensions.setDrawable
import com.spendesk.grapes.extensions.setDrawableLeft
import com.spendesk.grapes.extensions.visibleWithTextOrGone
import com.spendesk.grapes.internal.libs.glide.loadFromUrl
import kotlinx.android.synthetic.main.message_block_view.view.*


/**
 * @author danyboucanova
 * @since 26/10/2020
 */
class MessageBlockView : ConstraintLayout {

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
        View.inflate(context, R.layout.message_block_view, this)

        setupView()
    }

    class Configuration(
        val style: Style,
        val title: CharSequence,
        @DrawableRes val drawableStartId: Int? = null,
        val description: CharSequence? = null,
        val signatureText: CharSequence? = null,
        val signatureDrawableUrl: String? = null,
        @DrawableRes val signatureDrawablePlaceholderResId: Int? = null
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
        setDescription(configuration.description)
        setSignatureText(configuration.signatureText)
        setSignatureDrawable(url = configuration.signatureDrawableUrl, placeholderResId = configuration.signatureDrawablePlaceholderResId)
    }

    /**
     * This method will set the style of the view.
     *
     * @param style corresponds to the style to be set
     */
    fun setStyle(style: Style) {
        when (style) {
            Style.NEUTRAL -> ColorConfiguration(R.color.mainNeutralDarker, R.color.mainNeutralLight, R.color.mainNeutralNormal)
            Style.SUCCESS -> ColorConfiguration(R.color.mainSuccessNormal, R.color.mainSuccessLightest, R.color.mainSuccessLighter)
            Style.ALERT -> ColorConfiguration(R.color.mainAlertNormal, R.color.mainAlertLightest, R.color.mainAlertLighter)
            Style.WARNING -> ColorConfiguration(R.color.mainWarningNormal, R.color.mainWarningLightest, R.color.mainWarningLighter)
            Style.INFO -> ColorConfiguration(R.color.mainInfoNormal, R.color.mainInfoLightest, R.color.mainInfoLighter)
        }.let { configuration ->
            messageBlockTitle.setTextColor(ContextCompat.getColor(context, configuration.textColor))
            setDrawable(colorId = configuration.backgroundColor, radiusId = R.dimen.messageInlineRadius, strokeSizeId = R.dimen.messageInlineStrokeSize, strokeColorId = configuration.strokeColor)
        }
    }

    fun setTitle(title: CharSequence) {
        messageBlockTitle.text = title
    }

    fun setTitleDrawable(drawableStartId: Int) {
        if (drawableStartId != ResourcesCompat.ID_NULL) {
            val drawableSize = resources.getDimensionPixelOffset(R.dimen.messageInlineTextDrawableSize)
            // Convert drawable to a bitmap sized accordingly to be set as drawableStart in the textView.
            // The Bitmap is then converted again to a drawable but now having the right size which will not push the bounds and the size of the whole view.
            val drawableBitmap = ContextCompat.getDrawable(context, drawableStartId)?.toBitmap(drawableSize, drawableSize)
            messageBlockTitle.setDrawableLeft(drawableBitmap?.toDrawable(resources))
        }
    }

    fun setDescription(description: CharSequence?) {
        messageBlockDescription.visibleWithTextOrGone(description)
    }

    fun setSignatureText(signatureText: CharSequence?) {
        messageBlockSignatureText.visibleWithTextOrGone(signatureText)
    }

    fun setSignatureDrawable(url: String?, placeholderResId: Int? = 0) {
        messageBlockSignatureImage.loadFromUrl(url = url, errorResId = placeholderResId, shouldCircleCrop = true)
    }

    fun setSignatureDrawable(drawable: Drawable?) {
        messageBlockSignatureImage.setImageDrawable(drawable)
    }

    private fun initView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.MessageBlockView)) {
                val titleId = getResourceId(R.styleable.MessageBlockView_title, TypedValue.TYPE_NULL)
                val drawableStartId = getResourceId(R.styleable.MessageBlockView_android_drawableStart, TypedValue.TYPE_NULL)
                val style = Style.fromPosition(getInt(R.styleable.MessageBlockView_messageStyle, Style.getDefault().position))
                val descriptionId = getResourceId(R.styleable.MessageBlockView_description, TypedValue.TYPE_NULL)
                val signatureTextId = getResourceId(R.styleable.MessageBlockView_signatureText, TypedValue.TYPE_NULL)
                val signatureDrawable = getDrawable(R.styleable.MessageBlockView_signatureDrawable)
                recycle()

                if (titleId != ResourcesCompat.ID_NULL) setTitle(context.getString(titleId))
                setTitleDrawable(drawableStartId)
                setStyle(style)
                if (descriptionId != ResourcesCompat.ID_NULL) setDescription(context.getString(descriptionId))
                if (signatureTextId != ResourcesCompat.ID_NULL) setSignatureText(context.getString(signatureTextId))
                setSignatureDrawable(signatureDrawable)
            }
        }
    }

    private fun setupView() {
        if (isInEditMode.not()) messageBlockTitle.setTypeface(ResourcesCompat.getFont(context, R.font.gt_america_bold), Typeface.NORMAL)

        val paddingVert = resources.getDimensionPixelOffset(R.dimen.messageBlockPaddingVert)
        val paddingHorz = resources.getDimensionPixelOffset(R.dimen.messageBlockPaddingHorz)
        setPadding(paddingHorz, paddingVert, paddingHorz, paddingVert)
    }

    private inner class ColorConfiguration(
        @ColorRes val textColor: Int,
        @ColorRes val backgroundColor: Int,
        @ColorRes val strokeColor: Int
    )
}