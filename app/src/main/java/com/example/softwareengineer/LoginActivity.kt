package com.example.softwareengineer

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private var isPasswordVisible = false  // Track password visibility state

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val ivTogglePassword = findViewById<ImageView>(R.id.ivTogglePassword)

        // ✅ Toggle Password Visibility
        ivTogglePassword.setOnClickListener {
            togglePasswordVisibility(etPassword, ivTogglePassword)
        }

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username == "admin" && password == "test123") {
                // ✅ Correct login → Go to StudentActivity
                val intent = Intent(this, StudentActivity::class.java)
                startActivity(intent)
                finish()  // Close login screen
            } else {
                // ❌ Wrong credentials
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ✅ Function to Toggle Password Visibility
    private fun togglePasswordVisibility(etPassword: EditText, ivTogglePassword: ImageView) {
        if (isPasswordVisible) {
            // Hide Password
            etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            ivTogglePassword.setImageResource(R.drawable.ic_visibility_off)
        } else {
            // Show Password
            etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            ivTogglePassword.setImageResource(R.drawable.ic_visibility)
        }
        isPasswordVisible = !isPasswordVisible

        // Keep cursor at the end after toggling
        etPassword.setSelection(etPassword.text.length)
    }
}
