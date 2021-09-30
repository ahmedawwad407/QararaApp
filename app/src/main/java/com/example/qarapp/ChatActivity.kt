package com.example.qarapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.firebase.client.ChildEventListener
import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import java.util.*

class ChatActivity : AppCompatActivity() {

    var layout: LinearLayout? = null
    var sendButton: ImageView? = null
    var messageArea: EditText? = null
    var scrollView: ScrollView? = null
    var reference1: Firebase? = null
    var reference2: Firebase? = null
    var time2: Date? = null
    var tv_hint: TextView? = null
    var time22: Long = 0

    companion object {
        const val SHARED_PREF_NAME = "sharedpreferance"
        const val STORAGE_MINUTE = "sharedtime"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        layout = findViewById<View>(R.id.layout1) as LinearLayout
        sendButton = findViewById<View>(R.id.sendButton) as ImageView
        messageArea = findViewById<View>(R.id.messageArea) as EditText
        scrollView = findViewById<View>(R.id.scrollView) as ScrollView
        tv_hint = findViewById(R.id.tv_message)


        Firebase.setAndroidContext(this)


        reference1 =
            Firebase("https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/messages/" + FormDetails.id + "_" + FormDetails.chatWith)
        reference2 =
            Firebase("https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/messages/" + FormDetails.chatWith + "_" + FormDetails.id)
        val intent = intent
        val text = intent.getStringExtra("text")
        val id = intent.getStringExtra("id")
        val id2 = intent.getStringExtra("id")
        val map: MutableMap<String, String?> = HashMap()
        map["message"] = text
        map["id"] = id

        val time = Calendar.getInstance().time
        messageArea!!.visibility = View.GONE
        val currenttime = time.time
        Log.d("current time: ", currenttime.toString() + "")
        val a: Long = 1800000 // 30 minute

        val newtime = currenttime + a

        time2 = Calendar.getInstance().time
        time22 = time2!!.getTime()

        Thread {
            while (time22 <= newtime) {
                if (newtime == time22) {
                    runOnUiThread {
                        tv_hint!!.setVisibility(View.GONE)
                        messageArea!!.visibility = View.VISIBLE
                        sendButton!!.visibility = View.VISIBLE
                    }
                }
                Log.d("time", "$time22  ***  $newtime")
                time2 = Calendar.getInstance().time
                time22 = time2!!.getTime()
            }
        }.start()


        reference1!!.child(id2).push().setValue(map)
        reference2!!.push().setValue(map)

        sendButton!!.setOnClickListener {
            val messageText = messageArea!!.text.toString()
            if (messageText != "") {
                val map: MutableMap<String, String> =
                    HashMap()
                map["message"] = messageText
                map["id"] = id!!
                reference1!!.child(id2).push().setValue(map)
                reference2!!.push().setValue(map)
            }
        }

        reference1!!.child(id2).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String) {
                val map = dataSnapshot.getValue(Map::class.java)
                val dataStored = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).getBoolean(
                    "dataStored",
                    false
                )
                if (!dataStored) {
                    getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE).edit()
                        .putBoolean("dataStored", true).apply()
                    val message = map["message"].toString()
                    val id = map["id"].toString()
                    if (id == id) {
                        addMessageBox("You :\n$message", 1)
                    } else {
                        addMessageBox(
                            """${FormDetails.chatWith} :
$message""", 2
                        )
                    }
                }
                val text = map["message"].toString()
                val id = map["id"].toString()
                if (id == id) {
                    addMessageBox("you:\n$text", 1)
                }
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String) {}
            override fun onCancelled(firebaseError: FirebaseError) {}
        })
    }


    fun addMessageBox(message: String?, type: Int) {
        val textView = TextView(this@ChatActivity)
        textView.text = message
        val lp = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(0, 0, 0, 10)
        textView.layoutParams = lp
        if (type == 1) {
            textView.setTextColor(Color.WHITE)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)
            textView.setBackgroundResource(R.drawable.rounded_corner1)
        } else {
            textView.setTextColor(Color.BLACK)
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)
            textView.setBackgroundResource(R.drawable.rounded_corner2)
        }
        layout!!.addView(textView)
        scrollView!!.fullScroll(View.FOCUS_DOWN)
    }
}