package com.example.qarapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.firebase.auth.FirebaseAuth



class HomeeActivity : AppCompatActivity() {
    private lateinit var toogle: ActionBarDrawerToggle
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var toolbar: Toolbar
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var appBarLayout: AppBarLayout
    lateinit var fragmentAdapter: FragmentAdapter
//    lateinit var textName:TextView
//    lateinit var textEmail:TextView
//    lateinit var imageViewEmail: ImageView

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homee)

        toolbar = findViewById(R.id.toolbar)
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.ViewPager)
        appBarLayout = findViewById(R.id.AppBarLayout)

//        textName = findViewById(R.id.textName)
//        textEmail = findViewById(R.id.textEmail)
//        imageViewEmail = findViewById(R.id.imageViewEmail)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        /*     val account = GoogleSignIn.getLastSignedInAccount(this)
             if (account != null) {
                 val personName = account.displayName
                 val personEmail = account.email
                 val personPhoto = account.photoUrl

     //            textName.setText(personName)
     //            textEmail.setText(personEmail)
     //            Picasso.get().load(personPhoto).into(imageViewEmail)

             }else{

     //            textName.setText("")
     //            textEmail.setText("")
     //            imageViewEmail.setImageResource(R.drawable.logo)
             }*/

        val drawerlayout = findViewById<DrawerLayout>(R.id.drawer_layoutt)
        toogle = ActionBarDrawerToggle(
            this,
            drawerlayout,
            toolbar,
            R.string.open,
            R.string.close
        )//connection toolbar and Drawer_layout
        drawerlayout.addDrawerListener(toogle)
        toogle.syncState()//create icon borger

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
            } else if (item_menu.itemId == R.id.Services1) {
                val i = Intent(applicationContext, TransactionsScreen::class.java)
                startActivity(i)
                drawerlayout.closeDrawers()
            } else if (item_menu.itemId == R.id.Services2) {
                val i = Intent(applicationContext, MyBillScreen::class.java)
                startActivity(i)
                drawerlayout.closeDrawers()
            } else if (item_menu.itemId == R.id.Services3) {
                val i = Intent(applicationContext, CounterReadScreen::class.java)
                startActivity(i)
                drawerlayout.closeDrawers()
            } else if (item_menu.itemId == R.id.Services4) {

                val i = Intent(applicationContext, webview1::class.java)
                i.putExtra("link", "https://www.qarara.ps/complaints.html")
                startActivity(i)
                drawerlayout.closeDrawers()

            } else if (item_menu.itemId == R.id.Services5) {
                val i = Intent(applicationContext, DisplayComplaintsScreen::class.java)
                startActivity(i)
                drawerlayout.closeDrawers()
            } else if (item_menu.itemId == R.id.Services6) {
                val i = Intent(applicationContext, webview1::class.java)
                i.putExtra("link", "https://www.qarara.ps/complaints.html")
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

        //init FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        // Configure Google Sign In
        val googleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        tabLayout.addTab(tabLayout.newTab().setText("??????????????"))
        tabLayout.addTab(tabLayout.newTab().setText("??????????"))
        tabLayout.addTab(tabLayout.newTab().setText("???? ??????????????"))


        tabLayout.getTabAt(0)!!.setIcon(R.drawable.iconservice)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.iconcomplaints)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.building)

        tabLayout.getTabAt(0)!!.icon!!.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)

        tabLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.icon!!.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.icon!!.setColorFilter(
                    resources.getColor(R.color.tabIconTint),
                    PorterDuff.Mode.SRC_IN
                )

            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


        fragmentAdapter = FragmentAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = fragmentAdapter

        viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tabLayout)
        )
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
    }

    private fun checkUser() {
        //check user logIn or Not
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            //user is already logged in
            val email = firebaseUser.email
            Toast.makeText(applicationContext, "loggedIn as $email", Toast.LENGTH_SHORT).show();

        } else {
            //user is not logged in
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        exist_dialogOnbackPressed()
    }

    fun exist_dialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle("????????????")
            .setMessage("???? ?????? ?????????? ???????????? ???? ????????????????")
            .setPositiveButton("??????") { dialogInterface, i ->
                firebaseAuth.signOut()
                LoginManager.getInstance().logOut()
                checkUser()
            }
            .setNegativeButton("????") { dialogInterface, i ->
                dialogInterface.cancel()
            }
        val AD = builder.create()
        AD.show()
    }


    fun exist_dialogOnbackPressed() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle("????????????")
            .setMessage("???? ?????? ???????????? ???? ????????????????")
            .setPositiveButton("??????") { dialogInterface, i ->
                val a = Intent(Intent.ACTION_MAIN)
                a.addCategory(Intent.CATEGORY_HOME)
                a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(a)
            }
            .setNegativeButton("????") { dialogInterface, i ->
                dialogInterface.cancel()
            }
        val AD = builder.create()
        AD.show()
    }
}