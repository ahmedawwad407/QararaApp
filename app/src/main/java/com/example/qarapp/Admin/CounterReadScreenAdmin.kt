package com.example.qarapp.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.qarapp.*
import com.facebook.login.LoginManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_counter_read_screen_admin.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*

class CounterReadScreenAdmin : AppCompatActivity() {
    private lateinit var toogle: ActionBarDrawerToggle
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var toolbar: Toolbar
    lateinit var db: FirebaseFirestore
    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter_read_screen_admin)
        toolbar = findViewById(R.id.toolbar)
        db = Firebase.firestore
        id = intent.getStringExtra("id")!!
        var name = intent.getStringExtra("name")!!
        ername.setText(name.toString())
        firebaseAuth = FirebaseAuth.getInstance()
        if (TextUtils.isEmpty(ername.text.toString())) {
            Toast.makeText(applicationContext, "username is required", Toast.LENGTH_SHORT)
                .show()
        }
        if (TextUtils.isEmpty(erNum.text.toString())) {
            Toast.makeText(applicationContext, "Enter the first five numbers from the left", Toast.LENGTH_SHORT).show()
        }

        if (TextUtils.isEmpty(meerNum.text.toString())) {
            Toast.makeText(applicationContext, "Read confirmation", Toast.LENGTH_SHORT)
                .show()
        }
        Btn3.setOnClickListener {
            add_Counter(
                ername.text.toString(),
                erNum.text.toString(),
                meerNum.text.toString()
            )
        }

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
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

    private fun add_Counter(
        username: String?,
        CounterNum: String?,
        ConfirmeCounterNum: String?,
    ) {
        var Counter = hashMapOf(
            "id" to id,
            "username" to username,
            "CounterNum" to CounterNum,
            "ConfirmeCounterNum" to ConfirmeCounterNum,
            "created_at" to com.google.firebase.Timestamp(Date()),
        )
        db.collection("Counter").add(Counter)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "تم  قراءة العداد ", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { exception ->
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
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            val email = firebaseUser.email
            Toast.makeText(applicationContext, "loggedIn as $email", Toast.LENGTH_SHORT).show()
        } else {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }
}