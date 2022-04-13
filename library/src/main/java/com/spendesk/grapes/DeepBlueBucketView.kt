package com.spendesk.grapes

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.spendesk.grapes.databinding.BucketDeepBlueBinding

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

    data class Configuration(
        val title: CharSequence,
        val description: CharSequence,
        val buttonText: CharSequence
    )

    var onButtonClick: (() -> Unit)? = null

    private val binding: BucketDeepBlueBinding = BucketDeepBlueBinding.inflate(LayoutInflater.from(context), this)

    init {
        bindView()
    }

    fun updateConfiguration(configuration: Configuration) {
        updateTitle(title = configuration.title)
        updateDescription(description = configuration.description)
        updateButtonText(buttonText = configuration.buttonText)
    }

    fun updateTitle(title: CharSequence) {
        binding.deepBlueBucketTitle.text = title
    }

    fun updateDescription(description: CharSequence) {
        binding.deepBlueBucketDescription.text = description
    }

    fun updateButtonText(buttonText: CharSequence) {
        binding.deepBlueBucketButton.setText(buttonText)
    }

    private fun bindView() {
        binding.deepBlueBucketButton.setOnClickListener { onButtonClick?.invoke() }
    }
}