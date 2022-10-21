package com.example.qarapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qarapp.Admin.ChatActivity
import com.example.qarapp.R
import com.example.qarapp.models.MyModelUser
import kotlinx.android.synthetic.main.itemlist.view.*

class MyRecyclerAdapterchat(val context: Context, val list: ArrayList<MyModelUser>) :
    RecyclerView.Adapter<MyRecyclerAdapterchat.ViewHolder>() {

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var NameHeading = item.NameHeading
        var tvdetails = item.tvdetails

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.itemlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.NameHeading.text = list[position].name
        holder.tvdetails.setOnClickListener {
            var intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("Id", list[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}