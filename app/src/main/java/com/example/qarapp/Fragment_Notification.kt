package com.example.qarapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qarapp.Adapter.MyRecyclerAdapternotifications
import com.example.qarapp.models.MyModel1
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_list_my_bill_screen.*
import kotlinx.android.synthetic.main.fragment__notification.view.*


class Fragment_Notification : Fragment() {
    var db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment__notification, container, false)
        var name = ""
        auth = Firebase.auth
        db!!.collection("users").whereEqualTo("email", auth.currentUser!!.email).get()
            .addOnSuccessListener { q ->
                name = q.documents.get(0).get("name").toString()
                val productdata = ArrayList<MyModel1>()
                db!!.collection("notifications").orderBy("name").startAt(name).get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            for (document in task.result!!) {
                                val id = document.id
                                val data = document.data
                                val productName = data["name"] as String?
                                val title = data["title"] as String?
                                val topic = data["topic"] as String?
                                productdata.add(MyModel1(id, title, topic))
                            }
                            var adapter = MyRecyclerAdapternotifications(v.context, productdata)
                            v.recycle.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            v.recycle.adapter = adapter
                        }
                    }
            }
        return v
    }
}
