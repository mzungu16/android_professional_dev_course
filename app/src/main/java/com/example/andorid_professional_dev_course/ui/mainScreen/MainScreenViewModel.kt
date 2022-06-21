package com.example.andorid_professional_dev_course.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.andorid_professional_dev_course.data.MainScreenData.ResultDTO
import com.example.andorid_professional_dev_course.domain.ProjectUsecase
import kotlinx.coroutines.launch

class MainScreenViewModel(private val usecase: ProjectUsecase.MainScreenUsecase) : ViewModel(),
    ViewModelProvider.Factory {
    private val _resultDTO = MutableLiveData<ResultDTO>()
    private val _spinnerList = MutableLiveData<List<String>>()
    val spinnerList: LiveData<List<String>> = _spinnerList
    val resultDTO: LiveData<ResultDTO> = _resultDTO

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel(usecase) as T
    }

    fun showLanguages() {
        usecase.data.scope.launch {
            _spinnerList.postValue(usecase.data.languages())
        }
    }

    fun showTranslation(lang: String, text: String) {
        usecase.data.scope.launch {
            _resultDTO.postValue(usecase.data.getTranslation(lang, text))
        }
    }
}