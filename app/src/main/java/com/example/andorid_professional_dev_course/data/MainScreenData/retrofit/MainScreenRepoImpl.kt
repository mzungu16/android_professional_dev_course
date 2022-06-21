package com.example.andorid_professional_dev_course.data.MainScreenData.retrofit

import android.util.Log
import com.example.andorid_professional_dev_course.data.MainScreenData.ResultDTO
import com.example.andorid_professional_dev_course.domain.RepositoryInt
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainScreenRepoImpl(private val api: RetrofitInt) : RepositoryInt.MainScreenRepo {
    private val key =
        "dict.1.1.20220613T083359Z.fcd4b0897bfdb28c.6f755fcc2b10cf9e97feb39fa83657ef2263a98a"

    override fun languages(): Observable<List<String>> {
        return Observable.create { subscriber ->
            api.getLanguages(key)
                .enqueue(object : Callback<List<String>> {
                    override fun onResponse(
                        call: Call<List<String>>,
                        response: Response<List<String>>
                    ) {
                        response.body()?.let {
                            subscriber.onNext(it)
                        }
                    }

                    override fun onFailure(call: Call<List<String>>, t: Throwable) {
                        t.message?.let { Log.d("TAG", it) }
                    }
                })
        }
    }

    override fun getTranslation(lang: String, text: String): Observable<ResultDTO> {
        return Observable.create { subscriber2 ->
            api.getTranslation(key, lang, text).enqueue(object : Callback<ResultDTO> {
                override fun onResponse(call: Call<ResultDTO>, response: Response<ResultDTO>) {
                    response.body()?.let {
                        subscriber2.onNext(it)
                    }
                }

                override fun onFailure(call: Call<ResultDTO>, t: Throwable) {
                    t.message?.let { Log.d("TAG", it) }
                }

            })
        }
    }
}