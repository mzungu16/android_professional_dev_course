package com.example.andorid_professional_dev_course.data.MainScreenData.retrofit

import com.example.andorid_professional_dev_course.data.MainScreenData.ResultDTO
import com.example.andorid_professional_dev_course.data.MainScreenData.Synonyms
import com.example.andorid_professional_dev_course.data.database.Converter
import com.example.andorid_professional_dev_course.data.database.WordDatabase
import com.example.andorid_professional_dev_course.data.database.WordTableEntity
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Executors

class MainScreenRepoImpl(private val api: RetrofitInt, private val db: WordDatabase) {
    private val key =
        "dict.1.1.20220613T083359Z.fcd4b0897bfdb28c.6f755fcc2b10cf9e97feb39fa83657ef2263a98a"
    var lang: String = ""
    var text: String = ""

    val flow = flow {
        println("TAG ${Thread.currentThread().name}")
        emit(api.getLanguages(key).execute().body())
    }

    val flow2 = flow {
        println("TAG ${Thread.currentThread().name}")
        emit(api.getTranslation(key, lang, text).execute().body())
    }

    fun insertWordToDB(word: ResultDTO) {
        val list = if (word.def.first().tr.first().syn == null) {
            emptyList()
        } else {
            word.def.first().tr.first().syn
        }
        Executors.newSingleThreadExecutor().execute {
            db.getDao().insertWord(
                WordTableEntity(
                    0,
                    word.def.first().tr.first().text,
                    Converter().fromSynonymsToString(list),
                    word.def.first().pos
                )
            )
        }
    }
}