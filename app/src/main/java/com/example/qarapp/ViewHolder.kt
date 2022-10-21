package com.example.qarapp

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


 var view =itemView

    fun setDetails(context: Context,image: String) {
        var  mImage = view.findViewById<ImageView>(R.id.RimageView)
        Picasso.get().load(image).into(mImage)
       var animation = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left)
        itemView.startAnimation(animation)


    }
}

