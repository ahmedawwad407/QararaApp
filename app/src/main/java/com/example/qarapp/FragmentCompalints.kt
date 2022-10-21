package com.example.qarapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView

class FragmentCompalints : Fragment() {

    val link = "https://www.qarara.ps/complaints.html"
    lateinit var cardview02: CardView
    lateinit var cardview09: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_compalints, container, false)
        cardview02 = v.findViewById(R.id.cardview02)
        cardview09 = v.findViewById(R.id.cardview09)
        cardview02.setOnClickListener {
            startActivity(Intent(v.context, ComplaintsScreen::class.java))
        }
        cardview09.setOnClickListener {
            startActivity(Intent(v.context, ChatActivityCompalints::class.java))
        }
        return v
    }
}