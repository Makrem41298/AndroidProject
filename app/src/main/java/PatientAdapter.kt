package com.gfg.recyclerview_kotlin

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R

class PatientAdapter (private val patienList:List<Patient>,     private val onItemClick: (Patient) -> Unit // <- Cette ligne est essentielle
):RecyclerView.Adapter<PatientAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientName: TextView = itemView.findViewById(R.id.PatientName)
        val infoButton: Button = itemView.findViewById(R.id.btnInfo)
        val callButton:Button = itemView.findViewById(R.id.btnCall)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_patient,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return patienList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val  patientItem=patienList[position]
        holder.patientName.text="${patientItem.firstName} ${patientItem.lastName}"
        val patientNumero: TextView = holder.itemView.findViewById(R.id.PatientNumero)
        patientNumero.text = patientItem.numero.toString()
        val phoneText = patientNumero.text.toString().removePrefix("Numéro : ").trim()

        holder.infoButton.setOnClickListener{
            Log.d("PatientAdapter", "Clicked: ${patientItem.firstName}")

            onItemClick(patientItem)
        }

        holder.callButton.setOnClickListener {
            phoneText?.let {
                val callIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$it")
                }
                holder.itemView.context.startActivity(callIntent)
            }
        }

    }




}