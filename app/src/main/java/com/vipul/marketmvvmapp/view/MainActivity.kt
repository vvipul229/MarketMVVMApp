package com.vipul.marketmvvmapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.vipul.marketmvvmapp.R
import com.vipul.marketmvvmapp.adapters.MarketViewPagerAdapter
import com.vipul.marketmvvmapp.utils.Utils.Companion.MARKET_LISTING_FRAGMENT
import com.vipul.marketmvvmapp.utils.Utils.Companion.MARKET_MAP_FRAGMENT
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var fragmentList: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
         fragmentList = ArrayList()
        setUpViewPager()
        setUpTabLayout()
    }

    private fun setUpTabLayout() {
        TabLayoutMediator(marketTabLayout, marketViewPager) { tab, position ->
            tab.text =fragmentList[position]
        }.attach()
    }

    private fun setUpViewPager() {
        fragmentList.add(MARKET_LISTING_FRAGMENT)
        fragmentList.add(MARKET_MAP_FRAGMENT)

        marketViewPager.adapter = MarketViewPagerAdapter(fragmentList, this)
    }
}