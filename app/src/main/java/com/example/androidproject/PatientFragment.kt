package com.example.androidproject

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gfg.recyclerview_kotlin.Gender
import com.gfg.recyclerview_kotlin.Patient
import com.gfg.recyclerview_kotlin.PatientAdapter
import java.time.LocalDate

class PatientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_patient, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val patientList = mutableListOf<Patient>()
        patientList.add(
            Patient(
                firstName = "John",
                lastName = "Doe",
                birthDate = LocalDate.of(1990, 1, 12),
                gender = Gender.MALE // Utilisation correcte de l'enum
            )
        )
        patientList.add(
            Patient(
                firstName = "samah",
                lastName = "Doe",
                birthDate = LocalDate.of(1990, 1, 12),
                gender = Gender.FEMALE
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter =
            PatientAdapter(patientList)  // Assume you have a PatientAdapter class
    }
}