package com.example.andorid_professional_dev_course.domain

import com.example.andorid_professional_dev_course.ui.mainScreen.MainActivity

interface Contracts {
    interface MainScreenContracts{
        fun showData(list:List<String>)
    }
    interface MainScreenPresenter{
        fun attach(mainActivity: MainActivity)
        fun transferResult(callBack: (List<String>) -> Unit)
        fun detach()
    }
}