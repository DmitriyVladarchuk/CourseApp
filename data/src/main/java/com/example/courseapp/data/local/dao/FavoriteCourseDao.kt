package com.example.courseapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.courseapp.data.local.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCourseDao {
    @Query("SELECT * FROM favorite_courses")
    fun getAllFavorites(): Flow<List<CourseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(courseEntity: CourseEntity)

    @Delete
    suspend fun deleteFavorite(courseEntity: CourseEntity)
}