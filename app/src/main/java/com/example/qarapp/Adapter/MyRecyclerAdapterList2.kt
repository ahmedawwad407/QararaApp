package com.example.qarapp.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.qarapp.R
import com.example.qarapp.models.MyModel3
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.financial_record.view.*
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MyRecyclerAdapterList2(val context: Context, val list: ArrayList<MyModel3>) :
    RecyclerView.Adapter<MyRecyclerAdapterList2.ViewHolder>() {
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
        holder.tvProductReviewCmt1.text = list[position].Total_Amount.toString()
        holder.tvProductReviewDuration1.text = list[position].paid.toString()
        holder.tvProductReviewDuration1.text = list[position].date
        var x = list[position].paid.toString().toInt()
        var xx = list[position].Total_Amount.toString().toInt()
        if (x >= xx) {
            holder.tvProductReviewRating12.text = "تم تسديد الفتورة"
            holder.tvProductReviewRating12.setBackgroundResource(R.color.green)
        } else if (xx > x) {
            holder.tvProductReviewRating12.text =   " يوجد باقي من الفاتورة : ${xx - x}"
            holder.tvProductReviewRating12.setBackgroundResource(R.color.cat_3)
        }

        if (x == 0){
            holder.tvProductReviewRating12.text = "لم يتم تسديد الفتورة"
            holder.tvProductReviewRating12.setBackgroundResource(R.color.red)
        }

        holder.tvProductReviewRating12.setOnClickListener {
            val dialog = BottomSheetDialog(context)
            val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet, null)
            val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
            val tvQuantity = view.findViewById<EditText>(R.id.tvQuantity)
            val tvQuantity2 = view.findViewById<Button>(R.id.tvQuantity2)

            tvQuantity2.setOnClickListener {
                var xx = list[position].Total_Amount.toString().toInt()
                var username = list[position].partnership_name.toString()
                var x = tvQuantity.text.toString().toInt()
                var product = HashMap<String, Any>()
                product["paid"] = x
                db.collection("partnership_users").document(list[position].id!!).update(product)
                    .addOnSuccessListener { documentReference ->
                        if (x >= xx) {
                            MotionToast.createToast(
                                context as Activity,
                                "تم تسديد الفتورة",
                                "اكتمل التعديل بنجاح!",
                                MotionToastStyle.SUCCESS,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(context, R.font.helvetica_regular)
                            )
                            add_notifications(username,"تم تسديد الفتورة ","اكتمل تسديد الفتورة بنجاح بقيمة :  ${x}!" )
                        }

                        if (xx > x) {
                            MotionToast.createToast(
                                context as Activity,
                                " يوجد باقي من الفاتورة :  ${xx - x}",
                                "اكتمل التعديل بنجاح!",
                                MotionToastStyle.ERROR,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(context, R.font.helvetica_regular)
                            )
                            add_notifications(username,"تم تسديد الفتورة ", " يوجد باقي من الفاتورة : ${xx - x}")
                        }
                    }.addOnFailureListener { exception ->
                    }
                dialog.dismiss()
            }
            btnClose.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun add_notifications(
        name: String?,
        title: String?,
        topic: String?,
    ) {
        var Complaints = hashMapOf(
            "name" to name,
            "title" to title,
            "topic" to topic,
            "created_at" to com.google.firebase.Timestamp(Date()),
        )
        db.collection("notifications").add(Complaints)
            .addOnSuccessListener { documentReference ->
            }.addOnFailureListener { exception ->
            }
    }
}