package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.samples.R
import kotlinx.android.synthetic.main.view_home_header.*

/**
 * @author danyboucanova
 * @since 1/6/21
 */
/**
 * @author danyboucanova
 * @since 12/29/20
 */
class ButtonsFragment : Fragment(R.layout.fragment_home_buttons) {

    companion object {
        fun newInstance() = ButtonsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeGenericHeaderTitle.text = context?.getString(R.string.buttons)
    }
}