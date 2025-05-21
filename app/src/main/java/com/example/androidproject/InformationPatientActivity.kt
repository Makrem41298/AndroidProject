package com.example.androidproject

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gfg.recyclerview_kotlin.Gender
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class InformationPatientActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_information_patient)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val sb = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(sb.left, sb.top, sb.right, sb.bottom)
            insets
        }

        val firstName   = intent.getStringExtra("firstName").orEmpty()
        val lastName    = intent.getStringExtra("lastName").orEmpty()
        val numero      = intent.getStringExtra("numero").orEmpty()
        val birthDateStr= intent.getStringExtra("data").orEmpty()
        val genderStr   = intent.getStringExtra("gender").orEmpty()

        val birthDate = runCatching { LocalDate.parse(birthDateStr) }
            .getOrNull()
        val gender = runCatching { Gender.valueOf(genderStr) }
            .getOrNull()

        findViewById<TextView>(R.id.first_name).text   = "Prénom : $firstName"
        findViewById<TextView>(R.id.last_name).text    = "Nom : $lastName"
        findViewById<TextView>(R.id.phone).text        = "Numéro : $numero"
        findViewById<TextView>(R.id.birth_date).text   =
            birthDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                ?: "Date de naissance : inconnue"
        findViewById<TextView>(R.id.gender).text       =
            "Genre : ${gender?.name?.lowercase()?.replaceFirstChar(Char::uppercase) ?: "inconnu"}"

        findViewById<ImageButton>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
