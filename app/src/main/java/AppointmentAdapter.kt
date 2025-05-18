package com.gfg.recyclerview_kotlin

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import java.time.format.DateTimeFormatter

class AppointmentAdapter(private val appointmentList: List<Appointment>) :
    RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    inner class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTimeTextView: TextView = itemView.findViewById(R.id.tvDateTime)
        val statusTextView: TextView = itemView.findViewById(R.id.tvStatus)
        val patientIdTextView: TextView = itemView.findViewById(R.id.tvPatientId)
        val consultationIdTextView: TextView = itemView.findViewById(R.id.tvConsultationId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appointment, parent, false)
        return AppointmentViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointmentList[position]
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm")
        val dateTime = appointment.dateTime?.format(formatter) ?: "No date set"



        holder.dateTimeTextView.text = dateTime
        holder.statusTextView.text = (appointment.status ?: "Unknown").toString()
        holder.patientIdTextView.text = "Patient ID: ${appointment.patientId}"
        holder.consultationIdTextView.text = "Consultation ID: ${appointment.consultation_id}"
    }

    override fun getItemCount() = appointmentList.size
}
