package com.example.onlinesoppinggaza.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinesoppinggaza.models.MyModel
import com.example.qarapp.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.layout_paymentdetail.view.*
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MyRecyclerAdaptercarts(val context: Context, val list: ArrayList<MyModel>) :
    RecyclerView.Adapter<MyRecyclerAdaptercarts.ViewHolder>() {
    var db = FirebaseFirestore.getInstance()

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var Heading = item.Heading
        var tvOffer22 = item.tvOffer22// رقم الهوية
        var tvOffer = item.tvOffer// رقم الجوال
        var tvOffer2 = item.tvOffer2//نص شكوى
        var tvShippingCharge = item.tvShippingCharge// المنطقة
        var tvShippingCharge2 = item.tvShippingCharge2// اقرب معلم
        var tvTotalAmount = item.tvTotalAmount//تصنيف
        var button5 = item.button5// انتظار المراجع
        var button7 = item.button7// متوقف مؤقتًا
        var button8 = item.button8//مرفوض

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.layout_paymentdetail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvOffer.text = list[position].mobile_num
        holder.tvOffer22.text = list[position].id_num
        holder.Heading.text = list[position].address
        holder.tvOffer2.text = list[position].complaint_text
        holder.tvShippingCharge2.text = list[position].nearest_address
        holder.tvShippingCharge.text = list[position].region
        holder.tvTotalAmount.text = list[position].category

        holder.button5.setOnClickListener {
            var product = HashMap<String, Any>()
            product["The_Condition"] = "في انتظار المراجعة"
            db.collection("complaints").document(list[position].id!!).update(product)
                .addOnSuccessListener { documentReference ->
                    MotionToast.createToast(
                        context as Activity,
                        "في انتظار المراجعة ",
                        "اكتمل التعديل بنجاح!",
                        MotionToastStyle.SUCCESS,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(context, R.font.helvetica_regular)
                    )
                    add_notifications(list[position].name,"في انتظار المراجعة","في انتظار المراجعة ")
                }.addOnFailureListener { exception ->
                }
        }
        holder.button7.setOnClickListener {
            var product = HashMap<String, Any>()
            product["The_Condition"] = "متوقف مؤقتًا"
            db.collection("complaints").document(list[position].id!!).update(product)
                .addOnSuccessListener { documentReference ->
                    MotionToast.createToast(
                        context as Activity,
                        "متوقف مؤقتًا!!",
                        "اكتمل التعديل بنجاح!",
                        MotionToastStyle.WARNING,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(context, R.font.helvetica_regular)
                    )
                    add_notifications(list[position].name,"متوقف مؤقتًا!!","متوقف مؤقتًا!!")

                }.addOnFailureListener { exception ->
                }
        }

        holder.button8.setOnClickListener {
            val dialog = BottomSheetDialog(context)
            val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheets, null)
            val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)
            val tvQuantity = view.findViewById<TextView>(R.id.tvQuantity)
            val tvQuantity2 = view.findViewById<TextView>(R.id.tvQuantity2)
            val tvQuantity4 = view.findViewById<TextView>(R.id.tvQuantity4)

            tvQuantity.setOnClickListener {
            var product = HashMap<String, Any>()
            product["The_Condition"] = "مرفوض"
            db.collection("complaints").document(list[position].id!!).update(product)
                .addOnSuccessListener { documentReference ->
                    MotionToast.createToast(
                        context as Activity,
                        "مرفوض ",
                        "اكتمل التعديل بنجاح!",
                        MotionToastStyle.ERROR,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(context, R.font.helvetica_regular)
                    )
                    add_notifications(list[position].name,"مرفوض ","عدم اكتمال طلب شكوى")
                }.addOnFailureListener { exception ->
                }
                dialog.dismiss()
            }

            tvQuantity2.setOnClickListener {
                var product = HashMap<String, Any>()
                product["The_Condition"] = "مرفوض"
                db.collection("complaints").document(list[position].id!!).update(product)
                    .addOnSuccessListener { documentReference ->
                        MotionToast.createToast(
                            context as Activity,
                            "مرفوض ",
                            "اكتمل التعديل بنجاح!",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, R.font.helvetica_regular)
                        )
                        add_notifications(list[position].name,"مرفوض ","عدم وضوح نص شكوى")
                    }.addOnFailureListener { exception ->
                    }
                dialog.dismiss()
            }

            tvQuantity4.setOnClickListener {
                var product = HashMap<String, Any>()
                product["The_Condition"] = "مرفوض"
                db.collection("complaints").document(list[position].id!!).update(product)
                    .addOnSuccessListener { documentReference ->
                        MotionToast.createToast(
                            context as Activity,
                            "مرفوض ",
                            "اكتمل التعديل بنجاح!",
                            MotionToastStyle.ERROR,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(context, R.font.helvetica_regular)
                        )
                        add_notifications(list[position].name,"مرفوض ","عدم توفر الامكانيات الازمة لحل شكوى")
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