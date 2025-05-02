package com.gfg.recyclerview_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R

class PatientAdapter (private val patienList:List<Patient>):RecyclerView.Adapter<PatientAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_patient,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return patienList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val  patientItem=patienList[position]
        holder.patientName.text=patientItem.toString()
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientName: TextView = itemView.findViewById(R.id.PatientName)
    }


}