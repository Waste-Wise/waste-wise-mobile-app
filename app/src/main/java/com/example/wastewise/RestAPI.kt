package com.example.wastewise

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import com.example.wastewise.LoginResponse

class RestAPI {
    private val client = OkHttpClient()

    companion object {
        private const val BASE_URL = "https://waste-wise-backend.vercel.app/"
        private const val LOGIN_ENDPOINT = "api/v1/auth/driver/login"
        private const val LOCATION_ENDPOINT = "api/v1/location"
    }

    fun login(mobileNumber: String, password: String): LoginResponse? {
        val requestBody = JSONObject().apply {
            put("mobileNumber", mobileNumber)
            put("password", password)
        }.toString().toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(BASE_URL + LOGIN_ENDPOINT)
            .post(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return null

            val responseData = response.body?.string() ?: return null
            return parseLoginResponse(responseData)
        }
    }
//    fun sendLocation(latitude: Double, longitude: Double): Boolean {
//        val requestBody = JSONObject().apply {
//            put("latitude", latitude)
//            put("longitude", longitude)
//        }.toString().toRequestBody("application/json".toMediaTypeOrNull())
//
//        val request = Request.Builder()
//            .url(BASE_URL + LOCATION_ENDPOINT)
//            .post(requestBody)
//            .build()
//
//        client.newCall(request).execute().use { response ->
//            return response.isSuccessful
//        }
//    }


    private fun parseLoginResponse(responseData: String): LoginResponse {
        val jsonResponse = JSONObject(responseData)
        val decodedTokenJson = jsonResponse.getJSONObject("decoded_token")
        Log.i("DecodedToken", "DecodedTokenJson: $decodedTokenJson")
        val tokenDecoded = TokenDecoded(
            _id = decodedTokenJson.getString("_id"),
            role = decodedTokenJson.getString("role"),
            name = decodedTokenJson.getString("name"),
            email = decodedTokenJson.getString("email"),
            isVerified = decodedTokenJson.getBoolean("isVerified"),
            iat = decodedTokenJson.getLong("iat"),
            exp = decodedTokenJson.getLong("exp"),
            assignedVehicle = decodedTokenJson.getString("assignedVehicle")
        )



        return LoginResponse(
            success = jsonResponse.getBoolean("success"),
            token = jsonResponse.getString("token"),
            refreshToken = jsonResponse.getString("refresh_token"),
            tokenDecoded = tokenDecoded
        )
    }

}
