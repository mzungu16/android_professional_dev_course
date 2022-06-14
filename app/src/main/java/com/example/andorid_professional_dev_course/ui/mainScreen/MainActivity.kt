package com.example.andorid_professional_dev_course.ui.mainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.andorid_professional_dev_course.R
import com.example.andorid_professional_dev_course.app
import com.example.andorid_professional_dev_course.databinding.ActivityMainBinding
import com.example.andorid_professional_dev_course.domain.Contracts

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        app.presenter.attach(this)
        app.presenter.transferResult {
            binding.spinnerList.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, it)
        }
        binding.translateBtn.setOnClickListener {
            app.presenter.translateMethod(
                binding.spinnerList.selectedItem.toString(),
                binding.editText.text.toString()
            ){
                Log.d("TAG",it.def[0].tr[0].text)
                binding.resultText.text = it.def[0].tr[0].text
            }
        }
    }

    override fun onDestroy() {
        app.presenter.detach()
        super.onDestroy()
    }
}