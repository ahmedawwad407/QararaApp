package com.example.qarapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qarapp.Adapter.MyRecyclerAdapterList
import com.example.qarapp.models.MyModel3
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register_my_bill_screen.*
import kotlinx.android.synthetic.main.layout_nodata.*

class RegisterMyBillScreen : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_my_bill_screen)
        var name = ""
        auth = Firebase.auth
        db!!.collection("users").whereEqualTo("email", auth.currentUser!!.email).get()
            .addOnSuccessListener { q ->
                name = q.documents.get(0).get("name").toString()
                ivasad.setText(name)
                val productdata = ArrayList<MyModel3>()
                db!!.collection("partnership_users")
                    .whereEqualTo("partnership_name", ivasad.text.toString())
                    .get()//.whereEqualTo("name", name)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            for (document in task.result!!) {
                                val id = document.id
                                val data = document.data
                                val partnership_Date = data["partnership_Date"] as String?
                                val consumption_amount = data["consumption_amount"] as Number?
                                val partnership_name = data["partnership_name"] as String?
                                val paid = data["paid"] as Number?
                                productdata.add(
                                    MyModel3(
                                        id,
                                        partnership_Date,
                                        consumption_amount.toString(),
                                        paid.toString(),
                                        partnership_name.toString()
                                    )
                                )
                            }
                            if (productdata.size >= 1) {
                                rlNoData.isVisible = false
                            }

                            var adapter = MyRecyclerAdapterList(this, productdata)
                            rvPaymentGateways.isVisible = true
                            rvPaymentGateways.layoutManager =
                                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                            rvPaymentGateways.adapter = adapter
                        }
                    }
            }


        ivBack.setOnClickListener {
            onBackPressed()
        }

        llSeePriceDetail.setOnClickListener {
            startActivity(Intent(this, ListMyBillScreen::class.java))
        }

//        tvPayWithPayPal.setOnClickListener {
//            MotionToast.createToast(
//                this,
//                "Hurray success 😍",
//                "Upload Completed successfully!",
//                MotionToastStyle.SUCCESS,
//                MotionToast.GRAVITY_BOTTOM,
//                MotionToast.LONG_DURATION,
//                ResourcesCompat.getFont(this, R.font.helvetica_regular)
//            )
//        }
//
//        tvNetBanking.setOnClickListener {
//            MotionToast.createToast(
//                this,
//                "Failed ☹️",
//                "Profile Update Failed!",
//                MotionToastStyle.ERROR,
//                MotionToast.GRAVITY_BOTTOM,
//                MotionToast.LONG_DURATION,
//                ResourcesCompat.getFont(this, R.font.helvetica_regular)
//            )
//        }
//
//        tvCash.setOnClickListener {
//            MotionToast.createToast(
//                this, "Please fill all the details!",
//                "Profile Update Failed!",
//                MotionToastStyle.WARNING,
//                MotionToast.GRAVITY_BOTTOM,
//                MotionToast.LONG_DURATION,
//                ResourcesCompat.getFont(this, R.font.helvetica_regular)
//            )
//        }


//        MotionToast.createToast(this,"This is information toast!",
//            "Profile Update Failed!",
//            MotionToastStyle.INFO,
//            MotionToast.GRAVITY_BOTTOM,
//            MotionToast.LONG_DURATION,
//            ResourcesCompat.getFont(this,R.font.helvetica_regular))


//        MotionToast.createToast(this,"Delete all history!",
//            "Profile Update Failed!",
//            MotionToastStyle.DELETE,
//            MotionToast.GRAVITY_BOTTOM,
//            MotionToast.LONG_DURATION,
//            ResourcesCompat.getFont(this,R.font.helvetica_regular))


//        rvPaymentGateways
    }
}