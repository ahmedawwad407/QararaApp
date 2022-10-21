package com.example.qarapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qarapp.R
import com.example.qarapp.models.MyModel3
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.financial_record.view.*

class MyRecyclerAdapterList(val context: Context, val list: ArrayList<MyModel3>) :
    RecyclerView.Adapter<MyRecyclerAdapterList.ViewHolder>() {
    var db = FirebaseFirestore.getInstance()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var tvProductReviewCmt1 = item.tvProductReviewCmt1
        var tvProductReviewDuration1 = item.tvProductReviewDuration1
        var tvProductReviewRating12 = item.tvProductReviewRating12
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.financial_record, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvProductReviewCmt1.text = list[position].Total_Amount
        holder.tvProductReviewDuration1.text = list[position].date
        holder.tvProductReviewRating12.text = list[position].paid

//        var x = list[position].paid
//        var xx = list[position].Total_Amount
//        if (x!! > xx ) {
//            holder.tvProductReviewRating12.text = "تم تسديد الفتورة"
//            holder.tvProductReviewRating12.setBackgroundResource(R.color.green)
//        } else {
//            holder.tvProductReviewRating12.text = "لم يتم تسديد الفتورة"
//            holder.tvProductReviewRating12.setBackgroundResource(R.color.red)
//        }
//    }


        var x = list[position].paid.toString().toInt()
        var xx = list[position].Total_Amount.toString().toInt()
        if (x >= xx) {
            holder.tvProductReviewRating12.text = "تم تسديد الفتورة"
            holder.tvProductReviewRating12.setBackgroundResource(R.color.green)
        } else if (xx > x) {
            holder.tvProductReviewRating12.text = " يوجد باقي من الفاتورة : ${xx - x}"
            holder.tvProductReviewRating12.setBackgroundResource(R.color.cat_3)
        }

        if (x == 0) {
            holder.tvProductReviewRating12.text = "لم يتم تسديد الفتورة"
            holder.tvProductReviewRating12.setBackgroundResource(R.color.red)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}