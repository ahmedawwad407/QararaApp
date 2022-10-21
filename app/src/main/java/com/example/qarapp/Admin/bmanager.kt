package com.example.qarapp.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.qarapp.R
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_bmanager.*

class bmanager : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmanager)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        logOut.setOnClickListener {
            exist_dialog()
        }

       /* monthly.setOnClickListener {
            startActivity(Intent(this, UsersActivity3::class.java))
        }*/

        bill_management.setOnClickListener {
            var xx = Intent(this, Search_Users::class.java)
            xx.putExtra("key3", true)
            startActivity(xx)
        }

        myuse.setOnClickListener {
            var xx = Intent(this, Search_Users::class.java)
            xx.putExtra("key", true)
            startActivity(xx)
        }
    }

    fun exist_dialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle("الخروج")
            .setMessage("هل تود تسجيل الخروج من التطبيق؟")
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
           // Toast.makeText(applicationContext, "loggedIn as $email", Toast.LENGTH_SHORT).show();

        } else {
            //user is not logged in
            startActivity(Intent(applicationContext, sign_admin::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        exist_dialog()
    }
}