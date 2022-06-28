package com.example.andorid_professional_dev_course.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.andorid_professional_dev_course.R
import com.example.andorid_professional_dev_course.databinding.ActivityMainBinding
import com.example.andorid_professional_dev_course.ui.dictionaryScreen.DictionaryFragment
import com.example.andorid_professional_dev_course.ui.mainScreen.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, MainFragment())
            .commit()

        binding.bottonNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.translate -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MainFragment())
                        .commit()
                }
                R.id.dictionary -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, DictionaryFragment())
                        .commit()
                }
            }
            true
        }
    }
}