package com.example.wastewise

import retrofit2.Call
import retrofit2.http.GET

interface ApiService2 {
    @GET("/api/v1/branches/6619605fc05f28bf03f8ece9/routes")
    fun getRoutes(): Call<RoutesResponse>
}