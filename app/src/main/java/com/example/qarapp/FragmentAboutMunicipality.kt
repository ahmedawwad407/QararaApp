package com.example.qarapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAboutMunicipality.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentAboutMunicipality : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var cardview01: CardView
    lateinit var cardview02: CardView
    lateinit var cardview03: CardView
    lateinit var cardview04: CardView
    lateinit var cardview05: CardView
    lateinit var cardview06: CardView
    lateinit var cardview07: CardView
    lateinit var cardview08: CardView
    lateinit var button1: ImageView
    lateinit var button2: ImageView
    lateinit var button3: ImageView
    lateinit var button4: ImageView
    var link1 = "https://www.facebook.com/Qarara.Mun"
    var link2 = "https://twitter.com/qararamun"
    var link3 = "https://www.youtube.com/channel/UCVvLZ9wejUJikgU4ZZjBGiw"
    var link4 = "https://www.qarara.ps/"
    var link5 = "https://www.qarara.ps/details16.html"
    var link6 = "https://www.qarara.ps/news11.html"
    var link7 = "https://www.qarara.ps/friendly27.html"
    var link8 = "https://www.qarara.ps/archive.html"
    var link9 = "https://www.qarara.ps/contacts.html"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_about_municipality, container, false)
        cardview01 = v.findViewById(R.id.cardview01)
        cardview02 = v.findViewById(R.id.cardview02)
        cardview03 = v.findViewById(R.id.cardview03)
        cardview04 = v.findViewById(R.id.cardview04)
        cardview05 = v.findViewById(R.id.cardview05)
        cardview06 = v.findViewById(R.id.cardview06)
        cardview07 = v.findViewById(R.id.cardview07)
        cardview08 = v.findViewById(R.id.cardview08)

        button1 = v.findViewById(R.id.button1)
        button2 = v.findViewById(R.id.button2)
        button3 = v.findViewById(R.id.button3)
        button4 = v.findViewById(R.id.button4)


        cardview01.setOnClickListener {
            val i = Intent(v.context, webview1::class.java)
            i.putExtra("link", link7)
            startActivity(i)
        }

        cardview02.setOnClickListener {
            val i = Intent(v.context, webview1::class.java)
            i.putExtra("link", link9)
            startActivity(i)

        }
        cardview03.setOnClickListener {
            val i = Intent(v.context, ImagesOfNews::class.java)
            startActivity(i)
        }

        cardview04.setOnClickListener {
            val i = Intent(v.context, VideoOffLoading::class.java)
            startActivity(i)
        }

        /*cardview05.setOnClickListener {
          val i = Intent(v.context, webview1::class.java)
          i.putExtra("link","")
          startActivity(i)
      }*/

        cardview06.setOnClickListener {
            val i = Intent(v.context, webview1::class.java)
            i.putExtra("link", link8)
            startActivity(i)
        }
        cardview07.setOnClickListener {
            val i = Intent(v.context, webview1::class.java)
            i.putExtra("link", link5)
            startActivity(i)
        }
        cardview08.setOnClickListener {
            val i = Intent(v.context, webview1::class.java)
            i.putExtra("link", link6)
            startActivity(i)
        }



        button1.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(link1))
            startActivity(i)
        }
        button2.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(link2))
            startActivity(i)
        }
        button3.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(link3))
            startActivity(i)
        }
        button4.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(link4))
            startActivity(i)
        }

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentAboutMunicipality.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentAboutMunicipality().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}