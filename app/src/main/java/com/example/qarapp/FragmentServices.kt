package com.example.qarapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentServices.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentServices : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    

    lateinit var cardview01:CardView
    lateinit var cardview02:CardView
    lateinit var cardview03:CardView
    lateinit var cardview04:CardView
    lateinit var cardview05:CardView
    lateinit var cardview06:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_services, container, false)
        cardview01 =v.findViewById(R.id.cardview01)
        cardview02 =v.findViewById(R.id.cardview02)
        cardview03 =v.findViewById(R.id.cardview03)
        cardview04 =v.findViewById(R.id.cardview04)
        cardview05 =v.findViewById(R.id.cardview05)
        cardview06 =v.findViewById(R.id.cardview06)


        cardview01.setOnClickListener {
            val i = Intent(v.context, TransactionsScreen::class.java)
            startActivity(i)
        }

        cardview02.setOnClickListener {
            val i = Intent(v.context, CounterReadScreen::class.java)
            startActivity(i)
        }

        cardview03.setOnClickListener {
            val i = Intent(v.context, MyBillScreen::class.java)
            startActivity(i)
        }


        cardview04.setOnClickListener {
            val i = Intent(v.context, webview1::class.java)
            i.putExtra("link","https://www.qarara.ps/news67.html")
            startActivity(i)

        }

        cardview05.setOnClickListener {

            val i = Intent(v.context, webview1::class.java)
            i.putExtra("link","https://www.qarara.ps/news68.html")
            startActivity(i)

        }

        cardview06.setOnClickListener {
//            val i = Intent(v.context, webview1::class.java)
//            i.putExtra("link","https://www.qarara.ps/news69.html")
//            startActivity(i)

            startActivity(Intent(v.context,PrivacyServices::class.java))
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
         * @return A new instance of fragment FragmentServices.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentServices().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}