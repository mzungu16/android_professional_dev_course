package com.example.andorid_professional_dev_course.domain

import com.example.andorid_professional_dev_course.data.MainScreenData.retrofit.MainScreenRepoImpl

interface ProjectUsecase {
    interface MainScreenUsecase {
        val data: MainScreenRepoImpl
        val translation: MainScreenRepoImpl
    }
}