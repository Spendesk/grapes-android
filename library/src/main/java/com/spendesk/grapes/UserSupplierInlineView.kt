package com.spendesk.grapes

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.extensions.visibleOrGone
import com.spendesk.grapes.extensions.visibleWithTextOrGone
import com.spendesk.grapes.internal.libs.glide.loadFromUrl
import kotlinx.android.synthetic.main.user_supplier_inline_view.view.*

/**
 * @author Vivien Mahe
 * @since 19/01/2021
 */
class UserSupplierInlineView : ConstraintLayout {

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

    data class Configuration(
        val title: CharSequence,
        val subtitle: CharSequence? = null,
        val bigImageUrl: String? = null,
        @DrawableRes val bigImagePlaceholderResId: Int? = null,
        val shouldCircleCropBigImage: Boolean,
        val shouldShowSmallImage: Boolean,
        val smallImageUrl: String? = null,
        @DrawableRes val smallImagePlaceholderResId: Int? = null,
        val shouldCircleCropSmallImage: Boolean
    )

    private val bigImageRoundedCornerRadius = resources.getDimensionPixelOffset(R.dimen.userSupplierInlineBigImageCornerRadius)
    private val smallImageRoundedCornerRadius = resources.getDimensionPixelOffset(R.dimen.userSupplierInlineSmallImageCornerRadius)

    init {
        View.inflate(context, R.layout.user_supplier_inline_view, this)
    }

    fun updateConfiguration(configuration: Configuration) {
        setTitle(configuration.title)
        setSubtitle(configuration.subtitle)
        setBigImage(url = configuration.bigImageUrl, placeholderResId = configuration.bigImagePlaceholderResId, shouldCircleCrop = configuration.shouldCircleCropBigImage)
        shouldShowSmallImage(configuration.shouldShowSmallImage)
        setSmallImage(url = configuration.smallImageUrl, placeholderResId = configuration.smallImagePlaceholderResId, shouldCircleCrop = configuration.shouldCircleCropSmallImage)
    }

    fun setTitle(title: CharSequence) {
        userSupplierInlineTitleText.text = title
    }

    fun setSubtitle(subtitle: CharSequence?) {
        userSupplierInlineSubtitleText.visibleWithTextOrGone(subtitle)
    }

    fun setBigImage(url: String?, placeholderResId: Int? = 0, shouldCircleCrop: Boolean) {
        val cornerRadius = if (shouldCircleCrop) 0 else bigImageRoundedCornerRadius
        userSupplierInlineBigImage.loadFromUrl(url = url, errorResId = placeholderResId, shouldCircleCrop = shouldCircleCrop, roundedCorners = cornerRadius)
    }

    fun setBigImageDrawable(drawable: Drawable?) {
        userSupplierInlineBigImage.setImageDrawable(drawable)
    }

    fun shouldShowSmallImage(shouldShowImage: Boolean) {
        userSupplierInlineSmallImage.visibleOrGone(shouldShowImage)
    }

    fun setSmallImage(url: String?, placeholderResId: Int? = 0, shouldCircleCrop: Boolean) {
        val cornerRadius = if (shouldCircleCrop) 0 else smallImageRoundedCornerRadius
        userSupplierInlineSmallImage.loadFromUrl(url = url, errorResId = placeholderResId, shouldCircleCrop = shouldCircleCrop, roundedCorners = cornerRadius)
    }

    fun setSmallImageDrawable(drawable: Drawable?) {
        userSupplierInlineSmallImage.setImageDrawable(drawable)
    }

    private fun initView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.UserSupplierInlineView)) {
                val titleId = getResourceId(R.styleable.UserSupplierInlineView_title, TypedValue.TYPE_NULL)
                val subtitleId = getResourceId(R.styleable.UserSupplierInlineView_subtitle, TypedValue.TYPE_NULL)
                val bigImageDrawable = getDrawable(R.styleable.UserSupplierInlineView_bigImageDrawable)
                val shouldShowSmallImage = getBoolean(R.styleable.UserSupplierInlineView_shouldShowSmallImage, false)
                val smallImageDrawable = getDrawable(R.styleable.UserSupplierInlineView_smallImageDrawable)
                recycle()

                if (titleId != ResourcesCompat.ID_NULL) setTitle(context.getString(titleId))
                if (subtitleId != ResourcesCompat.ID_NULL) setSubtitle(context.getString(subtitleId))
                setBigImageDrawable(bigImageDrawable)
                shouldShowSmallImage(shouldShowSmallImage)
                setSmallImageDrawable(smallImageDrawable)
            }
        }
    }
}