package com.example.wastewise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wastewise.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.cardView1.setOnClickListener {

            val intent = Intent(this, DisposableActivity::class.java)
            startActivity(intent)

        }

        // Click listener for the second card view
        binding.cardView2.setOnClickListener {

            val intent = Intent(this, NonDisposableActivity::class.java)
            startActivity(intent)
        }


    }
}

