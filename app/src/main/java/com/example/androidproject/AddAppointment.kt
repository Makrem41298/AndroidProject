package com.example.androidproject

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class AddAppointment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_appointment)

        val gender = resources.getStringArray(R.array.gender_options)
        val spinner = findViewById<Spinner>(R.id.spinnerGender)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, gender)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        var dateEdt: EditText = findViewById(R.id.idEdtDate)
        var  dateBerth: EditText =findViewById(R.id.etBirthDate)


        dateEdt.setOnClickListener {


            val c = Calendar.getInstance()


            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->

                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    dateEdt.setText(dat)
                },

                year,
                month,
                day
            )

            datePickerDialog.show()
        }
        dateBerth.setOnClickListener {

            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(
                this,
                { view, year, monthOfYear, dayOfMonth ->

                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    dateBerth.setText(dat)
                },

                year,
                month,
                day
            )

            datePickerDialog.show()
        }

        val btnBackAppointment = findViewById<Button>(R.id.btnBack)
        btnBackAppointment.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("targetFragment", "appointment")
            startActivity(intent)
            finish()
        }

    }
}
