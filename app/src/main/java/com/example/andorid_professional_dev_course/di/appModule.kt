package com.example.andorid_professional_dev_course.di

import androidx.room.Room
import com.example.andorid_professional_dev_course.data.DictionaryScreenData.DictionaryScreenRepoImpl
import com.example.andorid_professional_dev_course.data.DictionaryScreenData.DictionaryScreenUsecaseImpl
import com.example.andorid_professional_dev_course.data.MainScreenData.MainScreenUsecaseImpl
import com.example.andorid_professional_dev_course.data.MainScreenData.retrofit.MainScreenRepoImpl
import com.example.andorid_professional_dev_course.data.MainScreenData.retrofit.RetrofitInt
import com.example.andorid_professional_dev_course.data.database.WordDatabase
import com.example.andorid_professional_dev_course.domain.ProjectUsecase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    scope(named("Project_scope")) {
        scoped(named("OkHttpClient")) {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }
        scoped(named("Retrofit")) {
            Retrofit.Builder()
                .baseUrl("https://dictionary.yandex.net")
                .client(get(named("OkHttpClient")))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        scoped(named("RetrofitInt")) {
            get<Retrofit>(named("Retrofit")).create(
                RetrofitInt::class.java
            )
        }
        scoped(named("MainScreenRepoImpl")) {
            MainScreenRepoImpl(
                get(named("RetrofitInt")),
                get(named("WordDatabase"))
            )
        }
        scoped(named("DictionaryScreenRepoImpl")) {
            DictionaryScreenRepoImpl(get(named("WordDatabase")))
        }

        scoped<ProjectUsecase.MainScreenUsecase>(named("MainScreenUsecaseImpl")) {
            MainScreenUsecaseImpl(
                get(named("MainScreenRepoImpl"))
            )
        }

        scoped<ProjectUsecase.DictionaryUsecase>(named("DictionaryScreenUsecaseImpl")) {
            DictionaryScreenUsecaseImpl(
                get(named("DictionaryScreenRepoImpl"))
            )
        }

        scoped(named("WordDatabase")) {
            Room.databaseBuilder(androidContext(), WordDatabase::class.java, "word_table").build()
        }

    }
//    single(qualifier = named("OkHttpClient")) {
//        OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            })
//            .build()
//    }
//    single(qualifier = named("Retrofit")) {
//        Retrofit.Builder()
//            .baseUrl("https://dictionary.yandex.net")
//            .client(get(named("OkHttpClient")))
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//    single<RetrofitInt>(qualifier = named("RetrofitInt")) {
//        get<Retrofit>(named("Retrofit")).create(
//            RetrofitInt::class.java
//        )
//    }
//    single(qualifier = named("MainScreenRepoImpl")) {
//        MainScreenRepoImpl(
//            get(named("RetrofitInt")),
//            get(named("WordDatabase"))
//        )
//    }

//    single(qualifier = named("DictionaryScreenRepoImpl")) {
//        DictionaryScreenRepoImpl(get(named("WordDatabase")))
//    }

//    single<ProjectUsecase.MainScreenUsecase>(qualifier = named("MainScreenUsecaseImpl")) {
//        MainScreenUsecaseImpl(
//            get(named("MainScreenRepoImpl"))
//        )
//    }

//    single<ProjectUsecase.DictionaryUsecase>(qualifier = named("DictionaryScreenUsecaseImpl")) {
//        DictionaryScreenUsecaseImpl(
//            get(named("DictionaryScreenRepoImpl"))
//        )
//    }

//    single(qualifier = named("WordDatabase")) {
//        Room.databaseBuilder(androidContext(), WordDatabase::class.java, "word_table").build()
//    }
}
