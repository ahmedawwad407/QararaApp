package com.example.qarapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth

class passwordReminderActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var toolbar: Toolbar
    lateinit var email: EditText
    var isEmailValid: Boolean = false
    lateinit var Btn3: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reminder)


        email = findViewById(R.id.email)
        Btn3 = findViewById(R.id.Btn3)

        //init FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        Btn3.setOnClickListener {
            SetValidation()

        }

    }

    fun SetValidation() {
        // Check for a valid email address.
        val emailText = email.text.toString()
        if (emailText.isEmpty()) {
            email.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else {
            isEmailValid = true;
        }

        if (isEmailValid) {
            firebaseAuth.sendPasswordResetEmail(emailText)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(getApplicationContext(), "please check your account email..", Toast.LENGTH_SHORT).show();
                        startActivity(Intent(applicationContext, MainActivity::class.java))

                    }else{
                        val massage = it.exception!!.message
                        Toast.makeText(getApplicationContext(), "Error:$massage", Toast.LENGTH_SHORT).show();

                    }
                }
        }
    }

}