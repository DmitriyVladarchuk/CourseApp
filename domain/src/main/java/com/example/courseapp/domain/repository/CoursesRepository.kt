package com.example.courseapp.domain.repository

import com.example.courseapp.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    suspend fun getCourses(): Flow<List<Course>>
    suspend fun getFavorites(): Flow<List<Course>>
    suspend fun addFavorite(course: Course)
    suspend fun removeFavorite(course: Course)
}