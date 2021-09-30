package com.example.qarapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class LoginAdapter(fm:FragmentManager,private var tabCount:Int,private var context: Context) : FragmentPagerAdapter(fm) {



    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position:Int):Fragment{
        when (position) {
            0 -> return LoginTapFragment()
            1 -> return RegisterTapFragment()
            else -> return null!!
        }
    }
}