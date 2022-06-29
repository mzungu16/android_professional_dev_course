package com.example.andorid_professional_dev_course.data.database

import androidx.room.TypeConverter
import com.example.andorid_professional_dev_course.data.MainScreenData.Synonyms
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun fromSynonymsToString(value: List<Synonyms>?): String {
        val mutableList: MutableList<String> = mutableListOf()
        value?.forEach {
            mutableList.add(it.text)
        }
        return Gson().toJson(mutableList)
    }

    @TypeConverter
    fun fromJsonToListString(value: String): List<String> {
        return Gson().fromJson(value, Array<String>::class.java).asList()
    }
}