package com.example.qarapp.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.qarapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_admin.*
import kotlinx.android.synthetic.main.activity_sign_admin.btnLogin
import kotlinx.android.synthetic.main.activity_sign_admin.etEmail
import kotlinx.android.synthetic.main.activity_sign_admin.etPassword

class sign_admin : AppCompatActivity() {

//    private lateinit var auth: FirebaseAuth
//    lateinit var db: FirebaseFirestore

    private var auth: FirebaseAuth? = null
    private var firebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_admin)


        auth = FirebaseAuth.getInstance()
        if (firebaseUser != null) {
            val intent = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                Toast.makeText(
                    applicationContext,
                    "الإيميل و كلمة السر مطلوبة",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                auth!!.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
//                            etEmail.setText("")
//                            etPassword.setText("")
                            if (etEmail.text.toString().equals("b.manager@gmail.com")) {
                                val intent = Intent(
                                    this,
                                    bmanager::class.java
                                )
                                startActivity(intent)
                                finish()
                            }
                            if (etEmail.text.toString().equals("c.manager@gmail.com")) {
                                val intent = Intent(
                                    this,
                                    cmanager::class.java
                                )
                                startActivity(intent)
                                finish()
                            }
                            if(etEmail.text.toString().equals("admin@gmail.com")) {
                                val intent = Intent(
                                    this,
                                    MainActivity::class.java
                                )
                                startActivity(intent)
                                finish()
                            }

//                            val intent = Intent(
//                                this,
//                                ::class.java
//                            )
//                            startActivity(intent)
//                            finish()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "خطأ في الإيميل و كلمة السر",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}


//        auth = Firebase.auth
//        db = Firebase.firestore
//        btnLogin.setOnClickListener {
//            if (etEmail.text!!.isEmpty() || etPassword.text!!.isEmpty()) {
//                Toast.makeText(this, "The field is empty", Toast.LENGTH_SHORT).show()
//            } else {
//                createNewAccount(etEmail.text.toString(), etPassword.text.toString())
//            }
//        }
//    }

//    private fun createNewAccount(email: String, pass: String) {
//        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
//            if (task.isSuccessful) {
//                Toast.makeText(this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show()
//                val user = auth.currentUser
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
//                Log.e("izz", "user ${user!!.uid} + ${user.email}")
//            } else {
//                Toast.makeText(this, "فشل في التسجيل", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//}