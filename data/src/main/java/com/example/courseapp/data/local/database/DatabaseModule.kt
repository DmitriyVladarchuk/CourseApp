package com.example.courseapp.data.local.database

import android.content.Context
import androidx.room.Room
import com.example.courseapp.data.local.dao.FavoriteCourseDao

object DatabaseModule {
    fun provideDatabase(context: Context): CourseDatabase {
        return Room.databaseBuilder(
            context,
            CourseDatabase::class.java,
            "course-db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    fun provideFavoriteDao(courseDatabase: CourseDatabase): FavoriteCourseDao {
        return courseDatabase.favoriteDao()
    }
}