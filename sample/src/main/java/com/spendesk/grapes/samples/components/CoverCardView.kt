package com.spendesk.grapes.samples.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import com.spendesk.grapes.samples.databinding.ViewCoverCardviewBinding

/**
 * @author danyboucanova
 * @since 09/03/2022
 */
class CoverCardView : CardView {

    //region constructors

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    //endregion constructors

    private val binding = ViewCoverCardviewBinding.inflate(LayoutInflater.from(context), this)

    class Configuration(
        @DrawableRes val drawableRes: Int = ResourcesCompat.ID_NULL,
        val text: CharSequence? = null
    )

    fun updateConfiguration(configuration: Configuration) {
        binding.coverCardText.text = configuration.text
        binding.coverCardImage.setBackgroundResource(configuration.drawableRes)
    }
}