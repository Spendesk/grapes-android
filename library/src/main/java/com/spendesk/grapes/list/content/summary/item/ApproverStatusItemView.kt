package com.spendesk.grapes.list.content.summary.item

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.spendesk.grapes.messages.MessageInlineView
import com.spendesk.grapes.R
import com.spendesk.grapes.internal.libs.glide.loadFromUrl
import kotlinx.android.synthetic.main.item_approver_status.view.*

/**
 * @author danyboucanova
 * @since 1/19/21
 */
class ApproverStatusItemView : ConstraintLayout {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    class Configuration(
        val imageUrl: String? = null,
        @DrawableRes val placeholderResId: Int = 0,
        val title: CharSequence,
        val messageInlineStatusText: CharSequence,
        val messageInlineStatusStyle: MessageInlineView.Style
    )

    init {
        View.inflate(context, R.layout.item_approver_status, this)
    }

    fun updateConfiguration(configuration: Configuration) {
        itemApproverStatusText.text = configuration.title
        itemApproverStatusImage.loadFromUrl(url = configuration.imageUrl, errorResId = configuration.placeholderResId, shouldCircleCrop = true)

        itemApproverStatusMessageInline.setTitle(configuration.messageInlineStatusText)
        itemApproverStatusMessageInline.setStyle(configuration.messageInlineStatusStyle)
    }
}