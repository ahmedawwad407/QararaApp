package com.example.qarapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.facebook.login.LoginManager

import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class NewsScreen : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var toolbar: Toolbar
    // lateinit var recyclerView: RecyclerView

    private lateinit var toogle: ActionBarDrawerToggle
    lateinit var newsButton: ImageButton
    var link = "https://www.qarara.ps/news10.html"

    //    lateinit var news1: News
//    lateinit var news2: News
//    lateinit var news3: News
//    lateinit var news4: News
//    lateinit var news5: News
//    lateinit var news6: News
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_screen)

        toolbar = findViewById(R.id.toolbar)
        //recyclerView = findViewById(R.id.RecyclerNews)
        newsButton = findViewById(R.id.NewsButton)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)


        //init FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

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


//    newsButton.setOnClickListener {
//        val i = Intent(Intent.ACTION_VIEW)
//        i.setData(Uri.parse(link))
//        startActivity(i)
//    }

//        news1 = News()
//        news1.photo = R.drawable.qarara
//        news1.title = "......................................"
//        news1.date = "2021-05-08T14:53:12Z"
//
//        news2 = News()
//        news2.photo = R.drawable.qarara
//        news2.title = " ......................................"
//        news2.date = "2021-05-08T23:47:00Z"
//
//        news3 = News()
//        news3.photo = R.drawable.qarara
//        news3.title =
//            "......................................"
//        news3.date = "2021-05-08T13:13:55Z"
//
//        news4 = News()
//        news4.photo = R.drawable.qarara
//        news4.title = "......................................"
//        news4.date = "2021-05-08T15:19:30Z"
//
//        news5 = News()
//        news5.photo = R.drawable.qarara
//        news5.title = "......................................"
//        news5.date = "2021-05-08T20:06:30Z"
//
//        news6 = News()
//        news6.photo = R.drawable.qarara
//        news6.title = "......................................"
//        news6.date = "2021-05-08T22:10:28Z"
//
//        var arr = arrayListOf<News>(
//            news1, news2, news3, news4, news5, news6,
//            news1, news2, news3, news4, news5, news6,
//            news1, news2, news3, news4, news5, news6
//        )
//
//        val adapter_view =
//            News_adapter(R.layout.news, arr, this)
//        recyclerView.adapter = adapter_view
//
//        val m = LinearLayoutManager(this)
//        m.orientation = LinearLayoutManager.VERTICAL
//        recyclerView.layoutManager = m
//
//        //add divider
//        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL) // حسب layout
//        val d = resources.getDrawable(R.drawable.vv)
//        divider.setDrawable(d)//add Icon
//        recyclerView.addItemDecoration(divider)


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