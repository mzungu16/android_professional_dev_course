package com.example.andorid_professional_dev_course.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface RepositoryInt {
    interface MainScreenRepo {
        fun languages(): Observable<List<String>>
    }
}