package com.spendesk.grapes.samples.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.samples.databinding.ViewColorSampleCardviewBinding

/**
 * @author danyboucanova
 * @since 09/03/2022
 */
class ColorSampleCardView : CardView {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    private val binding = ViewColorSampleCardviewBinding.inflate(LayoutInflater.from(context), this)

    class Configuration(
        @DrawableRes val drawableRes: Int = ResourcesCompat.ID_NULL,
        val text: CharSequence? = null
    )

    fun updateConfiguration(configuration: Configuration) {
        binding.colorSampleCardText.text = configuration.text
        binding.colorSampleCardImage.setBackgroundResource(configuration.drawableRes)
    }
}