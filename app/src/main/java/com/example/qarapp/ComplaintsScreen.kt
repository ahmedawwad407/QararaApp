package com.example.qarapp

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.facebook.login.LoginManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import org.json.JSONException
import org.json.JSONObject
import java.text.FieldPosition
import java.util.*


class ComplaintsScreen : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var toogle: ActionBarDrawerToggle
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var toolbar: Toolbar
    var et_id: EditText? = null
    var et_phone:EditText? = null
    var et_title:EditText? = null
    var et_description:EditText? = null
    var et_text:EditText? = null
    var sp_near: Spinner? = null
    var sp_area:Spinner? = null
    var sp_category:Spinner? = null
    var sp_type:Spinner? = null
    var btn_sent: Button? = null
    var id: String? = null
    var phone:String? = null
    var title:String? = null
    var description:String? = null
    var text:String? = null
    var near:String? = null
    var area:String? = null
    var category:String? = null
    var type:String? = null

    var all = ArrayList<String>()
    var totalUsers = 0
    var random = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaints_screen)

        random = Random().nextInt(9000)
        inflate()
        configureToolbar()
        com.firebase.client.Firebase.setAndroidContext(this)

        //init FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        sp_area!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (sp_area!!.selectedItem == "منطقة 86") {
                    val arrayList = ArrayList<String>()
                    arrayList.add("مسجد التوحيد")
                    arrayList.add("مسجد الرضوان")
                    arrayList.add("مسجد بلال بن رباح")
                    arrayList.add("مدرسة المعري")
                    arrayList.add("روضة الأشبال النموذجية")
                    val arrayAdapter = ArrayAdapter(
                        baseContext, android.R.layout.simple_spinner_item, arrayList
                    )
                    sp_near!!.adapter = arrayAdapter
                } else if (sp_area!!.selectedItem == "السريج") {
                    val arrayList = ArrayList<String>()
                    arrayList.add("مسجد الرحمن")
                    arrayList.add("نادي القرارة الرياضي")
                    arrayList.add("مدرسة كامل الأغا")
                    arrayList.add("مدرسة النور")
                    arrayList.add("روضة المروة")
                    arrayList.add("مسجد عمر بن الخطاب")
                    arrayList.add("مسجد أبو بكر الصديق")
                    val arrayAdapter = ArrayAdapter(
                        baseContext, android.R.layout.simple_spinner_item, arrayList
                    )
                    sp_near!!.adapter = arrayAdapter
                } else if (sp_area!!.selectedItem == "أل عبد الغفور و فياض") {
                    val arrayList = ArrayList<String>()
                    arrayList.add("عيادة الزنة")
                    arrayList.add("مدرسة محمد بن صالح ابن عثمين")
                    arrayList.add("مسجد أسامة بن زيد")
                    arrayList.add("مجلس العلمي للدعوة السلفية")
                    val arrayAdapter = ArrayAdapter(
                        baseContext, android.R.layout.simple_spinner_item, arrayList
                    )
                    sp_near!!.adapter = arrayAdapter
                } else if (sp_area!!.selectedItem == "منطقة العبادلة") {
                    val arrayList = ArrayList<String>()
                    arrayList.add("مسجد كامل الأسطل")
                    arrayList.add("روضة العلماء الصغار")
                    arrayList.add("مسجد الهدى")
                    arrayList.add("مسجد المحسنين")
                    arrayList.add("مسجد الاستقامة")
                    arrayList.add("روضة الكوثر")
                    arrayList.add("جمعية الانسان التنموية")
                    arrayList.add("مدرسة القرارة")
                    arrayList.add("مسجد الصحابة")
                    arrayList.add("جمعية دار الكتاب و السنة")
                    arrayList.add("مسجد خالد بن الوليد")
                    arrayList.add("مسجد السلام")
                    arrayList.add("عيادة القرارة")
                    arrayList.add("مصلى عمر بن الخطاب")
                    arrayList.add("مدرسة عيلبون")
                    arrayList.add("روضة القدس النموذجية")
                    arrayList.add("عيادة مسقط")
                    arrayList.add("مدرسة مسقط")
                    val arrayAdapter = ArrayAdapter(
                        baseContext, android.R.layout.simple_spinner_item, arrayList
                    )
                    sp_near!!.adapter = arrayAdapter
                } else if (sp_area!!.selectedItem == "منطقة الغربية") {
                    val arrayList = ArrayList<String>()
                    arrayList.add("مدرسة الوكالة الإبتدائية")
                    arrayList.add("مركز شرطة القرارة")
                    arrayList.add("مدرسة الوكالة الإعدادية")
                    arrayList.add("المطاحن الفلسطينية")
                    val arrayAdapter = ArrayAdapter(
                        baseContext, android.R.layout.simple_spinner_item, arrayList
                    )
                    sp_near!!.adapter = arrayAdapter
                } else {
                    val arrayList = ArrayList<String>()
                    val arrayAdapter = ArrayAdapter(
                        baseContext, android.R.layout.simple_spinner_item, arrayList
                    )
                    sp_near!!.adapter = arrayAdapter
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        sp_category!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (sp_category!!.selectedItem == "قسم المياه والصرف الصحي") {
                    val arrayList = ArrayList<String>()
                    arrayList.add("وصلات غير شرعية")
                    arrayList.add("اهدار مياه")
                    arrayList.add("استفسار مياه")
                    arrayList.add("انفجار خط مياه")
                    arrayList.add("ملوحة مياه")
                    arrayList.add("سرقة مياه")
                    arrayList.add("انقطاع مياه")
                    arrayList.add("منهل مفتوح")
                    arrayList.add("توصيل خطوط الصرف الصحي")
                    arrayList.add("مصافي مياه أمطار")
                    arrayList.add("مشاريع صرف صحي")
                    arrayList.add("طفح صرف صحي")
                    val arrayAdapter = ArrayAdapter(
                        baseContext, android.R.layout.simple_spinner_item, arrayList
                    )
                    sp_type!!.adapter = arrayAdapter
                } else if (sp_category!!.selectedItem == "قسم التنظيم والتخطيط") {
                    val arrayList = ArrayList<String>()
                    arrayList.add("ترميم مباني")
                    arrayList.add("حرفة بدون ترخيص")
                    arrayList.add("اشغال رصيف")
                    arrayList.add("بناء بدون ترخيص")
                    arrayList.add("رسوم تنظيم")
                    arrayList.add("بناء لإيل للسقوط")
                    arrayList.add("التسجيل لحصة أسمنت")
                    arrayList.add("البسطات العشوائية")
                    arrayList.add("تعديات")
                    arrayList.add("ازعاج مكبرات صوت")
                    val arrayAdapter = ArrayAdapter(
                        baseContext, android.R.layout.simple_spinner_item, arrayList
                    )
                    sp_type!!.adapter = arrayAdapter
                } else if (sp_category!!.selectedItem == "قسم المالية") {
                    val arrayList = ArrayList<String>()
                    arrayList.add("فواتير خدمات")
                    arrayList.add("إشكالات مالية")
                    arrayList.add("فواتير عامة")
                    arrayList.add("تركيب يافظة")
                    arrayList.add("قراءة عداد مياه")
                    arrayList.add("مواقف البلدية للسيارات")
                    arrayList.add("استفسار عن اليافطات")
                    val arrayAdapter = ArrayAdapter(
                        baseContext, android.R.layout.simple_spinner_item, arrayList
                    )
                    sp_type!!.adapter = arrayAdapter
                } else if (sp_category!!.selectedItem == "الشؤون الإدارية") {
                    val arrayList = ArrayList<String>()
                    arrayList.add("طلب تطوع")
                    arrayList.add("طلب توظيف")
                    arrayList.add("مخالفات موظفين")
                    arrayList.add("استفسار عن وظيفة")
                    val arrayAdapter = ArrayAdapter(
                        baseContext, android.R.layout.simple_spinner_item, arrayList
                    )
                    sp_type!!.adapter = arrayAdapter
                } else if (sp_category!!.selectedItem == "قسم الصيانة") {
                    val arrayList = ArrayList<String>()
                    arrayList.add("عمل مطبات")
                    arrayList.add("خلع بلاط")
                    arrayList.add("رصف شارع")
                    arrayList.add("حفر طرق")
                    arrayList.add("استفسار عن مشاريع طرق")
                    arrayList.add("حفرة شارع")
                    arrayList.add("انارة شوارع")
                    arrayList.add("اصلاح فانوس")
                    arrayList.add("تركيب فانوس جديد")
                    arrayList.add("كلاب ضالة")
                    arrayList.add("موالدات كهربائية")
                    arrayList.add("طلب حاويات")
                    arrayList.add("عما نظافة تصنيع")
                    arrayList.add("انتشار بعوض")
                    arrayList.add("انتشار قوارص")
                    arrayList.add("قطع أشجار")
                    arrayList.add("تشجير")
                    val arrayAdapter = ArrayAdapter(
                        baseContext, android.R.layout.simple_spinner_item, arrayList
                    )
                    sp_type!!.adapter = arrayAdapter
                } else if (sp_category!!.selectedItem == "شكوى عامة") {
                    val arrayList = ArrayList<String>()
                    arrayList.add("طلب الغاء معاملة")
                    arrayList.add("طلب افادة")
                    arrayList.add("مقترحات تطويرية")
                    arrayList.add("طلب تعويضات")
                    arrayList.add("استفسار عن قوانين و أنظمة")
                    arrayList.add("استفسار عن حجز قاعة")
                    arrayList.add("استفسار عن ايجار مكان")
                    val arrayAdapter = ArrayAdapter(
                        baseContext, android.R.layout.simple_spinner_item, arrayList
                    )
                    sp_type!!.adapter = arrayAdapter
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btn_sent!!.setOnClickListener {
            id = et_id!!.text.toString()
            phone = et_phone!!.text.toString()
            title = et_title!!.text.toString()
            description = et_description!!.text.toString()
            text = et_text!!.text.toString()
            near = sp_near!!.selectedItem.toString()
            area = sp_area!!.selectedItem.toString()
            category = sp_category!!.selectedItem.toString()
            type = sp_type!!.selectedItem.toString()
            val pd = ProgressDialog(this, R.style.MyAlertDialogStyle)
            pd.setMessage("Loading...")
            pd.show()
            val url = "https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/forms.json"
            val request = StringRequest(Request.Method.GET, url, object : Response.Listener<String?>{
                override fun onResponse(response: String?) {
                        val reference =
                            com.firebase.client.Firebase("https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/forms")
                        random = Random().nextInt(10000)
                        Log.d("random2", random.toString() + "")
                        if (response == "null") {
                            if (TextUtils.isEmpty(id)) {
                                et_id!!.error = "id is required!"
                            } else if (TextUtils.isEmpty(phone)) {
                                et_phone!!.error = "phone is required!"
                            } else if (TextUtils.isEmpty(title)) {
                                et_title!!.error = "title is required!"
                            } else if (TextUtils.isEmpty(near)) {
                                (sp_near!!.selectedView as TextView).error = "near is required!"
                            } else if (TextUtils.isEmpty(area)) {
                                (sp_area!!.selectedView as TextView).error = "area is required!"
                            } else if (TextUtils.isEmpty(description)) {
                                et_description!!.error = "description is required!"
                            } else if (TextUtils.isEmpty(category)) {
                                (sp_category!!.selectedView as TextView).error =
                                    "category is required!"
                            } else if (TextUtils.isEmpty(type)) {
                                (sp_type!!.selectedView as TextView).error = "type is required!"
                            } else if (TextUtils.isEmpty(text)) {
                                et_text!!.error = "text is required!"
                            } else {
                                reference.child(random.toString()).child("id").setValue(id)
                                reference.child(random.toString()).child("phone").setValue(phone)
                                reference.child(random.toString()).child("title").setValue(title)
                                reference.child(random.toString()).child("milepost")
                                    .setValue(near)
                                reference.child(random.toString()).child("area").setValue(area)
                                reference.child(random.toString()).child("description")
                                    .setValue(description)
                                reference.child(random.toString()).child("category")
                                    .setValue(category)
                                reference.child(random.toString()).child("type").setValue(type)
                                reference.child(random.toString()).child("text").setValue(text)
                                chat()
                            }
                            //                            startActivity(new Intent(SentFormActivity.this, ChatActivity.class));
                        } else {
                            try {
                                val obj = JSONObject(response)
                                if (TextUtils.isEmpty(id)) {
                                    et_id!!.error = "id is required!"
                                } else if (TextUtils.isEmpty(phone)) {
                                    et_phone!!.error = "phone is required!"
                                } else if (TextUtils.isEmpty(title)) {
                                    et_title!!.error = "title is required!"
                                } else if (TextUtils.isEmpty(near)) {
                                    (sp_near!!.selectedView as TextView).error =
                                        "near is required!"
                                } else if (TextUtils.isEmpty(area)) {
                                    (sp_area!!.selectedView as TextView).error =
                                        "area is required!"
                                } else if (TextUtils.isEmpty(description)) {
                                    et_description!!.error = "description is required!"
                                } else if (TextUtils.isEmpty(category)) {
                                    (sp_category!!.selectedView as TextView).error =
                                        "category is required!"
                                } else if (TextUtils.isEmpty(type)) {
                                    (sp_type!!.selectedView as TextView).error =
                                        "type is required!"
                                } else if (TextUtils.isEmpty(text)) {
                                    et_text!!.error = "text is required!"
                                } else {
                                    reference.child(random.toString()).child("id").setValue(id)
                                    reference.child(random.toString()).child("phone")
                                        .setValue(phone)
                                    reference.child(random.toString()).child("title")
                                        .setValue(title)
                                    reference.child(random.toString()).child("milepost")
                                        .setValue(near)
                                    reference.child(random.toString()).child("area")
                                        .setValue(area)
                                    reference.child(random.toString()).child("description")
                                        .setValue(description)
                                    reference.child(random.toString()).child("category")
                                        .setValue(category)
                                    reference.child(random.toString()).child("type")
                                        .setValue(type)
                                    reference.child(random.toString()).child("text")
                                        .setValue(text)
                                    chat()
                                }
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                        pd.dismiss()
                    }

            }, object : Response.ErrorListener {
                   override fun onErrorResponse(volleyError: VolleyError) {
                        println("" + volleyError)
                        pd.dismiss()
                    }
                })
            val rQueue: RequestQueue = Volley.newRequestQueue(this)
            rQueue.add(request)
        }
        val drawerlayout=findViewById<DrawerLayout>(R.id.drawer_layout)

        toogle = ActionBarDrawerToggle(
            this,
            drawerlayout,
            toolbar,
            R.string.open,
            R.string.close
        )//connection toolbar and Drawer_layout
        toogle.syncState()//create icon borger
        drawerlayout.addDrawerListener(toogle)
        var navigationView = findViewById<NavigationView>(R.id.NavigationView)
        navigationView.setNavigationItemSelectedListener { item_menu ->
            if (item_menu.itemId == R.id.Home) {
                val i = Intent(applicationContext, HomeeActivity::class.java)
                startActivity(i)
                drawerlayout.closeDrawers()//close navigationDrawer
            } else if (item_menu.itemId == R.id.News) {
                val i = Intent(applicationContext, webview1::class.java)
                i.putExtra("link", "https://www.qarara.ps/news10.html")
                startActivity(i)
                drawerlayout.closeDrawers()
            } else if (item_menu.itemId == R.id.VideoScreen) {
                val i = Intent(applicationContext, VideoOffLoading::class.java)
                startActivity(i)
                drawerlayout.closeDrawers()
            } else if (item_menu.itemId == R.id.AboutApp) {
                val i = Intent(applicationContext, AboutScreen::class.java)
                startActivity(i)
                drawerlayout.closeDrawers()
            } else if (item_menu.itemId == R.id.ImageScreen) {
                val i = Intent(applicationContext, ImagesOfNews::class.java)
                startActivity(i)
                drawerlayout.closeDrawers()
            } else if (item_menu.itemId == R.id.logOut) {
                exist_dialog()
                drawerlayout.closeDrawers()
            }
            false
        }


//        var arrayAdapter1 = ArrayAdapter.createFromResource(
//            this,
//            R.array.Spinner1,
//            android.R.layout.simple_spinner_item
//        )
//        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner1.adapter = arrayAdapter1
//        spinner1.onItemSelectedListener
//
//        var arrayAdapter2 = ArrayAdapter.createFromResource(
//            this,
//            R.array.Spinner2,
//            android.R.layout.simple_spinner_item
//        )
//        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner2.adapter = arrayAdapter2
//        spinner2.onItemSelectedListener

    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        var text = parent?.getItemAtPosition(position).toString()
        Toast.makeText(applicationContext,text,Toast.LENGTH_SHORT).show()

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun exist_dialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle("الخروج")
            .setMessage("هل تود الخروج من التطبيق؟")
            .setPositiveButton("نعم") { dialogInterface, i ->
                firebaseAuth.signOut()
                LoginManager.getInstance().logOut()
                checkUser()
            }
            .setNegativeButton("لا") { dialogInterface, i ->
                dialogInterface.cancel()
            }
        val AD = builder.create()
        AD.show()
    }
    private fun checkUser() {
        //check user logIn or Not
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            //user is already logged in
            val email = firebaseUser.email
            Toast.makeText(applicationContext, "loggedIn as $email", Toast.LENGTH_SHORT).show();

        } else {
            //user is not logged in
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

    private fun inflate() {
        toolbar = findViewById(R.id.toolbar)
        et_id = findViewById(R.id.sent_et_id)
        et_phone = findViewById(R.id.sent_et_phone)
        et_title = findViewById(R.id.sent_et_title)
        et_description = findViewById(R.id.sent_et_description)
        et_text = findViewById(R.id.sent_et_text)
        sp_near = findViewById(R.id.sent_sp_near)
        sp_area = findViewById(R.id.sent_sp_area)
        sp_category = findViewById(R.id.sent_sp_category)
        sp_type = findViewById(R.id.sent_sp_type)
        btn_sent = findViewById(R.id.sent_btn_sent)
    }

    private fun configureToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

    }

    fun doOnSuccess(s: String?) {
        try {
            val obj = JSONObject(s)
            val i: Iterator<*> = obj.keys()
            var key = ""
            while (i.hasNext()) {
                key = i.next().toString()
                if (key != FormDetails.id) {
                    all.add(key)
                }
                totalUsers++
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    private fun chat() {
        val url = "https://alqararamunicipality-b276d-default-rtdb.firebaseio.com/users.json"
        val request = StringRequest(Request.Method.GET, url, object : Response.Listener<String?> {
           override fun onResponse(s: String?) {
                doOnSuccess(s)
            }
        }, object : Response.ErrorListener {
            override fun onErrorResponse(volleyError: VolleyError) {
                println("" + volleyError)
            }
        })
        val rQueue: RequestQueue = Volley.newRequestQueue(this)
        rQueue.add(request)
        FormDetails.chatWith = "Admin"
        Log.d("random3", random.toString() + "")
        val intent = Intent(this, ChatActivity::class.java)
            .putExtra("text", et_text!!.text.toString())
            .putExtra("text", et_text!!.text.toString())
            .putExtra("id", et_id!!.text.toString())
        startActivity(intent)
    }
}