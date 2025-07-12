package com.example.courseapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseapp.domain.model.Course
import com.example.courseapp.domain.usecase.GetCoursesUseCase
import com.example.courseapp.domain.usecase.SortCoursesByDateUseCase
import com.example.courseapp.domain.usecase.ToggleFavoriteCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteCoursesUseCase,
    private val sortCoursesByDateUseCase: SortCoursesByDateUseCase
) : ViewModel() {

    private val _coursesState = MutableStateFlow<List<Course>>(emptyList())
    val coursesState: StateFlow<List<Course>> = _coursesState

    private var isSortedDescending = false
    private var originalCourses = listOf<Course>()

    init {
        loadCourses()
    }

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase(course)
            if (isSortedDescending) {
                loadCourses()
                _coursesState.value = sortCoursesByDateUseCase(_coursesState.value)
            }
        }
    }

    fun toggleSortByDate() {
        viewModelScope.launch {
            isSortedDescending = !isSortedDescending
            if (isSortedDescending) {
                originalCourses = _coursesState.value
                _coursesState.value = sortCoursesByDateUseCase(_coursesState.value)
            } else {
                _coursesState.value = originalCourses
            }
        }
    }

    private fun loadCourses() {
        viewModelScope.launch {
            getCoursesUseCase().collectLatest { courses ->
                originalCourses = courses
                _coursesState.value = if (isSortedDescending) {
                    sortCoursesByDateUseCase(courses)
                } else {
                    courses
                }
            }
        }
    }
}