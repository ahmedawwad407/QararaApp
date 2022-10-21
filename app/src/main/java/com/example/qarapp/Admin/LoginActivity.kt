package com.example.qarapp.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.qarapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var auth: FirebaseAuth? = null
    private  var firebaseUser: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
            auth = FirebaseAuth.getInstance()
            if (firebaseUser != null) {
                val intent = Intent(
                    this@LoginActivity,
                    UsersActivity::class.java
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
                                etEmail.setText("")
                                etPassword.setText("")
                                val intent = Intent(
                                    this@LoginActivity,
                                    MainActivity::class.java
                                )
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "الإيميل و كلمة السر مطلوبة",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }

            btnSignUp.setOnClickListener {
                val intent = Intent(
                    this@LoginActivity,
                    SignUpActivity::class.java
                )
                startActivity(intent)
                finish()
            }
        }
    }