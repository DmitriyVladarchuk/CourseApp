package com.example.courseapp.domain.usecase

import com.example.courseapp.domain.model.Course
import com.example.courseapp.domain.repository.CoursesRepository

class ToggleFavoriteCoursesUseCase(
    private val coursesRepository: CoursesRepository
) {
    suspend operator fun invoke(course: Course) {
        if (course.hasLike) {
            coursesRepository.removeFavorite(course)
        } else {
            coursesRepository.addFavorite(course)
        }
    }
}