package com.example.qarapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCompalints.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentCompalints : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val link ="https://www.qarara.ps/complaints.html"
    lateinit var cardview01: CardView
    lateinit var cardview02: CardView
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
        val v= inflater.inflate(R.layout.fragment_compalints, container, false)
        cardview01 =v.findViewById(R.id.cardview01)
        cardview02 =v.findViewById(R.id.cardview02)


        cardview02.setOnClickListener {
//            val i = Intent(v.context, webview1::class.java)
//            i.putExtra("link",link)
//            startActivity(i)
            startActivity(Intent(v.context, ComplaintsScreen::class.java))

        }

        cardview01.setOnClickListener {
            startActivity(Intent(v.context, DisplayComplaintsScreen::class.java))
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
         * @return A new instance of fragment FragmentCompalints.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCompalints().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}