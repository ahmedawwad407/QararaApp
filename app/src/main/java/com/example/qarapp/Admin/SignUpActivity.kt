package com.example.qarapp.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.qarapp.HomeeActivity
import com.example.qarapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth
        db = Firebase.firestore

        btnSignUp.setOnClickListener {
            val userName = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(applicationContext, "username is required", Toast.LENGTH_SHORT)
                    .show()
            }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "email is required", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "password is required", Toast.LENGTH_SHORT)
                    .show()
            }

            if (TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(
                    applicationContext,
                    "confirm password is required",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(applicationContext, "password not match", Toast.LENGTH_SHORT).show()
            }
            registerUser(userName, email, password)
            addUser(userName,email, password)
        }
    }

    private fun addUser(fname: String, email: String,password: String) {
        var user = hashMapOf("name" to fname, "email" to email, "password" to password)
        db!!.collection("users").add(user).addOnSuccessListener { documentReference ->
            Log.e("Document ID", documentReference.id)
        }.addOnFailureListener { exception ->
        }
    }

    private fun registerUser(userName: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    val userId: String = user!!.uid

                    databaseReference =
                        FirebaseDatabase.getInstance().getReference("Users").child(userId)

                    val hashMap: HashMap<String, String> = HashMap()
                    hashMap.put("userId", userId)
                    hashMap.put("userName", userName)
                    hashMap.put("Email", email)
                    hashMap.put("password", password)
                    hashMap.put("profileImage", "")

                    databaseReference.setValue(hashMap).addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            //open home activity
                            etName.setText("")
                            etEmail.setText("")
                            etPassword.setText("")
                            etConfirmPassword.setText("")
                            Toast.makeText(applicationContext, "تم تسجيل الدخول", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, HomeeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
    }
}