package com.example.courseapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseapp.domain.model.Course
import com.example.courseapp.domain.usecase.GetCoursesUseCase
import com.example.courseapp.domain.usecase.ToggleFavoriteCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel (
    private val getCoursesUseCase: GetCoursesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteCoursesUseCase
) : ViewModel() {

    private val _coursesState = MutableStateFlow<List<Course>>(emptyList())
    val coursesState: StateFlow<List<Course>> = _coursesState

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            getCoursesUseCase().collectLatest { courses ->
                _coursesState.value = courses
            }
        }
    }

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            toggleFavoriteUseCase(course)
        }
    }
}