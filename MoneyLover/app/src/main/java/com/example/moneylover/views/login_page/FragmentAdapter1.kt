package com.example.moneylover.views.login_page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter1(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return LoginFragment1.newInstance("Get stuff done with or without an internet connection")
            1 -> return LoginFragment2.newInstance("open edit and save word document")
            2 -> return LoginFragment3.newInstance("Write on your own invite more people to contribut")
            3 -> return LoginFragment4.newInstance("Write on your own invite more people to contribut")
        }
        return LoginFragment1.newInstance("This is Default Fragment")
    }

    override fun getItemCount(): Int {
        return 4
    }
}
