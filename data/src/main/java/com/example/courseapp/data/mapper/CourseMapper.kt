package com.example.courseapp.data.mapper

import com.example.courseapp.data.local.entity.CourseEntity
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

fun CourseEntity.toDomain(): Course = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate
)

fun Course.toEntity(): CourseEntity = CourseEntity(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate
)