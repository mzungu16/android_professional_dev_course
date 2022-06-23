package com.example.andorid_professional_dev_course.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [WordTableEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class WordDatabase : RoomDatabase() {
    abstract fun getDao(): WordTableDAO
}