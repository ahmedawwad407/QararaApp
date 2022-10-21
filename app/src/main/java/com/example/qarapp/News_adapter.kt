/*
package com.example.listveiw1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qarapp.R


class News_adapter : RecyclerView.Adapter<News_adapter.Recycl_holder> {
    var layoutid: Int = 0
    lateinit var datalist: ArrayList<UserAccount>
    lateinit var context: Context

    constructor(layoutid: Int, array: ArrayList<UserAccount>, context: Context) {
        this.layoutid = layoutid
        this.datalist = array
        this.context = context

    }


    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Recycl_holder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layoutid, parent, false)
        return Recycl_holder(view)
    }

    override fun onBindViewHolder(holder: Recycl_holder, position: Int) {

        val account = datalist.get(position)//return News
        holder.imageViewEmail.setImageResource(account.photo)
        holder.textEmail.setText(account.textEmail)
        holder.textName.setText(account.textName)
    }

    inner class Recycl_holder : RecyclerView.ViewHolder {
        lateinit var imageViewEmail: ImageView
        lateinit var textName: TextView
        lateinit var textEmail: TextView

        constructor(itemView: View) : super(itemView) {
            //imageViewEmail = itemView.findViewById<ImageView>(R.id.imageViewEmail)
           // textName = itemView.findViewById<TextView>(R.id.textName)
          //  textEmail = itemView.findViewById<TextView>(R.id.textEmail)
        }
    }
}

*/
