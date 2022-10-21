package com.example.qarapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.facebook.login.LoginManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.activity_list_my_bill_screen.*
import kotlinx.android.synthetic.main.activity_my_bill_screen.*
import kotlinx.android.synthetic.main.activity_my_bill_screen.ivBack

class MyBillScreen : AppCompatActivity() {
    private lateinit var toogle: ActionBarDrawerToggle
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var toolbar: Toolbar
    lateinit var db: FirebaseFirestore
    var id = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_bill_screen)

        ivBack.setOnClickListener {
            onBackPressed()
        }
        id = intent.getStringExtra("id")!!
        var db = FirebaseFirestore.getInstance()
        var name = intent.getStringExtra("name")!!
        var ref = db.collection("partnership_users").document(id)
        ref.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot != null) {
                var data = documentSnapshot.data
                var partnership_name = data!!["partnership_name"] as String
                val partnership_num = data["partnership_num"] as String
                var partnership_Date = data["partnership_Date"] as String
                var arrears = data["arrears"] as Number
                var Sanitation = data["Sanitation"] as Number
                var consumption_amount = data["consumption_amount"] as Number
                tvLblReview.setText(partnership_num)
                Tx4.setText(partnership_name)
                Tx6.setText(partnership_Date)
                Tx8.setText(consumption_amount.toString())
                Tx14.setText(Sanitation.toString())
                Tx16.setText(arrears.toString())
                var x = (consumption_amount.toInt()+Sanitation.toInt()+arrears.toInt())/4
                tx22.setText(x.toString())

                circularProgressBar.apply {
                    progress = 100f
                    setProgressWithAnimation(consumption_amount.toFloat(), 3000) // =1s
                    progressMax = 100f
                    if (consumption_amount.toInt() < 50) {
                        progressBarColor = Color.GREEN
                    } else {
                        progressBarColor = Color.RED
                    }
                    //progressBarColor = Color.RED
                    progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
//                    backgroundProgressBarColorEnd = Color.WHITE
                    backgroundProgressBarColorDirection =
                        CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
                    progressBarWidth = 7f // in DP
                    backgroundProgressBarWidth = 3f // in DP
                    roundBorder = true
                    startAngle = 180f
                    progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
                }
                circularProgressBar4.apply {
                    progress = 100f
                    setProgressWithAnimation(Sanitation.toFloat(), 3000) // =1s
                    progressMax = 100f
                    if (Sanitation.toInt() < 50) {
                        progressBarColor = Color.GREEN
                    } else {
                        progressBarColor = Color.RED
                    }
                    //progressBarColor = Color.RED
                    progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
//                    backgroundProgressBarColorEnd = Color.WHITE
                    backgroundProgressBarColorDirection =
                        CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
                    progressBarWidth = 7f // in DP
                    backgroundProgressBarWidth = 3f // in DP
                    roundBorder = true
                    startAngle = 180f
                    progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
                }
                circularProgressBar5.apply {
                    progress = 100f
                    setProgressWithAnimation(arrears.toFloat(), 3000) // =1s
                    progressMax = 100f
                    if (arrears.toInt() < 50) {
                        progressBarColor = Color.GREEN
                    } else {
                        progressBarColor = Color.RED
                    }
                    //progressBarColor = Color.RED
                    progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
//                    backgroundProgressBarColorEnd = Color.WHITE
                    backgroundProgressBarColorDirection =
                        CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
                    progressBarWidth = 7f // in DP
                    backgroundProgressBarWidth = 3f // in DP
                    roundBorder = true
                    startAngle = 180f
                    progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
                }

                circularProgressBar6.apply {
                    progress = 100f
                    setProgressWithAnimation(x.toFloat(), 3000) // =1s
                    progressMax = 100f
                    if (x.toInt() < 50) {
                        progressBarColor = Color.GREEN
                    } else {
                        progressBarColor = Color.RED
                    }
                    //progressBarColor = Color.RED
                    progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
//                    backgroundProgressBarColorEnd = Color.WHITE
                    backgroundProgressBarColorDirection =
                        CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
                    progressBarWidth = 7f // in DP
                    backgroundProgressBarWidth = 3f // in DP
                    roundBorder = true
                    startAngle = 180f
                    progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
                }
            }
        }


    }

//    fun exist_dialog() {
//        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
//            .setCancelable(false)
//            .setTitle("الخروج")
//            .setMessage("هل تود الخروج من التطبيق؟")
//            .setPositiveButton("نعم") { dialogInterface, i ->
//                firebaseAuth.signOut()
//                LoginManager.getInstance().logOut()
//                checkUser()
//            }
//            .setNegativeButton("لا") { dialogInterface, i ->
//                dialogInterface.cancel()
//            }
//        val AD = builder.create()
//        AD.show()
//    }
//
//    private fun checkUser() {
//        //check user logIn or Not
//        val firebaseUser = firebaseAuth.currentUser
//        if (firebaseUser != null) {
//            //user is already logged in
//            val email = firebaseUser.email
//            Toast.makeText(applicationContext, "loggedIn as $email", Toast.LENGTH_SHORT).show()
//
//        } else {
//            //user is not logged in
//            startActivity(Intent(applicationContext, MainActivity::class.java))
//            finish()
//        }
//    }
}