package com.example.wastewise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NonDisposableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_non_disposable)
    }
}