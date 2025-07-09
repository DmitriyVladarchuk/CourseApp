package com.example.courseapp.domain.usecase

import com.example.courseapp.domain.model.Course
import com.example.courseapp.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow

class GetCoursesUseCase(
    private val coursesRepository: CoursesRepository
) {
    suspend operator fun invoke(): Flow<List<Course>> {
        return coursesRepository.getCourses()
    }
}