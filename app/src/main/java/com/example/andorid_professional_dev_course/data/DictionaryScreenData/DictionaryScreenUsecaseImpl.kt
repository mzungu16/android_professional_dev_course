package com.example.andorid_professional_dev_course.data.DictionaryScreenData

import com.example.andorid_professional_dev_course.domain.ProjectUsecase

class DictionaryScreenUsecaseImpl(private val api: DictionaryScreenRepoImpl) :
    ProjectUsecase.DictionaryUsecase {
    override val data: DictionaryScreenRepoImpl
        get() = api
}