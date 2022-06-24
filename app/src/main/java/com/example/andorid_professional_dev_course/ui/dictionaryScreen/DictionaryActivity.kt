package com.example.andorid_professional_dev_course.ui.dictionaryScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.andorid_professional_dev_course.databinding.ActivityDictionaryBinding
import com.example.andorid_professional_dev_course.domain.ProjectUsecase
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class DictionaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDictionaryBinding
    private val usecase: ProjectUsecase.DictionaryUsecase by inject(named("DictionaryScreenUsecaseImpl"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDictionaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(
            this,
            DictionaryViewModel(usecase)
        ).get(DictionaryViewModel::class.java)


    }
}