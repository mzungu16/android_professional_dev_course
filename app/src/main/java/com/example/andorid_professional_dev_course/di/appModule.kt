package com.example.andorid_professional_dev_course.di

import com.example.andorid_professional_dev_course.data.MainScreenData.MainScreenUsecaseImpl
import com.example.andorid_professional_dev_course.data.MainScreenData.retrofit.MainScreenRepoImpl
import com.example.andorid_professional_dev_course.data.MainScreenData.retrofit.RetrofitInt
import com.example.andorid_professional_dev_course.domain.ProjectUsecase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single(qualifier = named("OkHttpClient")) {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
    single(qualifier = named("Retrofit")) {
        Retrofit.Builder()
            .baseUrl("https://dictionary.yandex.net")
            .client(get(named("OkHttpClient")))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<RetrofitInt>(qualifier = named("RetrofitInt")) {
        get<Retrofit>(named("Retrofit")).create(
            RetrofitInt::class.java
        )
    }
    single(qualifier = named("MainScreenRepoImpl")) { MainScreenRepoImpl(get(named("RetrofitInt"))) }
    single<ProjectUsecase.MainScreenUsecase>(qualifier = named("MainScreenUsecaseImpl")) {
        MainScreenUsecaseImpl(
            get(named("MainScreenRepoImpl"))
        )
    }
}
