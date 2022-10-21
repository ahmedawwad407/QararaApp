package com.example.qarapp.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qarapp.Adapter.MyRecyclerAdaptersearch
import com.example.qarapp.Adapter.MyRecyclerAdaptersearch2
import com.example.qarapp.Adapter.MyRecyclerAdaptersearch3
import com.example.qarapp.R
import com.example.qarapp.models.MyModel2
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_search_users.*
import kotlinx.android.synthetic.main.layout_nodata.*

class Search_Users : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_users)

        var key = intent.getBooleanExtra("key", false)
        var key2 = intent.getBooleanExtra("key2", false)
        var key3 = intent.getBooleanExtra("key3", false)


        if (key == true) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    productData(newText)
                    pbLoader.isVisible = false
                    return true
                }
            })
        }

        if (key2 == true) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    productData2(newText)
                    pbLoader.isVisible = false
                    return true
                }
            })
        }

        if (key3 == true) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    productData3(newText)
                    pbLoader.isVisible = false
                    return true
                }
            })
        }
    }

    private fun productData(name: String) {
        val productdata = ArrayList<MyModel2>()
        db!!.collection("users").orderBy("name").startAt(name).get()//.whereEqualTo("name", name)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    rlNoData.isVisible = false
                    for (document in task.result!!) {
                        val id = document.id
                        val data = document.data
                        val productName = data["name"] as String?
                        productdata.add(MyModel2(id, productName))
                    }
                    var adapter = MyRecyclerAdaptersearch2(this, productdata)
                    aSearch_rvSearch.isVisible = true
                    aSearch_rvSearch.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    aSearch_rvSearch.adapter = adapter
                }
            }
    }

    private fun productData2(name: String) {
        val productdata = ArrayList<MyModel2>()
        db!!.collection("users").orderBy("name").startAt(name).get()//.whereEqualTo("name", name)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    rlNoData.isVisible = false
                    for (document in task.result!!) {
                        val id = document.id
                        val data = document.data
                        val productName = data["name"] as String?
                        productdata.add(MyModel2(id, productName))
                    }
                    var adapter = MyRecyclerAdaptersearch(this, productdata)
                    aSearch_rvSearch.isVisible = true
                    aSearch_rvSearch.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    aSearch_rvSearch.adapter = adapter
                }
            }
    }

    private fun productData3(name: String) {
        val productdata = ArrayList<MyModel2>()
        db!!.collection("users").orderBy("name").startAt(name).get()//.whereEqualTo("name", name)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    rlNoData.isVisible = false
                    for (document in task.result!!) {
                        val id = document.id
                        val data = document.data
                        val productName = data["name"] as String?
                        productdata.add(MyModel2(id, productName))
                    }
                    var adapter = MyRecyclerAdaptersearch3(this, productdata)
                    aSearch_rvSearch.isVisible = true
                    aSearch_rvSearch.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    aSearch_rvSearch.adapter = adapter
                }
            }
    }
}