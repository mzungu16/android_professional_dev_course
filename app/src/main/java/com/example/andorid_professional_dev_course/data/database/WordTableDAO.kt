package com.example.andorid_professional_dev_course.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WordTableDAO {
    @Query("SELECT * FROM word_table")
    fun getAllStudents(): List<WordTableEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: WordTableEntity)
}