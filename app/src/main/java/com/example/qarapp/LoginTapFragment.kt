package com.example.qarapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class LoginTapFragment : Fragment() {

    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var forgetPassword: TextView
    lateinit var login: Button
    var isEmailValid: Boolean = false
    var isPasswordValid: Boolean = false
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var progressDialog: ProgressDialog
    val i = 0f


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.login_tap_fragment, container, false)




        email = v.findViewById(R.id.email)
        password = v.findViewById(R.id.password)
        forgetPassword = v.findViewById(R.id.PasswordReminder)
        login = v.findViewById(R.id.login)

        //progressDialog
        progressDialog = ProgressDialog(v.context)
        progressDialog.setTitle("الرجاء الإنتظار")
        progressDialog.setMessage("جاري تسجيل الدخول....")
        progressDialog.setCanceledOnTouchOutside(false)



        //init FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        checkUser(v)

        login.setOnClickListener {
            SetValidation(v)

        }
        forgetPassword.setOnClickListener {
            startActivity(Intent(v.context, passwordReminderActivity::class.java))
        }

        email.translationY = 300f
        password.translationY = 300f
        forgetPassword.translationY = 300f
        login.translationY = 300f

        email.alpha = i
        password.alpha = i
        forgetPassword.alpha = i
        login.alpha = i

        email.animate().translationY(0f).alpha(1f).setDuration(800).setStartDelay(300).start()
        password.animate().translationY(0f).alpha(1f).setDuration(800).setStartDelay(500).start()
        forgetPassword.animate().translationY(0f).alpha(1f).setDuration(800).setStartDelay(500)
            .start()
        login.animate().translationY(0f).alpha(1f).setDuration(800).setStartDelay(700).start()
        return v
    }

    fun SetValidation(view: View) {

        val emailtext = email.text.toString().trim()
        val passwordtext = password.text.toString().trim()
        // Check for a valid email address.
        if (emailtext.isEmpty()) {
            email.error = resources.getString(R.string.email_error);
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()) {
            email.error = resources.getString(R.string.error_invalid_email);
            isEmailValid = false;
        } else {
            isEmailValid = true;
        }

        // Check for a valid password.
        if (passwordtext.isEmpty()) {
            password.error = resources.getString(R.string.password_error);
            isPasswordValid = false;
        } else if (passwordtext.length < 6) {
            password.error = resources.getString(R.string.error_invalid_password);
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
        }

        if (isEmailValid && isPasswordValid) {
            firebaseLogin(view)
        }

    }


    fun firebaseLogin(view: View) {
        val emailtext = email.text.toString().trim()
        val passwordtext = password.text.toString().trim()
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(emailtext, passwordtext)
            .addOnSuccessListener {
                //login success
                progressDialog.dismiss()
                //get user info
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
              //  Toast.makeText(view.context, "loggedIn as $email", Toast.LENGTH_SHORT).show();
                startActivity(Intent(view.context, HomeeActivity::class.java))
                activity?.finish()
            }.addOnFailureListener {
                //login Failed
                progressDialog.dismiss()
                Toast.makeText(
                    view.context,
                    "login Failed due to ${it.message}",
                    Toast.LENGTH_SHORT
                ).show();

            }
    }

    fun checkUser(view: View) {
        //check info user
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            //user is already logged in
            startActivity(Intent(view.context, HomeeActivity::class.java))
            activity?.finish()
        }
    }


}