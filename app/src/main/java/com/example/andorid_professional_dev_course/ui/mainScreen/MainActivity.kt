package com.example.andorid_professional_dev_course.ui.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.andorid_professional_dev_course.R
import com.example.andorid_professional_dev_course.app
import com.example.andorid_professional_dev_course.databinding.ActivityMainBinding
import com.example.andorid_professional_dev_course.domain.Contracts

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainList: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        app.presenter.attach(this)
        app.presenter.transferResult {
            binding.spinnerList.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, it)
        }
    }

    override fun onDestroy() {
        app.presenter.detach()
        super.onDestroy()
    }
}