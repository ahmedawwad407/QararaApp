package com.example.qarapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qarapp.Adapter.MyRecyclerAdapterList3
import com.example.qarapp.models.MyModel2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_list_my_bill_screen.*
import kotlinx.android.synthetic.main.layout_nodata.*

class ListMyBillScreen : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_my_bill_screen)
        var name = ""

        llSeePri.setOnClickListener {
            startActivity(Intent(this, RegisterMyBillScreen::class.java))
        }
        ivBack.setOnClickListener {
            onBackPressed()
        }
        auth = Firebase.auth
        db!!.collection("users").whereEqualTo("email", auth.currentUser!!.email).get()
            .addOnSuccessListener { q ->
                name = q.documents.get(0).get("name").toString()
                ivasad.setText(name)

                val productdata = ArrayList<MyModel2>()
                db!!.collection("partnership_users")
                    .whereEqualTo("partnership_name", name)
                    .get()//.whereEqualTo("name", name)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            for (document in task.result!!) {
                                val id = document.id
                                val data = document.data
                                val partnership_Date = data["partnership_Date"] as String?
                                productdata.add(MyModel2(id, partnership_Date))
                            }
                            if (productdata.size >= 1) {
                                rlNoData.isVisible = false
                            }
                            var adapter = MyRecyclerAdapterList3(this, productdata)
                            RecyclerView.isVisible = true
                            RecyclerView.layoutManager =
                                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                            RecyclerView.adapter = adapter
                        }
                    }
            }
    }
}