package com.example.qarapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.login.LoginManager
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ImagesOfNews : AppCompatActivity() {
    private lateinit var toogle: ActionBarDrawerToggle
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var toolbar: Toolbar
     lateinit var recyclerView:RecyclerView
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images_of_news)

        toolbar = findViewById(R.id.toolbar)


        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        recyclerView = findViewById(R.id.RecyclerNews)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //init FirebaseDatabase
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.getReference("Data")


        //Read Images
        val options =
            FirebaseRecyclerOptions.Builder<Members>()
                .setQuery(databaseReference, Members::class.java)
                .setLifecycleOwner(this)
                .build()
       val firebaseRecyclerAdapter = object :
            FirebaseRecyclerAdapter<Members, ViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.image, parent, false)
                return ViewHolder(view)
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int, members: Members) {
                holder.setDetails(applicationContext,members.image)
            }

        }
        recyclerView.adapter = firebaseRecyclerAdapter

        val drawerlayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        toogle = ActionBarDrawerToggle(
            this,
            drawerlayout,
            toolbar,
            R.string.open,
            R.string.close
        )//connection toolbar and Drawer_layout
        toogle.syncState()//create icon borger
        drawerlayout.addDrawerListener(toogle)

        val navigationView = findViewById<NavigationView>(R.id.NavigationView)
        navigationView.itemIconTintList = null

        navigationView.setNavigationItemSelectedListener { item_menu ->
            if (item_menu.itemId == R.id.Home) {
                val i = Intent(applicationContext, HomeeActivity::class.java)
                startActivity(i)
                drawerlayout.closeDrawers()//close navigationDrawer
            } else if (item_menu.itemId == R.id.News) {

                val i = Intent(applicationContext, webview1::class.java)
                i.putExtra("link", "https://www.qarara.ps/news10.html")
                startActivity(i)
                drawerlayout.closeDrawers()
            } else if (item_menu.itemId == R.id.VideoScreen) {
                val i = Intent(applicationContext, VideoOffLoading::class.java)
                startActivity(i)
                drawerlayout.closeDrawers()
            } else if (item_menu.itemId == R.id.AboutApp) {
                val i = Intent(applicationContext, AboutScreen::class.java)
                startActivity(i)
                drawerlayout.closeDrawers()
            } else if (item_menu.itemId == R.id.ImageScreen) {
                val i = Intent(applicationContext, ImagesOfNews::class.java)
                startActivity(i)
                drawerlayout.closeDrawers()
            } else if (item_menu.itemId == R.id.logOut) {
                exist_dialog()
                drawerlayout.closeDrawers()
            }
            false
        }


    }
    fun exist_dialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle("الخروج")
            .setMessage("هل تود الخروج من التطبيق؟")
            .setPositiveButton("نعم") { dialogInterface, i ->
                firebaseAuth.signOut()
                LoginManager.getInstance().logOut()
                checkUser()
            }
            .setNegativeButton("لا") { dialogInterface, i ->
                dialogInterface.cancel()
            }
        val AD = builder.create()
        AD.show()
    }

    private fun checkUser() {
        //check user logIn or Not
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            //user is already logged in
            val email = firebaseUser.email
            Toast.makeText(applicationContext, "loggedIn as $email", Toast.LENGTH_SHORT).show()

        } else {
            //user is not logged in
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

}