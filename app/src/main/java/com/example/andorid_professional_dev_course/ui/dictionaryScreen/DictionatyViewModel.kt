package com.example.andorid_professional_dev_course.ui.dictionaryScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.andorid_professional_dev_course.domain.ProjectUsecase

class DictionaryViewModel(val usecase: ProjectUsecase.DictionaryUsecase) : ViewModel(),
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DictionaryViewModel(usecase) as T
    }
}