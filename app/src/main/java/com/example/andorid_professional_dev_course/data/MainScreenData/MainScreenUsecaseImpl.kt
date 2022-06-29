package com.example.andorid_professional_dev_course.data.MainScreenData

import com.example.andorid_professional_dev_course.data.MainScreenData.retrofit.MainScreenRepoImpl
import com.example.andorid_professional_dev_course.domain.ProjectUsecase

class MainScreenUsecaseImpl(private val api: MainScreenRepoImpl) :
    ProjectUsecase.MainScreenUsecase {
    override val data: MainScreenRepoImpl
        get() = api
}