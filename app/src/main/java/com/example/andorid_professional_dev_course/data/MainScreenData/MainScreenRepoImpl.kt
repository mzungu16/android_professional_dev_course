package com.example.andorid_professional_dev_course.data.MainScreenData

import android.util.Log
import com.example.andorid_professional_dev_course.domain.RepositoryInt
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainScreenRepoImpl : RepositoryInt.MainScreenRepo {
    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dictionary.yandex.net")
        .client(client)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api: RetrofitInt = retrofit.create(RetrofitInt::class.java)
    override fun languages(): Observable<List<String>> {
        return Observable.create { subscriber ->
            api.getLanguages("dict.1.1.20220613T083359Z.fcd4b0897bfdb28c.6f755fcc2b10cf9e97feb39fa83657ef2263a98a")
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


}