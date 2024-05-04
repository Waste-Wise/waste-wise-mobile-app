package com.example.wastewise

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.wastewise.ApiService2


class RoutesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterClass
    private val routesList: ArrayList<Route> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routes)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = AdapterClass(this, routesList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchRoutes()
    }

    private fun fetchRoutes() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://waste-wise-backend.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService2::class.java)
        val call = apiService.getRoutes()

        call.enqueue(object : Callback<RoutesResponse> {
            override fun onResponse(call: Call<RoutesResponse>, response: Response<RoutesResponse>) {
                if (response.isSuccessful) {
                    val routesResponse = response.body()
                    routesResponse?.data?.let {
                        routesList.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(this@RoutesActivity, "Failed to fetch routes", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RoutesResponse>, t: Throwable) {
                // Handle failure
                Toast.makeText(this@RoutesActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
