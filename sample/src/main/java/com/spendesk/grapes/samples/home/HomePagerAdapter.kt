package com.spendesk.grapes.samples.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.spendesk.grapes.samples.home.fragments.*
import com.spendesk.grapes.samples.model.HomeTabItem

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
            is HomeTabItem.Colors -> ColorsFragment.newInstance()
            is HomeTabItem.Fonts -> FontsFragment.newInstance()
            is HomeTabItem.Buttons -> ButtonsFragment.newInstance()
            is HomeTabItem.Compose -> ComposeFragment.newInstance()
            is HomeTabItem.Cards -> CardsFragment.newInstance()
            is HomeTabItem.Selectors -> SelectorFragment.newInstance()
            is HomeTabItem.Inputs -> InputsFragment.newInstance()
            is HomeTabItem.Lists -> ListsFragment.newInstance()
            is HomeTabItem.Contents -> ContentsFragment.newInstance()
            is HomeTabItem.BottomSheets -> BottomSheetsFragment.newInstance()
            is HomeTabItem.Loaders -> LoadersFragment.newInstance()
            is HomeTabItem.Keyboards -> KeyboardsFragment.newInstance()
            is HomeTabItem.Messages -> MessagesFragment.newInstance()
            is HomeTabItem.Avatars,
            is HomeTabItem.Modals -> throw IllegalStateException("Cannot resolve the item (name: ${item::class.java.simpleName}. This item is not yet resolved is not handled")
        }

    override fun getItemCount(): Int = itemList.size

    fun updateList(list: List<HomeTabItem>) {
        itemList.clear()
        itemList.addAll(list)

        notifyDataSetChanged()
    }

    fun getTabText(position: Int): String =
        itemList[position]::class.java.simpleName
}