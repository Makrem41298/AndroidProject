package com.example.androidproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gfg.recyclerview_kotlin.Patient
import com.gfg.recyclerview_kotlin.PatientAdapter
import com.gfg.recyclerview_kotlin.PatientApiService
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PatientFragment : Fragment() {

    private lateinit var adapter: PatientAdapter
    private val patientList = mutableListOf<Patient>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_patient, container, false)

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val BASE_URL = "https://gist.githubusercontent.com/" +
                "Makrem41298/37012704c4e7cd1d2a3fa7988b0b67e5/raw/9ad10df9bfced541c81af89fff0a270260194ef8/"

        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val api = retrofit.create(PatientApiService::class.java)

        adapter = PatientAdapter(patientList) { patient ->
            Log.d("PATIENT_CLICK", "Clicked: ${patient.firstName}")
            val intent = Intent(requireContext(), InformationPatientActivity::class.java)
            intent.putExtra("firstName", patient.firstName)
            intent.putExtra("lastName", patient.lastName)
            intent.putExtra("numero", patient.phoneNumber)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        lifecycleScope.launch {
            try {
                val patients = api.getPatients()
                patientList.clear()
                patientList.addAll(patients)
                adapter.notifyDataSetChanged()
            } catch (e: IOException) {
                Log.e("PatientFragment", "Network error: ${e.message}")
            } catch (e: HttpException) {
                Log.e("PatientFragment", "HTTP error: ${e.code()}")
            } catch (e: Exception) {
                Log.e("PatientFragment", "Unexpected error: ${e.message}")
            }
        }
    }
}

class LocalDateAdapter : com.google.gson.JsonDeserializer<LocalDate> {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun deserialize(
        json: com.google.gson.JsonElement,
        typeOfT: java.lang.reflect.Type?,
        context: com.google.gson.JsonDeserializationContext?
    ): LocalDate {
        return LocalDate.parse(json.asString, DateTimeFormatter.ISO_LOCAL_DATE)
    }
}
