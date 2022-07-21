package com.spendesk.grapes.compose.edittext.windowed

import androidx.compose.ui.text.input.OffsetMapping
import java.lang.Integer.min

/**
 * @author Kélian CLERC
 * @since 21/07/2022
 */
class WindowedOffsetMapping(
    private val originalStringMaxLength: Int,
    private val windowLength: Int,
    private val windowSeparatorLength: Int,
) : OffsetMapping {

    var currentLength: Int = 0

    override fun originalToTransformed(offset: Int): Int {
        val numberOfWindowSeparator = offset / windowLength
        val separatorExtraOffset = if (offset == originalStringMaxLength) {
            // Separators are only in between windows. If offset corresponds to the last char of original string, do not consider last separator
            (numberOfWindowSeparator - 1) * windowSeparatorLength
        } else {
            numberOfWindowSeparator * windowSeparatorLength
        }

        // Transformed offset corresponds to original offset plus offset due to separators
        return separatorExtraOffset + offset
    }

    override fun transformedToOriginal(offset: Int): Int {
        val numberOfWindowSeparator = offset / (windowLength + windowSeparatorLength)
        val separatorExtraOffset = numberOfWindowSeparator * windowSeparatorLength

        // Prevents considering padding chars in offset calculation
        return min(offset - separatorExtraOffset, currentLength)
    }
}
