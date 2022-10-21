package com.example.design_1

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.qarapp.MainActivity
import com.example.qarapp.R

class OnBoardingFragment2 : Fragment() {
    lateinit var textView: TextView
    lateinit var textView4: TextView
    lateinit var textView5: TextView
    lateinit var nextText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v =inflater.inflate(R.layout.fragment_on_bording_2,container,false)
        textView = v.findViewById(R.id.textView)
        textView4 = v.findViewById(R.id.textView4)
        textView5 = v.findViewById(R.id.textView5)
        nextText = v.findViewById(R.id.NextText)

        val typeface = Typeface.createFromAsset(activity?.assets, "font/Roboto-Black.ttf")
        textView.typeface = typeface
        textView4.typeface = typeface
        textView5.typeface = typeface
        nextText.typeface = typeface

        nextText.setOnClickListener {
            startActivity(Intent(v.context, MainActivity::class.java))
        }
        return v
    }
}