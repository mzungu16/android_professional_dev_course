package com.example.andorid_professional_dev_course.ui.mainScreen

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andorid_professional_dev_course.databinding.ActivityMainBinding
import com.example.andorid_professional_dev_course.domain.ProjectUsecase
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainScreenAdapter = MainScreenAdapter()
    private val usecase: ProjectUsecase.MainScreenUsecase by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(
            this,
            MainScreenViewModel(usecase)
        ).get(MainScreenViewModel::class.java)

        viewModel.showLanguages()

        viewModel.spinnerList.observe(this) {
            binding.spinnerList.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, it)
        }

        binding.translateBtn.setOnClickListener {
            viewModel.showTranslation(
                binding.spinnerList.selectedItem.toString(),
                binding.editText.text.toString()
            )
            viewModel.resultDTO.observe(this) {
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
}