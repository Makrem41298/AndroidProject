package com.gfg.recyclerview_kotlin

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R

class PatientAdapter(
    private val patientList: List<Patient>,
    private val onItemClick: (Patient) -> Unit // Appelée lors du clic sur le bouton Info
) : RecyclerView.Adapter<PatientAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientName: TextView = itemView.findViewById(R.id.PatientName)
        val infoButton: Button = itemView.findViewById(R.id.btnInfo)
        val callButton: Button = itemView.findViewById(R.id.btnCall)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_patient, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = patientList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val patientItem = patientList[position]
        holder.patientName.text = "${patientItem.firstName} ${patientItem.lastName}"


        holder.callButton.setOnClickListener {
            val phoneNumber = patientItem.phoneNumber.trim()
            val callIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            holder.itemView.context.startActivity(callIntent)
        }

        holder.infoButton.setOnClickListener {
            Log.d("PatientAdapter", "Clicked Info for: ${patientItem.firstName}")
            onItemClick(patientItem)
        }
    }
}
