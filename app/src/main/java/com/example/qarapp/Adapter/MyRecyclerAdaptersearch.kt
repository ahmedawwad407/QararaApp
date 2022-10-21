package com.example.qarapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qarapp.Admin.CounterReadScreenAdmin
import com.example.qarapp.R
import com.example.qarapp.models.MyModel2
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.item_payment.view.*
import kotlinx.android.synthetic.main.layout_nodata.*

class MyRecyclerAdaptersearch(val context: Context, val list: ArrayList<MyModel2>) :
        RecyclerView.Adapter<MyRecyclerAdaptersearch.ViewHolder>() {
    var db = FirebaseFirestore.getInstance()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var tvProductName = item.tvPaymentGateway
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_payment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvProductName.text = list[position].name
        holder.tvProductName.setOnClickListener {
            var intent = Intent(context, CounterReadScreenAdmin::class.java)
            intent.putExtra("id", list[position].id)
            intent.putExtra("name", list[position].name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}