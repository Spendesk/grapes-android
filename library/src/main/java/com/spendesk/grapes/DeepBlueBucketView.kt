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

    fun updateData(configuration: Configuration) {
        with(binding) {
            deepBlueBucketTitle.text = configuration.title
            deepBlueBucketDescription.text = configuration.description
            deepBlueBucketButton.text = configuration.buttonText
        }
    }

    fun updateTitle(title: CharSequence) {
        binding.deepBlueBucketTitle.text = title
    }

    fun updateDescription(description: CharSequence) {
        binding.deepBlueBucketDescription.text = description
    }

    fun updateButtonText(buttonText: CharSequence) {
        binding.deepBlueBucketButton.text = buttonText
    }

    private fun bindView() {
        binding.deepBlueBucketButton.setOnClickListener { onButtonClick?.invoke() }
    }
}