package com.example.courseapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.courseapp.data.local.dao.FavoriteCourseDao
import com.example.courseapp.data.local.entity.CourseEntity

@Database(
    entities = [CourseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CourseDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteCourseDao
}