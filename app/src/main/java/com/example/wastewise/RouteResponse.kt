package com.example.wastewise

import com.google.gson.annotations.SerializedName

data class RoutesResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: List<Route>
)

data class Route(
    @SerializedName("_id") val id: String,
    @SerializedName("route_name") val routeName: String,
    @SerializedName("route_distance") val routeDistance: String
)