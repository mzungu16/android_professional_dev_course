package com.example.andorid_professional_dev_course.data.MainScreenData.retrofit

import android.util.Log
import com.example.andorid_professional_dev_course.data.MainScreenData.ResultDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainScreenRepoImpl(private val api: RetrofitInt) {
    private val key =
        "dict.1.1.20220613T083359Z.fcd4b0897bfdb28c.6f755fcc2b10cf9e97feb39fa83657ef2263a98a"

    //    val scope = CoroutineScope(Dispatchers.IO)
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

    /*suspend fun languages() = suspendCoroutine<List<String>> { continuation ->
        api.getLanguages(key)
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    response.body()?.let {
                        continuation.resume(it)
                    }
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    t.message?.let { Log.d("TAG", it) }
                }
            })
    }*/

    /*suspend fun getTranslation(lang: String, text: String) =
        suspendCoroutine<ResultDTO> { continuation2 ->
            api.getTranslation(key, lang, text).enqueue(object : Callback<ResultDTO> {
                override fun onResponse(call: Call<ResultDTO>, response: Response<ResultDTO>) {
                    response.body()?.let {
                        continuation2.resume(it)
                    }
                }

                override fun onFailure(call: Call<ResultDTO>, t: Throwable) {
                    t.message?.let { Log.d("TAG", it) }
                }

            })
        }*/
}