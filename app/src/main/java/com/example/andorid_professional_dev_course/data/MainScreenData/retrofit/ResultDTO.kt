package com.example.andorid_professional_dev_course.data.MainScreenData

import android.transition.Transition

data class ResultDTO(
    val def: List<Articles>
)

data class Articles(
    val text: String,
    val pos: String,
    val tr: List<Translations>,
    val mean: List<Meanings>,
    val ex: List<Examples>
)

data class Translations(
    val text: String,
    val syn: List<Synonyms>
)

data class Meanings(
    val text: String
)

data class Examples(
    val text: String,
    val tr: List<Translations>
)

data class Synonyms(
    val text: String,
)