package com.example.wastewise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OtpPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_otp_page)

        val verifButton = findViewById<Button>(R.id.verifButton)
        verifButton.setOnClickListener {
            // Create intent to start HomeActivity
            val intent = Intent(this, VerifiedSuccActivity::class.java)
            startActivity(intent)
        }

    }
}