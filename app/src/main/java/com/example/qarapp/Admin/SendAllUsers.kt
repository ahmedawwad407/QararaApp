package com.example.qarapp.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.qarapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import com.resocoder.firemessage.model.User
import kotlinx.android.synthetic.main.activity_send_all_users.*
import java.util.HashMap

class SendAllUsers : AppCompatActivity() {
    var firebaseUser: FirebaseUser? = null
    var reference: DatabaseReference? = null
    var userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_all_users)

        imgBack.setOnClickListener {
            onBackPressed()
        }


        firebaseUser = FirebaseAuth.getInstance().currentUser
        reference = FirebaseDatabase.getInstance().getReference("Users")

        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }
        })


//        getUsersList()

//        btnSendMessage.setOnClickListener {
//            var message: String = etMessage.text.toString()
//
//            if (message.isEmpty()) {
//                Toast.makeText(applicationContext, "message is empty", Toast.LENGTH_SHORT).show()
//                etMessage.setText("")
//            } else {
//                Toast.makeText(this,"${reference.toString()}",Toast.LENGTH_SHORT).show()
////                sendMessage(firebaseUser!!.uid, userId, message)
////                etMessage.setText("")
////                topic = "/topics/$userId"
//            }
//        }
    }

    private fun sendMessage(senderId: String, receiverId: String, message: String) {
        var reference: DatabaseReference? = FirebaseDatabase.getInstance().getReference()

        var hashMap: HashMap<String, String> = HashMap()
        hashMap.put("senderId", senderId)
        hashMap.put("receiverId", receiverId)
        hashMap.put("message", message)

        reference!!.child("Chat").push().setValue(hashMap)

    }



    fun getUsersList() {
        val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        var userid = firebase.uid
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/$userid")
        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                val currentUser = snapshot.getValue(User::class.java)
                if (currentUser!!.profileImage == "") {
                    imgProfile.setImageResource(R.drawable.profile_image)
                } else {
                    Glide.with(this@SendAllUsers).load(currentUser.profileImage).into(imgProfile)
                }

                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val user = dataSnapShot.getValue(User::class.java)
                    if (!user!!.userId.equals(firebase.uid)) {
                        userList.add(user)
//                        userList.get(0)
                        Log.e("key",user!!.userId)
                    }
                }
            }
        })
    }

}