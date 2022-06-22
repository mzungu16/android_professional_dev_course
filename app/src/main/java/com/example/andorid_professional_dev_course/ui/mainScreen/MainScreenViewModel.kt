package com.example.andorid_professional_dev_course.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.andorid_professional_dev_course.data.MainScreenData.ResultDTO
import com.example.andorid_professional_dev_course.domain.ProjectUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainScreenViewModel(private val usecase: ProjectUsecase.MainScreenUsecase) : ViewModel(),
    ViewModelProvider.Factory {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val _resultDTO = MutableLiveData<ResultDTO>()
    private val _spinnerList = MutableLiveData<List<String>>()
    val spinnerList: LiveData<List<String>>
        get() = _spinnerList
    val resultDTO: LiveData<ResultDTO>
        get() = _resultDTO

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel(usecase) as T
    }

    fun showLanguages() {
        scope.launch {
            usecase.data.flow.collect {
                _spinnerList.postValue(it)
            }
        }
        /* usecase.data.scope.launch {
             _spinnerList.postValue(usecase.data.languages())
         }*/
    }

    fun showTranslation(lang: String, text: String) {
        scope.launch {
            usecase.data.lang = lang
            usecase.data.text = text
            usecase.data.flow2.collect {
                _resultDTO.postValue(it)
            }
        }
        /* usecase.data.scope.launch {
             _resultDTO.postValue(usecase.data.getTranslation(lang, text))
         }*/
    }
}