package com.example.qarapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qarapp.Adapter.MyRecyclerAdapterList3
import com.example.qarapp.models.MyModel2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_complaints_screen.*
import kotlinx.android.synthetic.main.activity_list_my_bill_screen.*
import kotlinx.android.synthetic.main.layout_nodata.*
import java.util.*

class ComplaintsScreen : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    var name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaints_screen)
        db = Firebase.firestore
        auth = Firebase.auth
        db!!.collection("users").whereEqualTo("email", auth.currentUser!!.email).get()
            .addOnSuccessListener { q ->
                name = q.documents.get(0).get("name").toString()
            }


        sent_btn_sent.setOnClickListener {
            add_Complaints(
                sent_et_id.text.toString(),
                sent_et_phone.text.toString(),
                sent_et_title.text.toString(),
                sent_sp_area.selectedItem.toString(),
                sent_sp_near.text.toString(),
                sent_et_description.text.toString(),
                sent_sp_category.selectedItem.toString(),
                sent_et_text.text.toString(),
            )
        }
    }

    private fun add_Complaints(
        address: String?,
        id_num: String?,
        mobile_num: String?,
        region: String?,
        nearest_address: String?,
        title_description: String?,
        category: String?,
        complaint_text: String?
    ) {
        var Complaints = hashMapOf(
            "id_num" to id_num,
            "name" to name,
            "mobile_num" to mobile_num,
            "address" to address,
            "region" to region,
            "nearest_address" to nearest_address,
            "title_description" to title_description,
            "category" to category,
            "complaint_text" to complaint_text,
            "The_Condition" to "",
            "created_at" to com.google.firebase.Timestamp(Date()),
        )
        db.collection("complaints").add(Complaints)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "تم اضافة شكوى ", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { exception ->
            }
    }
}
