package com.example.andorid_professional_dev_course.ui.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.andorid_professional_dev_course.data.MainScreenData.ResultDTO
import com.example.andorid_professional_dev_course.domain.ProjectUsecase
import com.example.mylibrary.Printer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
    }

    fun showTranslation(lang: String, text: String) {
        scope.launch {
            withContext(Dispatchers.Main) {
                usecase.data.lang = lang
                usecase.data.text = text
                usecase.data.flow2.collect {
                    it.enqueue(object : Callback<ResultDTO> {
                        override fun onResponse(
                            call: Call<ResultDTO>,
                            response: Response<ResultDTO>
                        ) {
                            if (response.isSuccessful) {
                                _resultDTO.postValue(response.body())
                            } else {
                                Printer.print(response.errorBody().toString())
                            }
                        }

                        override fun onFailure(call: Call<ResultDTO>, t: Throwable) {
                            Printer.print(t.printStackTrace().toString())
                        }

                    })
                }
            }
        }
    }

    fun insertWord(word: ResultDTO) {
        usecase.data.insertWordToDB(word)
    }
}