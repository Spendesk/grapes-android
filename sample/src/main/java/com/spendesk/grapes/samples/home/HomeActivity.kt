package com.spendesk.grapes.samples.home

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.extensions.disposedBy
import com.spendesk.grapes.samples.core.extensions.isDarkThemeOn
import com.spendesk.grapes.samples.databinding.ActivityHomeBinding
import com.spendesk.grapes.samples.entity.HomeTabItem
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * @author danyboucanova
 * @since 1/4/21
 */
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeActivityViewModel by viewModels()

    private val disposables = CompositeDisposable()
    private lateinit var homeAdapter: HomePagerAdapter
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.homeToolbar)

        setupView()
        bindViewModel()

        viewModel.onLifecycleStateChange(lifecycle.currentState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        with(menu?.findItem(R.id.menuHomeSwitchTheme)?.actionView as SwitchCompat) {
            isChecked = isDarkThemeOn()

            setOnCheckedChangeListener { buttonView, isChecked ->
                AppCompatDelegate.setDefaultNightMode(if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
                buttonView.isChecked = isChecked
            }
        }
        return true
    }

    private fun bindViewModel() {
        with(viewModel) {
            updateHomeTabItem().subscribe { updateHomeTabs(it) }.disposedBy(disposables)
        }
    }

    private fun setupView() {
        homeAdapter = HomePagerAdapter(this)
        with(binding.homeViewPager) {
            adapter = homeAdapter
            isUserInputEnabled = false
        }
    }

    private fun updateHomeTabs(expenseItemList: List<HomeTabItem>) {
        homeAdapter.updateList(expenseItemList)

        TabLayoutMediator(binding.homeTabLayout, binding.homeViewPager, true) { tab, position ->
            tab.text = homeAdapter.getTabText(position)
        }.attach()
    }
}