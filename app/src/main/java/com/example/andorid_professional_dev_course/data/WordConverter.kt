package com.example.andorid_professional_dev_course.data

import com.example.andorid_professional_dev_course.data.MainScreenData.Synonyms
import com.example.andorid_professional_dev_course.data.MainScreenData.Translations

data class WordConverter(
    val translations: String?,
    val synonyms: List<Synonyms>?,
    val pos: String?
)