package com.example.androidproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnLogin: Button = findViewById(R.id.BtnLogin)

        btnLogin.setOnClickListener{
            val email = findViewById<EditText>(R.id.InputEmail).text.toString()
            val password = findViewById<EditText>(R.id.InputPassword).text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()){
                Intent(this,MainActivity::class.java).apply {
                    putExtra("Email", email)
                    putExtra("Password", email)}
                    startActivity(intent)
                    finish()


                 }else{
                    Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}