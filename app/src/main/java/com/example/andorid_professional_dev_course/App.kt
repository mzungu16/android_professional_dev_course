package com.example.andorid_professional_dev_course

import android.app.Application
import com.example.andorid_professional_dev_course.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(appModule)
        }
    }
}

//    private val mainScreenRepo by lazy { MainScreenRepoImpl() }
//    val usecase: ProjectUsecase.MainScreenUsecase by lazy { MainScreenUsecaseImpl(mainScreenRepo) }
//}
//
//val Context.app: App
//    get() {
//        return applicationContext as App
//    }