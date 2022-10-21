package com.resocoder.firemessage.recyclerview.item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qarapp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.resocoder.firemessage.model.TextMessage
import kotlinx.android.synthetic.main.activity_chat2.*
import kotlinx.android.synthetic.main.item_text_message.view.*
import java.util.*
import kotlin.collections.ArrayList

class TextMessageItem(val context: Context, val list: ArrayList<TextMessage>) :
    RecyclerView.Adapter<TextMessageItem.ViewHolder>() {
    var db = FirebaseFirestore.getInstance()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun getIsInTheMiddle(): Boolean {
            return true
        }

        var name = item.textView_message_text
        var time = item.textView_message_time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.item_text_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].text
        holder.time.text = Calendar.getInstance().time.toString()

    }

    override fun getItemCount(): Int {
        return list.size
    }
}