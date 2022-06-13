package com.example.andorid_professional_dev_course

import android.app.Application
import android.content.Context
import com.example.andorid_professional_dev_course.data.MainScreenData.MainScreenRepoImpl
import com.example.andorid_professional_dev_course.domain.Contracts
import com.example.andorid_professional_dev_course.domain.RepositoryInt
import com.example.andorid_professional_dev_course.ui.mainScreen.MainScreenPresenter

class App : Application() {
    private val mainScreenRepo by lazy { MainScreenRepoImpl() }
    val presenter: Contracts.MainScreenPresenter by lazy { MainScreenPresenter(mainScreenRepo) }
}

val Context.app: App
    get() {
        return applicationContext as App
    }