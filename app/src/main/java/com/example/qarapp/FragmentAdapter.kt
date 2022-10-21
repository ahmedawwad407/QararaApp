package com.example.qarapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FragmentAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentStatePagerAdapter(fm) {


    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
         when (position) {
            0 -> return FragmentServices()
            1 -> return FragmentCompalints()
            2 -> return FragmentAboutMunicipality()
            3 -> return Fragment_Notification()
            else -> return null!!
        }
    }


}