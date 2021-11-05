package com.spendesk.grapes.loader

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.spendesk.grapes.R
import com.spendesk.grapes.databinding.FragmentBottomSheetSearchableBinding
import com.spendesk.grapes.databinding.LoaderCircularProgressbarBinding
import java.util.*
import kotlin.math.roundToInt

/**
 * @author Vivien Mahe
 * @since 28/10/2021
 */
class CircularProgressBar : ConstraintLayout {

    constructor(context: Context) : super(context) {
        setupView(null)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        setupView(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr) {
        setupView(attributeSet)
    }

    enum class Size(val position: Int) {
        NORMAL(0),
        SMALL(1);

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

    private var binding = LoaderCircularProgressbarBinding.inflate(LayoutInflater.from(context), this, true)

    private fun setupView(attributeSet: AttributeSet?) {
        attributeSet?.let {
            with(context.obtainStyledAttributes(it, R.styleable.CircularProgressBar)) {
                val size = Size.fromPosition(getInt(R.styleable.CircularProgressBar_grapesProgressBarSize, Size.getDefault().position))
                recycle()

                setSize(size)
            }
        }
    }

    private fun setSize(size: Size) {
        when (size) {
            Size.NORMAL -> R.dimen.circularProgressBarNormalSize
            Size.SMALL -> R.dimen.circularProgressBarSmallSize
        }.let { sizeResId ->
            val dimen = resources.getDimension(sizeResId).roundToInt()

            with(binding.circularProgressBarMain) {
                minWidth = dimen
                minHeight = dimen
                maxWidth = dimen
                maxHeight = dimen
            }

            with(binding.circularProgressBarSecondary) {
                minWidth = dimen
                minHeight = dimen
                maxWidth = dimen
                maxHeight = dimen
            }
        }
    }
}