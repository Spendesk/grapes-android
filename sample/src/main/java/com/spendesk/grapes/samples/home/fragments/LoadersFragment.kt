package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.samples.R
import kotlinx.android.synthetic.main.view_home_header.*

/**
 * @author Vivien Mahe
 * @since 28/10/2021
 */
class LoadersFragment : Fragment(R.layout.fragment_home_loaders) {

    companion object {
        fun newInstance() = LoadersFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeGenericHeaderTitle.text = context?.getString(R.string.loaders)
    }
}