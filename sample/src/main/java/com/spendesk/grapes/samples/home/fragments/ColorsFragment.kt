package com.spendesk.grapes.samples.home.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.extensions.disposedBy
import com.spendesk.grapes.samples.core.internal.viewBinding
import com.spendesk.grapes.samples.databinding.FragmentHomeColorsBinding
import com.spendesk.grapes.samples.home.fragments.list.ColorsAdapter
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * @author danyboucanova
 * @since 09/03/2022
 */
@AndroidEntryPoint
class ColorsFragment : Fragment(R.layout.fragment_home_colors) {

    companion object {
        fun newInstance() = ColorsFragment()
    }

    private val viewModel: ColorsViewModel by viewModels()
    private val binding by viewBinding(FragmentHomeColorsBinding::bind)

    private val disposables = CompositeDisposable()

    private lateinit var adapter: ColorsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeHeader.homeGenericHeaderTitle.text = context?.getString(R.string.colors)

        setupView()
        bindViewModel()

        viewModel.onLifecycleStateChange(lifecycle.currentState)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewModel.onLifecycleStateChange(lifecycle.currentState)
        disposables.clear()
    }

    private fun setupView() {
        adapter = ColorsAdapter()
        binding.colorsList.adapter = adapter
    }

    private fun bindViewModel() {
        with(viewModel) {
            updateColorsItem().subscribe { adapter.updateList(it) }.disposedBy(disposables)
        }
    }
}