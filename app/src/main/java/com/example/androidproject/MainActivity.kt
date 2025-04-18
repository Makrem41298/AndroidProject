package com.example.androidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = binding.bottomNavigationView

        if (savedInstanceState == null) {
            setCurrentFragment(FirstFragment()) // ✅ Now resolves correctly
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.accueil -> setCurrentFragment(FirstFragment())
                R.id.utilisateur->  setCurrentFragment(profilFragment())
                R.id.medicament->setCurrentFragment(MedicamentFragment())
                R.id.parametre->setCurrentFragment(ParametreFragment())
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, fragment)
            .commit()
    }
}