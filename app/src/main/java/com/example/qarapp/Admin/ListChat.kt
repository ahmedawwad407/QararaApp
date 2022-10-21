package com.example.qarapp.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qarapp.Adapter.MyRecyclerAdapterchat
import com.example.qarapp.R
import com.example.qarapp.models.MyModelUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_list_chat.*

class ListChat : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_chat)
            val show = ArrayList<MyModelUser>()
            db.collection("users").get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            val id = document.id
                            val data = document.data
                            val address = data["name"] as String?
                            show.add(
                                MyModelUser(
                                    id,
                                    address
                                )
                            )
                        }
                        val adapter = MyRecyclerAdapterchat(this, show)
                        recyclerrecycler.layoutManager =
                            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                        recyclerrecycler.adapter = adapter
                    }
                }
    }
}