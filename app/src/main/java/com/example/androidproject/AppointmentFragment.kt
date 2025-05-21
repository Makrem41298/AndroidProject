package com.example.androidproject

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gfg.recyclerview_kotlin.Appointment
import com.gfg.recyclerview_kotlin.AppointmentAdapter
import com.gfg.recyclerview_kotlin.Status
import java.time.LocalDateTime

class AppointmentFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_appointment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewApontiment)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val appointments = listOf(
            Appointment(1, "makrem abderrazek",  LocalDateTime.now().plusDays(1), Status.accept),
            Appointment(2, "samah ben abdalaha", LocalDateTime.now().plusDays(2), Status.cancel),
            Appointment(3, "mouhamed test",  LocalDateTime.now().plusDays(3), Status.suspend)
        )

        val adapter = AppointmentAdapter(appointments)
        recyclerView.adapter = adapter

        val btnAddAppointment = view.findViewById<Button>(R.id.btnAddAppointment)
        btnAddAppointment.setOnClickListener {
            val intent = Intent(requireContext(), AddAppointment::class.java)
            startActivity(intent)
        }

        return view
    }
}
