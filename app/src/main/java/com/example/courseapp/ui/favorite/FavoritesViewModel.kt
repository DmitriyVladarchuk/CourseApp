package com.example.courseapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseapp.domain.model.Course
import com.example.courseapp.domain.usecase.GetFavoritesUseCase
import com.example.courseapp.domain.usecase.ToggleFavoriteCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val toggleFavoriteCoursesUseCase: ToggleFavoriteCoursesUseCase
) : ViewModel() {

    private val _coursesState = MutableStateFlow<List<Course>>(emptyList())
    val coursesState: StateFlow<List<Course>> = _coursesState

    init {
        loadCourses()
    }

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            toggleFavoriteCoursesUseCase(course)
        }
    }

    private fun loadCourses() {
        viewModelScope.launch {
            getFavoritesUseCase().collectLatest { courses ->
                _coursesState.value = courses
            }
        }
    }
}