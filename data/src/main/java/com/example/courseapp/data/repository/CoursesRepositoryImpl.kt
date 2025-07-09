package com.example.courseapp.data.repository

import com.example.courseapp.data.local.dao.FavoriteCourseDao
import com.example.courseapp.data.local.entity.CourseEntity
import com.example.courseapp.data.mapper.toDomain
import com.example.courseapp.data.mapper.toEntity
import com.example.courseapp.data.remote.api.CoursesApi
import com.example.courseapp.domain.model.Course
import com.example.courseapp.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class CoursesRepositoryImpl(
    private val api: CoursesApi,
    private val favoriteCourseDao: FavoriteCourseDao
) : CoursesRepository {

    override suspend fun getCourses(): Flow<List<Course>> {
        // Загрузка данных из сети
        val networkFlow = flow {
            val response = api.getCourses()
            val courses = response.courses.map { it.toDomain() }
            emit(courses)
        }

        // Загрузка избранных курсов
        val favoritesFlow = favoriteCourseDao.getAllFavorites().map { favorites ->
            favorites.map(CourseEntity::id)
        }

        // Комбинируем данные
        return networkFlow.combine(favoritesFlow) { courses, favoriteIds ->
            courses.map { course ->
                course.copy(hasLike = favoriteIds.contains(course.id))
            }
        }
    }

    override suspend fun addFavorite(course: Course) {
        favoriteCourseDao.insertFavorite(course.toEntity())
    }

    override suspend fun removeFavorite(course: Course) {
        favoriteCourseDao.deleteFavorite(course.toEntity())
    }
}