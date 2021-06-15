package com.spendesk.grapes.samples.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.spendesk.grapes.samples.entity.HomeTabItem
import com.spendesk.grapes.samples.home.fragments.ButtonsFragment
import com.spendesk.grapes.samples.home.fragments.CardsFragment
import com.spendesk.grapes.samples.home.fragments.InputsFragment
import com.spendesk.grapes.samples.home.fragments.SelectorFragment

/**
 * @author danyboucanova
 * @since 12/29/20
 */
class HomePagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val itemList: MutableList<HomeTabItem> = mutableListOf()

    override fun createFragment(position: Int): Fragment =
        when (val item = itemList[position]) {
            is HomeTabItem.Buttons -> ButtonsFragment.newInstance()
            is HomeTabItem.Cards -> CardsFragment.newInstance()
            is HomeTabItem.Selectors -> SelectorFragment.newInstance()
            is HomeTabItem.Inputs -> InputsFragment.newInstance()
            is HomeTabItem.Avatars,
            is HomeTabItem.Contents,
            is HomeTabItem.Lists,
            is HomeTabItem.Messages,
            is HomeTabItem.Modals -> throw IllegalStateException("Cannot resolve the item (name: ${item::class.java.simpleName}. This item is not yet resolved is not handled")
        }

    override fun getItemCount(): Int = itemList.size

    fun updateList(list: List<HomeTabItem>) {
        itemList.clear()
        itemList.addAll(list)

        notifyDataSetChanged()
    }

    fun getTabText(position: Int): String = itemList[position]::class.java.simpleName
}