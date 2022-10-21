package com.example.qarapp.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qarapp.Adapter.MyRecyclerAdapterList2
import com.example.qarapp.R
import com.example.qarapp.models.MyModel3
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_bill_management.*

class Bill_Management : AppCompatActivity() {
    private lateinit var toogle: ActionBarDrawerToggle
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var toolbar: Toolbar
    lateinit var db: FirebaseFirestore
    var id = ""
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_management)
        db = Firebase.firestore
        firebaseAuth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        id = intent.getStringExtra("id")!!
        var name = intent.getStringExtra("name")!!

        ivasad.setText(name)
        val productdata = ArrayList<MyModel3>()
        db!!.collection("partnership_users")
            .whereEqualTo("partnership_name", ivasad.text.toString())
            .get()//.whereEqualTo("name", name)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val id = document.id
                        val data = document.data
                        val partnership_Date = data["partnership_Date"] as String?
                        val consumption_amount = data["consumption_amount"] as Number?
                        val partnership_name = data["partnership_name"] as String?
                        val paid = data["paid"] as Number?
                        productdata.add(
                            MyModel3(
                                id,
                                partnership_Date,
                                consumption_amount.toString(),
                                paid.toString(),
                                partnership_name.toString()
                            )
                        )
                    }
                    var adapter = MyRecyclerAdapterList2(this, productdata)
                    rvPaymentGateways.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    rvPaymentGateways.adapter = adapter
                }
            }



        ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}