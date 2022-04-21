package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeFontsBinding

/**
 * @author : danyboucanova
 * @since : 13/04/2022, Wed
 **/
class FontsFragment : Fragment(R.layout.fragment_home_fonts) {

    companion object {
        fun newInstance() = FontsFragment()
    }

    private val binding by viewBinding(FragmentHomeFontsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.fonts)

        setupView()
    }

    private fun setupView() {

    }
}
