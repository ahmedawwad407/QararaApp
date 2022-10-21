package com.example.qarapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.qarapp.Admin.SignUp_User

class LoginAdapter(fm:FragmentManager,private var tabCount:Int,private var context: Context)
    : FragmentPagerAdapter(fm) {



    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position:Int):Fragment{
        when (position) {
            0 -> return LoginTapFragment()
            1 -> return SignUp_User()
            else -> return null!!
        }
    }
}