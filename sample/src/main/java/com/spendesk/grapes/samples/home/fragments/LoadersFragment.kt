package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeLoadersBinding

/**
 * @author Vivien Mahe
 * @since 28/10/2021
 */
class LoadersFragment : Fragment(R.layout.fragment_home_loaders) {

    companion object {
        fun newInstance() = LoadersFragment()
    }

    private val binding by viewBinding(FragmentHomeLoadersBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.loaders)
    }
}