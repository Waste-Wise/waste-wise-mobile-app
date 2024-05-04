package com.example.wastewise

data class TokenDecoded(
    val _id: String,
    val role: String,
    val name: String,
    val email: String,
    val isVerified: Boolean,
    val iat: Long,
    val exp: Long
)

