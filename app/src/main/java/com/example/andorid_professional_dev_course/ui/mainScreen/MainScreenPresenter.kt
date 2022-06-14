package com.example.andorid_professional_dev_course.ui.mainScreen

import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.andorid_professional_dev_course.R
import com.example.andorid_professional_dev_course.data.MainScreenData.MainScreenRepoImpl
import com.example.andorid_professional_dev_course.data.MainScreenData.ResultDTO
import com.example.andorid_professional_dev_course.domain.Contracts
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainScreenPresenter(val mainScreenRepo: MainScreenRepoImpl) : Contracts.MainScreenPresenter {
    private var activity: MainActivity? = null
    override fun attach(paramActivity: MainActivity) {
        activity = paramActivity
    }

    override fun transferResult(callBack: (List<String>) -> Unit) {
        mainScreenRepo.languages().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                callBack(it)
            }
    }

    override fun translateMethod(lang: String, text: String, callback: (ResultDTO) -> Unit) {
        mainScreenRepo.getTranslation(lang, text).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback(it)
            },
                {
                    it.message
                })
    }

    override fun detach() {
        activity = null
    }
}