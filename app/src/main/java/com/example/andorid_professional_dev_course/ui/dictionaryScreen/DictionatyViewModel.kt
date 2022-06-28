package com.example.andorid_professional_dev_course.ui.dictionaryScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.andorid_professional_dev_course.data.database.WordTableEntity
import com.example.andorid_professional_dev_course.domain.ProjectUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DictionaryViewModel(val usecase: ProjectUsecase.DictionaryUsecase) : ViewModel(),
    ViewModelProvider.Factory {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val _listOfWord = MutableLiveData<List<WordTableEntity>>()
    val listOfWord: LiveData<List<WordTableEntity>>
        get() = _listOfWord

    fun getWords() {
        scope.launch {
            usecase.data.flow.collect {
                _listOfWord.postValue(it)
            }
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DictionaryViewModel(usecase) as T
    }
}