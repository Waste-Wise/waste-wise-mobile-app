package com.example.wastewise

data class LoginResponse(
    val success: Boolean,
    val token: String,
    val refreshToken: String
)
