package com.example.andorid_professional_dev_course.data.MainScreenData.retrofit

import com.example.andorid_professional_dev_course.data.MainScreenData.ResultDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInt {
    @GET("/api/v1/dicservice.json/getLangs")
    fun getLanguages(
        @Query("key") apiKey: String
    ): Call<List<String>>

    @GET("/api/v1/dicservice.json/lookup")
    fun getTranslation(
        @Query("key") apiKey: String,
        @Query("lang") lang: String,
        @Query("text") text: String
    ):Call<ResultDTO>
}