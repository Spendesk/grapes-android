package com.spendesk.grapes.internal.libs.glide

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import java.io.File

/**
 * Extension functions for [ImageView] class.
 *
 * @author Vivien Mahe
 * @since 29/01/2019
 */

/**
 * Loads an url into this [ImageView]. Display the [errorResId] as a placeholder if we couldn't load the url. [circleCrop] loads the image as a circle and adds a border to it.
 *
 * @param url URL of the image to display into this [ImageView]
 * @param errorResId placeholder resource ID
 * @param circleCrop loads the image as a circle and adds a border (Pair of borderwidth and border color)
 */

fun ImageView.loadFromUrl(
    url: String?,
    errorResId: Int? = 0,
    circleCrop: Pair<Int, Int>? = null,
    roundedCorners: Int = 0,
    thumbnailSizeMultiplier: Float = 0f,
    size: Pair<Int, Int>? = null,
    errorConsumer: (() -> Unit)? = null
) =
    loadRequest(GlideApp.with(context).load(url), errorResId, circleCrop, roundedCorners, thumbnailSizeMultiplier, size, errorConsumer)

fun ImageView.loadFromUrl(
    url: String?,
    errorResId: Int? = 0,
    shouldCircleCrop: Boolean,
    roundedCorners: Int = 0,
    thumbnailSizeMultiplier: Float = 0f,
    size: Pair<Int, Int>? = null,
    errorConsumer: (() -> Unit)? = null
) =
    loadRequest(GlideApp.with(context).load(url), errorResId, shouldCircleCrop, roundedCorners, thumbnailSizeMultiplier, size, errorConsumer)

fun ImageView.loadFromUrl(
    url: GlideUrl?,
    errorResId: Int? = 0,
    circleCrop: Pair<Int, Int>? = null,
    roundedCorners: Int = 0,
    thumbnailSizeMultiplier: Float = 0f,
    size: Pair<Int, Int>? = null,
    errorConsumer: (() -> Unit)? = null
) =
    loadRequest(GlideApp.with(context).load(url), errorResId, circleCrop, roundedCorners, thumbnailSizeMultiplier, size, errorConsumer)

fun ImageView.loadFromFile(
    file: File?,
    errorResId: Int? = 0,
    circleCrop: Pair<Int, Int>? = null,
    roundedCorners: Int = 0,
    thumbnailSizeMultiplier: Float = 0f,
    size: Pair<Int, Int>? = null,
    errorConsumer: (() -> Unit)? = null
) =
    loadRequest(GlideApp.with(context).asDrawable().load(file), errorResId, circleCrop, roundedCorners, thumbnailSizeMultiplier, size, errorConsumer)

private fun ImageView.loadRequest(
    request: GlideRequest<Drawable>,
    errorResId: Int? = 0,
    shouldCircleCrop: Boolean = false,
    roundedCorners: Int = 0,
    thumbnailSizeMultiplier: Float = 0f,
    size: Pair<Int, Int>? = null,
    errorConsumer: (() -> Unit)? = null
) = loadRequest(
    request = request,
    errorResId = errorResId,
    shouldCircleCrop = shouldCircleCrop,
    circleCrop = null,
    roundedCorners = roundedCorners,
    thumbnailSizeMultiplier = thumbnailSizeMultiplier,
    errorConsumer = errorConsumer
)

private fun ImageView.loadRequest(
    request: GlideRequest<Drawable>,
    errorResId: Int? = 0,
    circleCrop: Pair<Int, Int>? = null,
    roundedCorners: Int = 0,
    thumbnailSizeMultiplier: Float = 0f,
    size: Pair<Int, Int>? = null,
    errorConsumer: (() -> Unit)? = null
) = loadRequest(
    request = request,
    errorResId = errorResId,
    shouldCircleCrop = false,
    circleCrop = circleCrop,
    roundedCorners = roundedCorners,
    thumbnailSizeMultiplier = thumbnailSizeMultiplier,
    size = size,
    errorConsumer = errorConsumer
)

private fun ImageView.loadRequest(
    request: GlideRequest<Drawable>,
    errorResId: Int? = 0,
    shouldCircleCrop: Boolean = false,
    circleCrop: Pair<Int, Int>? = null,
    roundedCorners: Int = 0,
    thumbnailSizeMultiplier: Float = 0f,
    size: Pair<Int, Int>? = null,
    errorConsumer: (() -> Unit)? = null
) =
    request
        .apply {
            size?.let { override(it.first, it.second) }

            // Apply the size multiplier if we are loading a thumbnail
            if (thumbnailSizeMultiplier > 0f) thumbnail(thumbnailSizeMultiplier)

            // Set an error drawable if set
            if (errorResId != 0) error(errorResId!!)

            // Apply a default circle crop transformation if set
            if (shouldCircleCrop) apply(RequestOptions().circleCrop())

            // Apply a custom circle crop transformation if set, only if we didn't apply a default crop first
            if (shouldCircleCrop.not()) circleCrop?.let { apply(RequestOptions().transform(CircleCropBorderTransformation(it.first, it.second))) }

            // Apply rounded corners if requested
            if (roundedCorners > 0) apply(RequestOptions().transform(CenterCrop(), RoundedCorners(roundedCorners)))

            // Set the transition with crossfade
            transition(DrawableTransitionOptions.withCrossFade())
        }
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                errorConsumer?.let { it() }
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean = false
        })
        .into(this)
