package com.example.courseapp.data.mapper

import com.example.courseapp.data.remote.model.CourseResponse
import com.example.courseapp.domain.model.Course

fun CourseResponse.toDomain(): Course = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate
)