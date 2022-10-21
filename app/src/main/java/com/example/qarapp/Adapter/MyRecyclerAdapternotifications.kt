package com.example.qarapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qarapp.R
import com.example.qarapp.models.MyModel1
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.layout_notification.view.*


class MyRecyclerAdapternotifications(val context: Context, val list: ArrayList<MyModel1>) :
    RecyclerView.Adapter<MyRecyclerAdapternotifications.ViewHolder>() {
    var db = FirebaseFirestore.getInstance()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var title = item.textView11// title
        var address = item.textView12// address
        var button8 = item.textView10//مرفوض
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.layout_notification, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.address.text = list[position].address
    }

    override fun getItemCount(): Int {
        return list.size
    }
}