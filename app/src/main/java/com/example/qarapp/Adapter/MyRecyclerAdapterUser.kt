package com.example.qarapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qarapp.R
import com.example.qarapp.models.MyModelUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.item_payment.view.*
import kotlinx.android.synthetic.main.layout_paymentdetail.view.*

class MyRecyclerAdapterUser(val context: Context, val list: ArrayList<MyModelUser>) :
    RecyclerView.Adapter<MyRecyclerAdapterUser.ViewHolder>() {
    var db = FirebaseFirestore.getInstance()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun getIsInTheMiddle(): Boolean {
            return true
        }
        var name = item.tvPaymentGateway
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.item_payment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.name.setOnClickListener {
//            var intent = Intent(context, Show_Daily::class.java)
//            intent.putExtra("Id", list[position].id)
//            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}