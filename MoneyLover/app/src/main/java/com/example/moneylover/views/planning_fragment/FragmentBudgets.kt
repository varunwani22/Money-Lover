package com.example.moneylover.views.planning_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moneylover.R
import com.example.moneylover.views.report_fragment.FragmentDayBeforeYesterday
import com.example.moneylover.views.report_fragment.FragmentFuture
import com.example.moneylover.views.report_fragment.FragmentToday
import com.example.moneylover.views.report_fragment.FragmentYesterday
import kotlinx.android.synthetic.main.fragment_budgets.*
import kotlinx.android.synthetic.main.fragment_report.*

class FragmentBudgets : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_budgets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MyViewPagerAdapter(fragmentManager!!)
        adapter.addFragment(FragmentRunning(), "Running")
        adapter.addFragment(FragmentFinished(), "Finished")

        VpViewPager.adapter = adapter
        tbTabLayout.setupWithViewPager(VpViewPager)

    }

    class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }
}

