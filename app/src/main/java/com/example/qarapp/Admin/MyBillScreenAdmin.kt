package com.example.qarapp.Admin

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qarapp.*
import com.example.qarapp.Adapter.MyRecyclerAdapterList2
import com.example.qarapp.MainActivity
import com.example.qarapp.models.MyModel3
import com.facebook.login.LoginManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_bill_management.*
import kotlinx.android.synthetic.main.activity_counter_read_screen_admin.*
import kotlinx.android.synthetic.main.activity_my_bill_screen_admin.*
import kotlinx.android.synthetic.main.activity_my_bill_screen_admin.Btn3
import java.util.*

class MyBillScreenAdmin : AppCompatActivity() {
    private lateinit var toogle: ActionBarDrawerToggle
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var toolbar: Toolbar
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_bill_screen_admin)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        db = Firebase.firestore

        var id = intent.getStringExtra("id")!!
//        var name = intent.getStringExtra("name")!!

        var ref = db.collection("users").document(id)
        ref.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot != null) {
                var data = documentSnapshot.data
                val subscription = data!!["subscription"] as String?
                val name = data!!["name"] as String?
                Tx2.setText(subscription)
                Tx4.setText(name)
            } else {
                Toast.makeText(this, "Failer", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener { exception ->
        }

        Tx6.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val day = currentDate.get(Calendar.DAY_OF_MONTH)
            val month = currentDate.get(Calendar.MONTH)
            val year = currentDate.get(Calendar.YEAR)

            val Picker = DatePickerDialog(
                this,
                { datePicker, i, i2, i3 -> Tx6.setText("$i/${1 + i2}/$i3") },
                year,
                month,
                day
            )
            Picker.show()
        }

        firebaseAuth = FirebaseAuth.getInstance()
        db = Firebase.firestore
        Btn3.setOnClickListener {
            add_MyBillAdmin(
                Tx2.text.toString(),
                Tx4.text.toString(),
                Tx6.text.toString(),
                Tx10.selectedItem.toString(),
                Tx8.text.toString().toInt(),
                Tx14.text.toString().toInt(),
                Tx16.text.toString().toInt(),
            )

            add_notifications(
                Tx4.text.toString(),
                "الفاتورة",
                " يرجى تسديد الفاتورة بقيمة : ${Tx8.text.toString().toInt()}"
            )
        }
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

    private fun add_MyBillAdmin(
        partnership_num: String?,
        partnership_name: String?,
        partnership_Date: String?,
        Month: String?,
        consumption_amount: Int?,
        Sanitation: Int?,
        arrears: Int?,
    ) {
        var Complaints = hashMapOf(
            "partnership_num" to partnership_num,
            "partnership_name" to partnership_name,
            "partnership_Date" to partnership_Date,
            "Month" to Month,
            "consumption_amount" to consumption_amount,//كمية الاستهلاك
            "Sanitation" to Sanitation,//الصرف الصحي
            "arrears" to arrears,//المتأخرات
            "paid" to 0, //هل تم تسديد الفاتورة ام لا
            "created_at" to com.google.firebase.Timestamp(Date()),
        )
        db.collection("partnership_users").add(Complaints)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "تم اضافة", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { exception ->
            }
    }

    private fun add_notifications(
        name: String?,
        title: String?,
        topic: String?,
    ) {
        var Complaints = hashMapOf(
            "name" to name,
            "title" to title,
            "topic" to topic,
            "created_at" to com.google.firebase.Timestamp(Date()),
        )
        db.collection("notifications").add(Complaints)
            .addOnSuccessListener { documentReference ->
            }.addOnFailureListener { exception ->
            }
    }

}