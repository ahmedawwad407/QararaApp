package com.example.qarapp.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlinesoppinggaza.Adapter.MyRecyclerAdaptercarts
import com.example.onlinesoppinggaza.models.MyModel
import com.example.qarapp.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register_complaint.*

class RegisterComplaint : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_complaint)
        val show = ArrayList<MyModel>()
        db.collection("complaints").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val id = document.id
                        val data = document.data
                        val address = data["address"] as String?
                        val name = data["name"] as String?
                        val id_num = data["id_num"] as String?
                        val mobile_num = data["mobile_num"] as String?
                        val region = data["region"] as String?
                        val nearest_address = data["nearest_address"] as String?
                        val title_description = data["title_description"] as String?
                        val category = data["category"] as String?
                        val complaint_text = data["complaint_text"] as String?
                        show.add(
                            MyModel(
                                id,
                                name,
                                mobile_num,
                                address,
                                id_num,
                                region,
                                nearest_address,
                                title_description,
                                category,
                                complaint_text
                            )
                        )
                    }
                    val adapter = MyRecyclerAdaptercarts(this, show)
                    show_all_users.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    show_all_users.adapter = adapter
                }
            }
    }
}