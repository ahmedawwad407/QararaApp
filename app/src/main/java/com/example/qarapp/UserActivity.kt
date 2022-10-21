package com.example.qarapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class UserActivity : AppCompatActivity() {
    var usersList: ListView? = null
    var noUsersText: TextView? = null
    var all = ArrayList<String>()
    var totalUsers = 0
    var pd: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        usersList = findViewById<View>(R.id.usersList) as ListView
        noUsersText = findViewById<View>(R.id.noUsersText) as TextView

        pd = ProgressDialog(this, R.style.MyAlertDialogStyle)
        pd!!.setMessage("Loading...")
        pd!!.show()

        val url = "https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/users.json"

        val request = StringRequest(
            Request.Method.GET, url,
            { s -> doOnSuccess(s) }
        ) { volleyError -> println("" + volleyError) }

        val rQueue = Volley.newRequestQueue(this@UserActivity)
        rQueue.add(request)
        if (FormDetails.id.equals("Admin")) {
            usersList!!.onItemClickListener =
                OnItemClickListener { parent, view, position, id ->
                    FormDetails.chatWith = all[position]
                    startActivity(Intent(this@UserActivity, ChatActivity::class.java))
                }
        }
    }
    fun doOnSuccess(s: String?) {
        try {
            val obj = JSONObject(s)
            val i: Iterator<*> = obj.keys()
            var key = ""
            while (i.hasNext()) {
                key = i.next().toString()
                if (key != FormDetails.id) {
                    all.add(key)
                }
                totalUsers++
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        if (totalUsers <= 1) {
            noUsersText!!.visibility = View.VISIBLE
            usersList!!.visibility = View.GONE
        } else {
            noUsersText!!.visibility = View.GONE
            usersList!!.visibility = View.VISIBLE
            usersList!!.adapter = ArrayAdapter(this, R.layout.item_user, all)
        }
        pd!!.dismiss()
    }
}