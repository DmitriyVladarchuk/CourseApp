package com.example.courseapp.data.repository

import com.example.courseapp.data.mapper.toDomain
import com.example.courseapp.data.remote.api.CoursesApi
import com.example.courseapp.domain.model.Course
import com.example.courseapp.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CoursesRepositoryImpl(
    private val api: CoursesApi
) : CoursesRepository {
    override suspend fun getCourses(): Flow<List<Course>> {
        val response = api.getCourses()
        val course = response.courses.map { it.toDomain() }

        return flowOf(course)
    }

    override suspend fun addFavorite(course: Course) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFavorite(course: Course) {
        TODO("Not yet implemented")
    }
}