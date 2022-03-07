package com.spendesk.grapes.samples.core.internal

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.spendesk.grapes.samples.R
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Delegates a viewBinding read-only property storing it inside the bind view's tag.
 *
 * usage:
 * ```
 *  class ExampleFragment : Fragment {
 *      //...
 *      private val binding: ExampleFragmentBinding by viewBinding(ExampleFragmentBinding::bind)
 *      // ...
 *  }
 * ```
 *
 * @param ViewBindingT the [ViewBinding] property type.
 * @param bind You need to call [ViewBindingT].bind static method to bind the view binding
 *  to fragment's view.
 * @return a [ViewBindingT] delegates
 *
 */
fun <ViewBindingT : ViewBinding> viewBinding(
    bind: (View) -> ViewBindingT
): ReadOnlyProperty<Fragment, ViewBindingT> {
    return FragmentViewBindingDelegate(bind)
}

private class FragmentViewBindingDelegate<out ViewBindingT : ViewBinding>(
    private val bind: (View) -> ViewBindingT
) : ReadOnlyProperty<Fragment, ViewBindingT> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): ViewBindingT {
        return thisRef.requireView().getOrPutBinding(R.id.view_binding_tag)
    }

    @Suppress("UNCHECKED_CAST")
    private fun View.getOrPutBinding(key: Int): ViewBindingT {
        val binding = getTag(key) as? ViewBindingT
        if (binding == null) {
            val newBinding = bind(this)
            setTag(key, newBinding)
            return newBinding
        }
        return binding
    }
}