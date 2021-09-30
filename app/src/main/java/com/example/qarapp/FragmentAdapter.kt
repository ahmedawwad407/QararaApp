package com.example.qarapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentStatePagerAdapter(fm) {


    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FragmentServices()
            1 -> FragmentCompalints()
            2 -> FragmentAboutMunicipality()
            else -> null!!
        }
    }


}