package com.dicoding.nikhoyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var dbHelper = Database(this)

        // Find views dari layout
        val usernameEditText = findViewById<TextInputEditText>(R.id.usernameInputi)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordInputi)
        val loginButton = findViewById<Button>(R.id.logini)

        loginButton.setOnClickListener {
            val username = usernameEditText.text?.toString()?.trim() ?: ""
            val password = passwordEditText.text?.toString()?.trim() ?: ""

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (dbHelper.readUser(username, password)) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
            }
        }

        // Menggunakan signup_link dari layout bukan signup_button
//        register.setOnClickListener {
//            startActivity(Intent(this, RegisterActivity::class.java))
//        }
    }
}