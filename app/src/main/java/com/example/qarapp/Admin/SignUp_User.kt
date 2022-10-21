package com.example.qarapp.Admin

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.qarapp.HomeeActivity
import com.example.qarapp.R
import com.example.qarapp.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.register_tap_fragment.*
import kotlinx.android.synthetic.main.register_tap_fragment.view.*

class SignUp_User : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    lateinit var db: FirebaseFirestore
    lateinit var progressDialog: ProgressDialog
    var isNameValid = false
    var isEmailValid: Boolean = false
    var isPhoneValid: Boolean = false
    var isSubscriptionValid: Boolean = false
    var isPasswordValid: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.register_tap_fragment, container, false)
        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth
        db = Firebase.firestore

        //progressDialog
        progressDialog = ProgressDialog(v.context)
        progressDialog.setTitle("الرجاء الإنتظار")
        progressDialog.setMessage("جااري انشاء الحساب.....")
        progressDialog.setCanceledOnTouchOutside(true)

        v.register.setOnClickListener {
            SetValidation(v)
        }
        return v

    }


    fun SetValidation(view: View) {
        val emailtext = eemail.text.toString().trim()
        val passwordtext = ppassword.text.toString().trim()

        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            name.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else {
            isNameValid = true;
        }

        // Check for a valid email address.
        if (emailtext.isEmpty()) {
            eemail.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()) {
            eemail.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else {
            isEmailValid = true;
        }
        // Check for a valid phone number.
        if (pphone.getText().toString().isEmpty()) {
            pphone.setError(getResources().getString(R.string.phone_error));
            isPhoneValid = false;
        } else {
            isPhoneValid = true;
        }


        // Check for a valid subscription.
        if (Subscription.getText().toString().isEmpty()) {
            Subscription.setError(getResources().getString(R.string.subscription_error));
            isSubscriptionValid = false;
        } else {
            isSubscriptionValid = true;
        }

        // Check for a valid password.
        if (passwordtext.isEmpty()) {
            ppassword.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (passwordtext.length < 6) {
            ppassword.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
        }

        if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid && isSubscriptionValid) {
            val userName = name.text.toString()
            val email = eemail.text.toString()
            val password = ppassword.text.toString()
            val phone = pphone.text.toString()
            val subscription = Subscription.text.toString()


            registerUser(userName, email, password,subscription,phone)
            addUser(userName,email,password,subscription,phone)
        }

    }


    private fun addUser(fname: String, email: String,password: String,subscription:String,phone:String) {
        var user = hashMapOf("name" to fname, "email" to email, "password" to password,"subscription" to subscription,"phone" to phone)
        db!!.collection("users").add(user).addOnSuccessListener { documentReference ->
            Log.e("Document ID", documentReference.id)
        }.addOnFailureListener { exception ->
        }
    }

    private fun registerUser(userName: String, email: String, password: String,subscription:String,phone:String) {
            progressDialog.show()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val user: FirebaseUser? = auth.currentUser
                    val userId: String = user!!.uid
                    progressDialog.dismiss()

                    databaseReference =
                        FirebaseDatabase.getInstance().getReference("Users").child(userId)

                    val hashMap: HashMap<String, String> = HashMap()
                    hashMap.put("userId", userId)
                    hashMap.put("userName", userName)
                    hashMap.put("Email", email)
                    hashMap.put("password", password)
                    hashMap.put("phone", phone)
                    hashMap.put("subscription", subscription)
                    hashMap.put("profileImage", "")

                    databaseReference.setValue(hashMap).addOnCompleteListener {
                        if (it.isSuccessful) {
                            //open home activity
                            progressDialog.dismiss()
                            name.setText("")
                            eemail.setText("")
                            ppassword.setText("")
                            Subscription.setText("")
                            pphone.setText("")
                            Toast.makeText(context, "تم تسجيل الدخول", Toast.LENGTH_SHORT).show()
                            val intent = Intent(context, HomeeActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
    }


}