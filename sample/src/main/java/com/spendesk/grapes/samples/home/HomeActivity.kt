package com.spendesk.grapes.samples.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.airbnb.android.showkase.models.Showkase
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.tabs.TabLayoutMediator
import com.spendesk.grapes.samples.R
import com.spendesk.grapes.samples.core.extensions.collectOnCreated
import com.spendesk.grapes.samples.core.extensions.isDarkThemeOn
import com.spendesk.grapes.samples.databinding.ActivityHomeBinding
import com.spendesk.grapes.samples.model.HomeTabItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @author danyboucanova
 * @since 1/4/21
 */
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    companion object {
        const val MAX_FRAGMENTS_RETAINED_EITHER_SIDE_CURRENT_PAGE = 1
    }

    private val viewModel: HomeViewModel by viewModels()

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        with(menu.findItem(R.id.menuHomeSwitchTheme)?.actionView as SwitchMaterial) {
            isChecked = isDarkThemeOn()

            setOnCheckedChangeListener { buttonView, isChecked ->
                AppCompatDelegate.setDefaultNightMode(if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
                buttonView.isChecked = isChecked
            }
        }

        with(menu.findItem(R.id.menuHomeShowCompose)) {
            setOnMenuItemClickListener {
                startActivity(Showkase.getBrowserIntent(this@HomeActivity))
                true
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.onLifecycleStateChange(lifecycle.currentState)
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            viewModel.updateHomeTabItem.collectOnCreated { updateHomeTabs(it) }
        }
    }

    @SuppressLint("WrongConstant")
    private fun setupView() {
        homeAdapter = HomePagerAdapter(this)

        with(binding.homeViewPager) {
            offscreenPageLimit = MAX_FRAGMENTS_RETAINED_EITHER_SIDE_CURRENT_PAGE
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
