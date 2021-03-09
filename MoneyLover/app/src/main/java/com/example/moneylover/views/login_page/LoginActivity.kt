package com.example.moneylover.views.login_page

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.moneylover.R

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy

class LoginActivity: AppCompatActivity(),TabLayout.OnTabSelectedListener {
    private var mViewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        setViewPagerAdapter()
    }

    private fun initViews() {
        mViewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
//        tabLayout.addOnTabSelectedListener(this)
    }

    private fun setViewPagerAdapter() {
        val fragmentAdapter1 = FragmentAdapter1(this)
        mViewPager!!.adapter = fragmentAdapter1
        TabLayoutMediator(tabLayout!!, mViewPager!!,
            TabConfigurationStrategy { tab, position ->
                Log.d(
                    "Dhanashree",
                    "onConfigureTab called"
                )
            }).attach()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        Log.d("Dhanashree", "onTabSelected")
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        Log.d("Dhanashree", "onTabUnselected")
    }


    override fun onTabReselected(tab: TabLayout.Tab?) {
        Log.d("Dhanashree", "onTabReselected")
    }
}