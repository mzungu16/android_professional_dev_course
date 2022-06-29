package com.example.andorid_professional_dev_course.data.MainScreenData.retrofit

import kotlinx.coroutines.flow.flow

class MainScreenRepoImpl(private val api: RetrofitInt) {
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
}