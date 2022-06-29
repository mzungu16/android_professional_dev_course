package com.example.andorid_professional_dev_course.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "word_table")
data class WordTableEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "translation") val translation: String,
    @TypeConverters(Converter::class)
    @ColumnInfo(name = "synonyms") val synonyms: String,
    @ColumnInfo(name = "pos") val pos: String,
)