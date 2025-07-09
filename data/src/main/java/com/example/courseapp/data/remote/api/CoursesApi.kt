package com.example.courseapp.data.remote.api

import com.example.courseapp.data.remote.model.CoursesResponse
import retrofit2.http.GET

interface CoursesApi {
    @GET("courses.json")
    suspend fun getCourses(): CoursesResponse
}