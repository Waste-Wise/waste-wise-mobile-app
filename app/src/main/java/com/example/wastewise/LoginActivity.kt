package com.example.wastewise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.wastewise.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    lateinit var username : EditText
    lateinit var password: EditText
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.notRegisteredTV.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            val mobileNumber = binding.username.text.toString()
            val password = binding.password.text.toString()

            // Call API for authentication
            login(mobileNumber, password)
        }

    }

    private fun login(mobileNumber: String, password: String) {
        val url = "https://waste-wise-backend.vercel.app/api/v1/auth/driver/login"

        val requestBody = JSONObject()
            .put("mobileNumber", mobileNumber as Any)
            .put("password", password)
            .toString()
            .toRequestBody("application/json".toMediaType())


        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                val jsonResponse = JSONObject(responseData)

                withContext(Dispatchers.Main) {
                    if (jsonResponse.getBoolean("success")) {
                        val token = jsonResponse.getString("token")
                        // Login successful, navigate to HomeActivity
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        intent.putExtra("token", token)
                        startActivity(intent)
                        finish() // Close login activity
                    } else {
                        // Login failed, show error message
                        Toast.makeText(
                            this@LoginActivity,
                            "Login Failed, Try Again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    // Handle network error
                    Toast.makeText(
                        this@LoginActivity,
                        "Network error. Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun ShowHidePass(view: View) {
        if (view.id == R.id.show_pass_btn) {

            if (binding.password.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
                (view as ImageView).setImageResource(R.drawable.ic_visibility_on)
                // Show Password
                binding.password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                (view as ImageView).setImageResource(R.drawable.ic_visibility_off)
                // Hide Password
                binding.password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

}