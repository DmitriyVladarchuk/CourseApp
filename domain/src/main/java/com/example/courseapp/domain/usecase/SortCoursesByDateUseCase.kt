package com.example.courseapp.domain.usecase

import com.example.courseapp.domain.model.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class SortCoursesByDateUseCase {
    suspend operator fun invoke(courses: List<Course>): List<Course> =
        withContext(Dispatchers.Default) {
            try {
                courses.sortedByDescending {
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(it.publishDate)?.time
                        ?: 0L
                }
            } catch (e: Exception) {
                courses
            }
        }
}