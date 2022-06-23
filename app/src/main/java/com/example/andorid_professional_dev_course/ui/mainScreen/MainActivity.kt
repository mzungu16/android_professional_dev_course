package com.example.andorid_professional_dev_course.ui.mainScreen

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andorid_professional_dev_course.data.MainScreenData.ResultDTO
import com.example.andorid_professional_dev_course.data.database.WordDatabase
import com.example.andorid_professional_dev_course.databinding.ActivityMainBinding
import com.example.andorid_professional_dev_course.domain.ProjectUsecase
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainScreenAdapter = MainScreenAdapter()
    private val usecase: ProjectUsecase.MainScreenUsecase by inject(named("MainScreenUsecaseImpl"))
    private val db: WordDatabase by inject(named("WordDatabase"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(
            this,
            MainScreenViewModel(usecase)
        ).get(MainScreenViewModel::class.java)
        lateinit var resultDTO: ResultDTO

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
                resultDTO = it
                if (it.def.first().tr.first().syn == null) {
                    mainScreenAdapter.list = emptyList()
                } else {
                    it.def.first().tr.first().syn?.let { listSyn ->
                        mainScreenAdapter.list = listSyn
                    }
                }
                binding.insideLayout.inside.visibility = View.VISIBLE
                binding.insideLayout.translateResult.text = it.def.first().tr.first().text
                binding.insideLayout.posResult.text = it.def.first().pos
                binding.insideLayout.recyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = mainScreenAdapter
                }
            }
        }

        binding.addBtn.setOnClickListener {
            viewModel.insertWord(resultDTO)
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
    }
}