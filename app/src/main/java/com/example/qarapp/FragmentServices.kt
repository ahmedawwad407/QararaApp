package com.example.qarapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView


class FragmentServices : Fragment() {
    var link6 = "https://www.qarara.ps/news11.html"
    var link8 = "https://www.qarara.ps/archive.html"
    lateinit var cardview03: CardView
    lateinit var cardview04: CardView
    lateinit var cardview05: CardView
    lateinit var cardview06: CardView
    lateinit var cardview08: CardView
    lateinit var cardview09: CardView
    lateinit var cardview000: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_services, container, false)
        cardview03 = v.findViewById(R.id.cardview03)
        cardview04 = v.findViewById(R.id.cardview04)
        cardview05 = v.findViewById(R.id.cardview05)
        cardview06 = v.findViewById(R.id.cardview06)
        cardview08 = v.findViewById(R.id.cardview08)
        cardview09 = v.findViewById(R.id.cardview09)
        cardview000 = v.findViewById(R.id.cardview000)

        cardview03.setOnClickListener {
            val i = Intent(v.context, ListMyBillScreen::class.java)
//            i.putExtra("name", "Gc0bng4QmwVe2kVNmUzZYXvd7OO2")
            startActivity(i)
        }

        cardview000.setOnClickListener {
            val i = Intent(v.context, Annual_Reports::class.java)
            startActivity(i)
        }

        cardview09.setOnClickListener {
            val i = Intent(v.context, com.example.qarapp.ChatActivity::class.java)
//            i.putExtra("userId","Gc0bng4QmwVe2kVNmUzZYXvd7OO2")
            startActivity(i)
        }

        cardview04.setOnClickListener {
            val i = Intent(v.context, webview1::class.java)
            i.putExtra("link", "https://www.qarara.ps/news67.html")
            startActivity(i)

        }

        cardview05.setOnClickListener {
            val i = Intent(v.context, webview1::class.java)
            i.putExtra("link", "https://www.qarara.ps/news68.html")
            startActivity(i)

        }

        cardview06.setOnClickListener {
            val i = Intent(v.context, webview1::class.java)
            i.putExtra("link", link8)
            startActivity(i)
        }

        cardview08.setOnClickListener {
            val i = Intent(v.context, webview1::class.java)
            i.putExtra("link", link6)
            startActivity(i)
        }
        return v
    }
}