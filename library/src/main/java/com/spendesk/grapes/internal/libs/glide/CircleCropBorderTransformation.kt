package com.spendesk.grapes.internal.libs.glide

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import java.security.MessageDigest

/**
 * Applies this transformation to an Image to get a rounded image with a border.
 * https://android--examples.blogspot.fr/2015/11/android-circular-bitmap-with-border-and.html
 *
 * @author Vivien Mahe
 * @since 28/01/2019
 */
class CircleCropBorderTransformation(
    private val borderWidth: Int,
    private val borderColor: Int
) : BitmapTransformation() {

    companion object {
        private const val VERSION = 1 // The version of this transformation, incremented to correct an error in a previous version.
        private const val ID: String = "com.bumptech.glide.load.resource.bitmap.CircleCrop.$VERSION"
        private val ID_BYTES: ByteArray = ID.toByteArray(Key.CHARSET)
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) = messageDigest.update(ID_BYTES)

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val circle = TransformationUtils.circleCrop(pool, toTransform, outWidth, outHeight)
        return addBorderToCircularBitmap(circle, borderWidth, borderColor)
    }

    override fun equals(other: Any?) = other is CircleCropBorderTransformation

    override fun hashCode() = ID.hashCode()

    /**
     * Custom method to add a border around circular bitmap.
     */
    private fun addBorderToCircularBitmap(srcBitmap: Bitmap, borderWidth: Int, borderColor: Int): Bitmap {
        // Calculate the circular bitmap width with border
        val dstBitmapWidth = srcBitmap.width + borderWidth * 2

        val dstBitmap = Bitmap.createBitmap(dstBitmapWidth, dstBitmapWidth, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(dstBitmap)
        canvas.drawBitmap(srcBitmap, borderWidth.toFloat(), borderWidth.toFloat(), null)

        val paint = Paint()
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderWidth.toFloat()
        paint.isAntiAlias = true

        // Draw the circular border around circular bitmap
        canvas.drawCircle(
            (canvas.width / 2).toFloat(), // cx
            (canvas.width / 2).toFloat(),
            (canvas.width / 2 - borderWidth / 2).toFloat(), // radius
            paint
        )

        // Free the native object associated with this bitmap
        srcBitmap.recycle()

        // Return the bordered circular bitmap
        return dstBitmap
    }
}