package com.spendesk.grapes

import android.content.Context
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.bucket_deep_blue.view.*

/**
 * @author danyboucanova
 * @since 20/10/2020
 */
open class DeepBlueBucketView : BucketView {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    class Configuration(
        val title: CharSequence,
        val description: CharSequence,
        val buttonText: CharSequence
    )

    var onButtonClick: (() -> Unit)? = null

    init {
        View.inflate(context, R.layout.bucket_deep_blue, this)

        bindView()
    }

    fun updateData(configuration: Configuration) {
        deepBlueBucketTitle.text = configuration.title
        deepBlueBucketDescription.text = configuration.description
        deepBlueBucketButton.text = configuration.buttonText
    }

    fun updateTitle(title: CharSequence) {
        deepBlueBucketTitle.text = title
    }

    fun updateDescription(description: CharSequence) {
        deepBlueBucketDescription.text = description
    }

    fun updateButtonText(buttonText: CharSequence) {
        deepBlueBucketButton.text = buttonText
    }

    private fun bindView() {
        deepBlueBucketButton.setOnClickListener { onButtonClick?.invoke() }
    }
}