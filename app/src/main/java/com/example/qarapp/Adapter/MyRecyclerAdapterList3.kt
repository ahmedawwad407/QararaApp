package com.example.qarapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qarapp.Admin.CounterReadScreenAdmin
import com.example.qarapp.MyBillScreen
import com.example.qarapp.R
import com.example.qarapp.models.MyModel2
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.itemlist.view.*

class MyRecyclerAdapterList3(val context: Context, val list: ArrayList<MyModel2>) :
    RecyclerView.Adapter<MyRecyclerAdapterList3.ViewHolder>() {
    var db = FirebaseFirestore.getInstance()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var tvProductName = item.NameHeading
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.itemlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvProductName.text = list[position].name
        holder.tvProductName.setOnClickListener {
            var intent = Intent(context, MyBillScreen::class.java)
            intent.putExtra("id", list[position].id)
            intent.putExtra("name", list[position].name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}