package com.example.andorid_professional_dev_course.ui.mainScreen

import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.andorid_professional_dev_course.R
import com.example.andorid_professional_dev_course.data.MainScreenData.MainScreenRepoImpl
import com.example.andorid_professional_dev_course.domain.Contracts
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainScreenPresenter(val mainScreenRepo: MainScreenRepoImpl) : Contracts.MainScreenPresenter {
    private var activity: MainActivity? = null

    //    private lateinit var spinnerAdapter: ArrayAdapter<String>
    override fun attach(paramActivity: MainActivity) {
        activity = paramActivity
    }
/*
    override fun showLanguages() {
//        val spinner = activity?.findViewById<Spinner>(R.id.spinner_list)
        *//*var spinnerAdapter = activity?.applicationContext?.let {
            ArrayAdapter(
                it, android.R.layout.simple_spinner_dropdown_item,
                listOf<String>()
            )
        }*//*
        mainScreenRepo.languages().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

            }
//        spinner?.adapter = spinnerAdapter
    }*/

    override fun transferResult(callBack: (List<String>) -> Unit) {
        mainScreenRepo.languages().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                callBack(it)
            }
    }

    override fun detach() {
        activity = null
    }
}