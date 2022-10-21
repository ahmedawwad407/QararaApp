package com.example.qarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.design_1.OnBoardingFragment1
import com.example.design_1.OnBoardingFragment2
import com.example.design_1.OnBoardingFragment3
import pl.droidsonroids.gif.GifImageView

class SplashScreen : AppCompatActivity() {
    lateinit var anim: Animation
    lateinit var splashGif:GifImageView
    companion object{
        val NUM_PAGES = 3
    }
    lateinit var viewPager: ViewPager
    lateinit var pagerAdapter :ScreenSlidPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        viewPager = findViewById(R.id.LiquidPager)
        splashGif = findViewById(R.id.splashGif)


        pagerAdapter = ScreenSlidPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter


        anim = AnimationUtils.loadAnimation(applicationContext,R.anim.o_b_anim)
        viewPager.startAnimation(anim)

        splashGif.animate().translationY(3500F).setDuration(1000).setStartDelay(1500)

    }

    class ScreenSlidPagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {

        override fun getCount(): Int {
            return NUM_PAGES
        }

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return OnBoardingFragment1()
                1 -> return OnBoardingFragment2()
                2 -> return OnBoardingFragment3()
                else -> return null!!
            }
        }

    }
}