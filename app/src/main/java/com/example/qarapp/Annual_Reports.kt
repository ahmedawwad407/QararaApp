package com.example.qarapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.example.qarapp.Adapter.MyRecyclerAdapterList3
import com.example.qarapp.models.MyModel2
import com.facebook.login.LoginManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_annual_reports.*
import kotlinx.android.synthetic.main.activity_list_my_bill_screen.*
import kotlinx.android.synthetic.main.activity_my_bill_screen_admin.*
import kotlinx.android.synthetic.main.layout_nodata.*
import java.util.*
import kotlin.collections.ArrayList

class Annual_Reports : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore
    var name = ""
//    var Date = ""
//    var amount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annual_reports)
        db = Firebase.firestore
        auth = Firebase.auth

        val anyChartView = any_chart_view
        anyChartView.setProgressBar(progress_bar)
        val pie = AnyChart.pie()
        val data1: MutableList<DataEntry> = ArrayList()

        db!!.collection("users").whereEqualTo("email", auth.currentUser!!.email).get()
            .addOnSuccessListener { q ->
                name = q.documents.get(0).get("name").toString()
                db!!.collection("partnership_users")
                    .whereEqualTo("partnership_name", name).get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            for (document in task.result!!) {
                                val id = document.id
                                val data = document.data
                                val Month = data["Month"] as String?
                                val consumption_amount = data["consumption_amount"] as Number?
                                data1.add(ValueDataEntry(Month.toString(), consumption_amount.toString().toInt()))
                                pie.data(data1)
                            }
                        }
                    }
            }

        pie.setOnClickListener(object : ListenersInterface.OnClickListener(arrayOf("x", "value")) {
            override fun onClick(event: Event) {
                Toast.makeText(
                    this@Annual_Reports,
                    event.getData().get("x").toString() + ": قيمة الاستهلاك " + event.getData().get("value"),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        pie.title("التقارير السنوية لعام 2022 (بالشيكل)")

        pie.labels().position("outside")

        pie.legend().title().enabled(true)
        pie.legend().title()
            .text("الأشهر")
            .padding(0.0, 0.0, 10.0, 0.0)

        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)
        anyChartView.setChart(pie)

    }
}
//                                data1.add(ValueDataEntry("فبراير", 20))
//                                data1.add(ValueDataEntry(" مارس", 60))
//                                data1.add(ValueDataEntry("أبريل", 260))
//                                data1.add(ValueDataEntry("مايو", 10))
//                                data1.add(ValueDataEntry("يونيو", 600))
//                                data1.add(ValueDataEntry("يوليو", 210))
//                                data1.add(ValueDataEntry("أغسطس", 260))
//                                data1.add(ValueDataEntry("سبتمير", 260))
//                                data1.add(ValueDataEntry("أكتوبر", 260))
//                                data1.add(ValueDataEntry("نوفمبر", 260))
//                                data1.add(ValueDataEntry("ديسبمر", 260))