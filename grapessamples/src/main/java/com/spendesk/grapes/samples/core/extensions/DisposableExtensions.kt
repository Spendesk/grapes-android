package com.spendesk.grapes.samples.core.extensions

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

/**
 * @author danyboucanova
 * @since 1/4/21
 */

/**
 * Adds this Disposable to the given container [CompositeDisposable] or disposes it if the container has been disposed.
 */
fun Disposable.disposedBy(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)

/**
 * Removes and disposes this Disposable from the given container [CompositeDisposable] if it is part of this container.
 */
fun Disposable.removeFrom(compositeDisposable: CompositeDisposable) = compositeDisposable.remove(this)
