package com.example.wastewise

import org.json.JSONObject

data class LoginResponse(
    val success: Boolean,
    val token: String,
    val refreshToken: String,
    val tokenDecoded: TokenDecoded
)
