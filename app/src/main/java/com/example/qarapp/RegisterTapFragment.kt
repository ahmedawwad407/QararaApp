package com.example.qarapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RegisterTapFragment : Fragment() {
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var databaseReference: DatabaseReference
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var progressDialog: ProgressDialog
    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var phone: EditText
    lateinit var subscription: EditText
    lateinit var register: Button
    var isNameValid = false
    var isEmailValid: Boolean = false
    var isPhoneValid: Boolean = false
    var isSubscriptionValid: Boolean = false
    var isPasswordValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.register_tap_fragment, container, false)
        name = v.findViewById(R.id.name);
        email = v.findViewById(R.id.eemail);
        password = v.findViewById(R.id.ppassword);
        phone = v.findViewById(R.id.pphone);
        subscription = v.findViewById(R.id.Subscription);
        register = v.findViewById(R.id.register);

        //progressDialog
        progressDialog = ProgressDialog(v.context)
        progressDialog.setTitle("الرجاء الإنتظار")
        progressDialog.setMessage("جااري انشاء الحساب.....")
        progressDialog.setCanceledOnTouchOutside(true)

        //init FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()


        register.setOnClickListener {
            SetValidation(v)
        }
        return v
    }

    fun SetValidation(view: View) {
        val emailtext = email.text.toString().trim()
        val passwordtext = password.text.toString().trim()

        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            name.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else {
            isNameValid = true;
        }

        // Check for a valid email address.
        if (emailtext.isEmpty()) {
            email.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()) {
            email.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else {
            isEmailValid = true;
        }
        // Check for a valid phone number.
        if (phone.getText().toString().isEmpty()) {
            phone.setError(getResources().getString(R.string.phone_error));
            isPhoneValid = false;
        } else {
            isPhoneValid = true;
        }


        // Check for a valid subscription.
        if (subscription.getText().toString().isEmpty()) {
            subscription.setError(getResources().getString(R.string.subscription_error));
            isSubscriptionValid = false;
        } else {
            isSubscriptionValid = true;
        }

        // Check for a valid password.
        if (passwordtext.isEmpty()) {
            password.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (passwordtext.length < 6) {
            password.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
        }

        if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid && isSubscriptionValid) {
            addDataInFirebase()
            firebaseSignUp(view)
        }

    }

    private fun addDataInFirebase() {
        //init firebaseDatabase
        firebaseDatabase = Firebase.database
        databaseReference = firebaseDatabase.getReference("RegisterData")
        val nameText = name.text.toString()
        val phoneText = phone.text.toString()
        val passwordText = password.text.toString()
        val emailText = email.text.toString()
        val subscriptionText = subscription.text.toString()

        val userData = UserData(
            nameText,
            emailText,
            phoneText,
            subscriptionText,
            passwordText
        )
        //addValue reference
        databaseReference.child(phoneText).setValue(userData)

    }

    fun firebaseSignUp(view: View) {
        val emailtext = email.text.toString().trim()
        val passwordtext = password.text.toString().trim()
        progressDialog.show()

        //create account
        firebaseAuth.createUserWithEmailAndPassword(emailtext, passwordtext)
            .addOnSuccessListener {
                //signUP success
                progressDialog.dismiss()
                //get user info
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
               // Toast.makeText(view.context, "Account created by $email", Toast.LENGTH_SHORT)
                //    .show();

                startActivity(Intent(view.context, HomeeActivity::class.java))
                activity?.finish()
            }
            .addOnFailureListener {
                //signUP Failed
                progressDialog.dismiss()
                Toast.makeText(
                    view.context,
                    "signUP Failed due to ${it.message}",
                    Toast.LENGTH_SHORT
                ).show();
            }
    }

}

