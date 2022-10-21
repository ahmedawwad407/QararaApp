package com.example.qarapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import androidx.cardview.widget.CardView

class PrivacyServices : AppCompatActivity() {

    lateinit var cardview01: CardView
    lateinit var cardview02: CardView
    lateinit var cardview03: CardView
    lateinit var cardview04: CardView
    lateinit var cardview05: CardView
    lateinit var cardview06: CardView
    lateinit var popupMenu: PopupMenu
    lateinit var view: View

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_services)

        cardview01 = findViewById(R.id.cardview01)
        cardview02 = findViewById(R.id.cardview02)
        cardview03 = findViewById(R.id.cardview03)
        cardview04 = findViewById(R.id.cardview04)
        cardview05 = findViewById(R.id.cardview05)
        cardview06 = findViewById(R.id.cardview06)

        cardview01.setOnClickListener { view ->
            popupMenu = PopupMenu(applicationContext, view)
            popupMenu.menuInflater.inflate(R.menu.menu_button, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.Add_1) {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/building_types/index.php"))
                    startActivity(i)

                } else if (item.itemId == R.id.Search_1) {

                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/buildings/index.php"))
                    startActivity(i)

                }


                false
            }

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                popupMenu.setForceShowIcon(true)
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                popupMenu.gravity = Gravity.END
            }
            popupMenu.show()
        }

        cardview02.setOnClickListener { view ->
            popupMenu = PopupMenu(applicationContext, view)
            popupMenu.menuInflater.inflate(R.menu.menu_button2, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.Add_2) {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/water/types/index.php"))
                    startActivity(i)


                } else if (item.itemId == R.id.Search_2) {

                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/water/index.php"))
                    startActivity(i)

                }else if (item.itemId == R.id.Importt) {

                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/water/types/index.php"))
                    startActivity(i)

                }

                false
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                popupMenu.setForceShowIcon(true)
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                popupMenu.gravity = Gravity.END
            }
            popupMenu.show()

        }

        cardview03.setOnClickListener { view ->

            popupMenu = PopupMenu(applicationContext, view)
            popupMenu.menuInflater.inflate(R.menu.menu_button3, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.Add_3) {

                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/water/types/index.php"))
                    startActivity(i)


                } else if (item.itemId == R.id.Search_3) {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/water/types/index.php"))
                    startActivity(i)


                }else if (item.itemId == R.id.Uses) {

                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/water/types/index.php"))
                    startActivity(i)

                }

                false
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                popupMenu.setForceShowIcon(true)
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                popupMenu.gravity = Gravity.END
            }
            popupMenu.show()

        }


        cardview04.setOnClickListener { view ->
            popupMenu = PopupMenu(applicationContext, view)
            popupMenu.menuInflater.inflate(R.menu.menu_button4, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.Add_4) {

                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/water/types/index.php"))
                    startActivity(i)

                } else if (item.itemId == R.id.Search_4) {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/economical/index.php"))
                    startActivity(i)

                }else if (item.itemId == R.id.Filter_1) {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/water/types/index.php"))
                    startActivity(i)

                }else if (item.itemId == R.id.Filter_2) {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/water/types/index.php"))
                    startActivity(i)

                }

                false
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                popupMenu.setForceShowIcon(true)
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                popupMenu.gravity = Gravity.END
            }
            popupMenu.show()

        }

        cardview05.setOnClickListener { view ->
            popupMenu = PopupMenu(applicationContext, view)
            popupMenu.menuInflater.inflate(R.menu.menu_button5, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.Add_5) {

                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/water/types/index.php"))
                    startActivity(i)
                } else if (item.itemId == R.id.Search_5) {

                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse("http://10.20.30.48/jcp/water/types/index.php"))
                    startActivity(i)
                }
                false
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                popupMenu.setForceShowIcon(true)
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                popupMenu.gravity = Gravity.END
            }
            popupMenu.show()

        }

        cardview06.setOnClickListener { view ->

        }
    }
}