package com.example.qarapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.qarapp.Admin.sign_admin
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthProvider
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var loginGoogle: FloatingActionButton
    lateinit var loginFacebook: FloatingActionButton
    lateinit var loginTwitter: FloatingActionButton
    lateinit var callbackManager: CallbackManager
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var provider: OAuthProvider.Builder
    lateinit var authStateListener: AuthStateListener
//    lateinit var textName: TextView
//    lateinit var textEmail: TextView
//    lateinit var imageViewEmail: ImageView

    companion object {
        private const val RC_SIGN_IN = 120
        private const val TAG = "SIGN_IN"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main)

        sign_insopping.setOnClickListener {
            startActivity(Intent(this, sign_admin::class.java))
        }



        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.ViewPager);

        loginGoogle = findViewById(R.id.LoginGoogle);
        loginFacebook = findViewById(R.id.LoginFacebook);
        loginTwitter = findViewById(R.id.LoginTwitter);

//        textName = findViewById(R.id.textName)
//        textEmail = findViewById(R.id.textEmail)
//        imageViewEmail = findViewById(R.id.imageViewEmail)

        //init FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        authStateListener = AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this@MainActivity, HomeeActivity::class.java))
            }
        }
        //init FacebookSdk
        FacebookSdk.sdkInitialize(applicationContext);

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create();
        // Callback registration
        loginFacebook.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(
                this,
                arrayListOf("email", "public_profile")
            )
            LoginManager.getInstance()
                .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        //  Log.d(TAG, "facebook:onSuccess:$loginResult")
                        handleFacebookAccessToken(loginResult.accessToken)
                    }

                    override fun onCancel() {
                        Toast.makeText(
                            applicationContext, "onCancel",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onError(exception: FacebookException) {
                        Toast.makeText(
                            applicationContext, "onError",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

        }

        //Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("791377592301-b917u0ao2vet14kldtcqmhqn5a651un8.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        loginGoogle.setOnClickListener {
            signIn()
        }


        // Configure twitter Sign In
        loginTwitter.setOnClickListener {

            provider = OAuthProvider.newBuilder("twitter.com")
            provider.addCustomParameter("lang", "fr")


            val pendingResultTask = firebaseAuth.pendingAuthResult
            if (pendingResultTask != null) {
                // There's something already here! Finish the sign-in for your user.
                pendingResultTask
                    .addOnSuccessListener(
                        OnSuccessListener {
                            checkUser()
                            Toast.makeText(
                                applicationContext, "SignIn Twitter Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                    .addOnFailureListener {
                        Toast.makeText(
                            applicationContext, it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                firebaseAuth
                    .startActivityForSignInWithProvider( /* activity= */this, provider.build())
                    .addOnSuccessListener {
                        checkUser()
                        Toast.makeText(
                            applicationContext, "SignIn Twitter Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            applicationContext, it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }

        }


        tabLayout.addTab(tabLayout.newTab().setText("تسجيل الدخول"))
        tabLayout.addTab(tabLayout.newTab().setText("التسجيل"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = LoginAdapter(supportFragmentManager, tabLayout.tabCount, this)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(tabLayout)
        )
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })


        loginFacebook.animate().translationY(0f).setDuration(1000).setStartDelay(400)
            .start()
        loginGoogle.animate().translationY(0f).setDuration(1000).setStartDelay(600).start()
        loginTwitter.animate().translationY(0f).setDuration(1000).setStartDelay(800).start()


    }


    private fun checkUser() {
        //check user LoggedIn or not
        val user = firebaseAuth.currentUser
        if (user != null) {
            //user already LoggedIn
            startActivity(Intent(this, HomeeActivity::class.java))
            //finish()
        } else {

            Toast.makeText(
                baseContext, R.string.please_SignIn,
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    override fun onBackPressed() {
        System.exit(0)
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        //facebook
        callbackManager.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d(TAG, "firebaseAuthWithGoogle:${account.id}")
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed
                    Log.d(TAG, "Google sign in failed")

                }
            } else {
                Log.d(TAG, exception.toString())

            }

        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    //  startActivity(Intent(applicationContext, HomeeActivity::class.java))
                    checkUser()
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "sign in Successfully")
                    checkUser()
                } else {

                    Log.d(TAG, "sign in with credential: failure")

                    Toast.makeText(
                        applicationContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    override fun onStop() {
        super.onStop()
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }


}