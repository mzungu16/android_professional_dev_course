package com.example.andorid_professional_dev_course.ui.mainScreen

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andorid_professional_dev_course.R
import com.example.andorid_professional_dev_course.app
import com.example.andorid_professional_dev_course.databinding.ActivityMainBinding
import com.example.andorid_professional_dev_course.domain.Contracts

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainScreenAdapter = MainScreenAdapter()
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
            ) {
                mainScreenAdapter.list = it.def.first().tr.first().syn
                binding.insideLayout.inside.visibility = View.VISIBLE
                binding.insideLayout.translateResult.text = it.def.first().tr.first().text
                binding.insideLayout.posResult.text = it.def.first().pos
                binding.insideLayout.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = mainScreenAdapter
                }
            }
        }
    }

    override fun onDestroy() {
        app.presenter.detach()
        super.onDestroy()
    }
}