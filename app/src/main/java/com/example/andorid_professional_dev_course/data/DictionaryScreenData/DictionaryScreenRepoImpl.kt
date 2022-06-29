package com.example.andorid_professional_dev_course.data.DictionaryScreenData

import com.example.andorid_professional_dev_course.data.database.WordDatabase
import kotlinx.coroutines.flow.flow

class DictionaryScreenRepoImpl(private val db: WordDatabase) {
    val flow = flow {
        emit(db.getDao().getAllStudents())
    }
}