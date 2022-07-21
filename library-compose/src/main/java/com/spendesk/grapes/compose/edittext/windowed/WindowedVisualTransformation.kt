package com.spendesk.grapes.compose.edittext.windowed

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * @author Kélian CLERC
 * @since 21/07/2022
 */
class WindowedVisualTransformation(
    val windowLength: Int,
    val originalStringMaxLength: Int,
    val separation: AnnotatedString = AnnotatedString("-"),
    val hintAnnotatedString: AnnotatedString = AnnotatedString(" "),
) : VisualTransformation {
    private val windowOffsetMapping = WindowedOffsetMapping(
        originalStringMaxLength = originalStringMaxLength,
        windowLength = windowLength,
        windowSeparatorLength = separation.length
    )

    override fun filter(text: AnnotatedString): TransformedText {
        val paddedString = if (text.length >= originalStringMaxLength) {
            windowOffsetMapping.currentLength = originalStringMaxLength
            text.subSequence(0, originalStringMaxLength)
        } else {
            windowOffsetMapping.currentLength = text.length

            var paddedAnnotatedString = text
            repeat(originalStringMaxLength - text.length) {
                paddedAnnotatedString = paddedAnnotatedString.plus(hintAnnotatedString)
            }
            paddedAnnotatedString
        }

        val result = paddedString
            .windowed(windowLength, windowLength)
            .join(separation)

        return TransformedText(result, windowOffsetMapping)
    }

    private fun AnnotatedString.windowed(size: Int, step: Int): List<AnnotatedString> {
        val thisSize = this.length
        val resultCapacity = thisSize / step + if (thisSize % step == 0) 0 else 1
        val result = ArrayList<AnnotatedString>(resultCapacity)
        var index = 0
        while (index in 0 until thisSize) {
            val end = index + size
            result.add(this.subSequence(index, end))
            index += step
        }
        return result
    }

    private fun List<AnnotatedString>.join(separationString: AnnotatedString? = null): AnnotatedString {
        var result = if (this.isEmpty()) AnnotatedString("") else this.first()
        for (index in 1 until this.size) {
            separationString?.let { result = result.plus(separationString) }
            result = result.plus(this[index])
        }
        return result
    }
}
