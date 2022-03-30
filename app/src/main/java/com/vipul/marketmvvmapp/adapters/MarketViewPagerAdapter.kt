package com.vipul.marketmvvmapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vipul.marketmvvmapp.utils.Utils.Companion.MARKET_LISTING_FRAGMENT
import com.vipul.marketmvvmapp.utils.Utils.Companion.MARKET_MAP_FRAGMENT
import com.vipul.marketmvvmapp.view.MarketListingFragment
import com.vipul.marketmvvmapp.view.MarketMapFragment

class MarketViewPagerAdapter(
    private val fragmentList: ArrayList<String>,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (fragmentList[position]) {
            MARKET_LISTING_FRAGMENT -> {
                MarketListingFragment()
            }
            MARKET_MAP_FRAGMENT -> {
                MarketMapFragment()
            }
            else -> {
                Fragment()
            }
        }
    }

}