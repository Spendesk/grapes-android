package com.spendesk.grapes

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView

/**
 * Implementation of the Bucket CardView component.
 *
 * @author danyboucanova
 * @since 19/10/2020
 */
open class BucketView : CardView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)
    //endregion constructors

    init {
        setupView()
    }

    private fun setupView() {
        radius = resources.getDimension(R.dimen.bucketCardRadius)
        cardElevation = resources.getDimension(R.dimen.bucketCardElevation)
    }
}