package com.example.courseapp.di

import com.example.courseapp.data.local.dao.FavoriteCourseDao
import com.example.courseapp.data.local.database.DatabaseModule
import com.example.courseapp.data.remote.api.CoursesApi
import com.example.courseapp.data.remote.api.RetrofitFactory
import com.example.courseapp.data.repository.CoursesRepositoryImpl
import com.example.courseapp.domain.repository.CoursesRepository
import com.example.courseapp.domain.usecase.GetCoursesUseCase
import com.example.courseapp.domain.usecase.ToggleFavoriteCoursesUseCase
import com.example.courseapp.domain.usecase.ValidateEmailUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    // Retrofit API
    single<CoursesApi> {
        RetrofitFactory.createCoursesApi(context = get())
    }

    // Room
    single<FavoriteCourseDao> {
        val database = DatabaseModule.provideDatabase(androidContext())
        DatabaseModule.provideFavoriteDao(database)
    }

    // Repository
    single<CoursesRepository> {
        CoursesRepositoryImpl(
            api = get(),
            favoriteCourseDao = get()
        )
    }

    // UseCases
    factory { GetCoursesUseCase(coursesRepository = get()) }
    factory { ToggleFavoriteCoursesUseCase(coursesRepository = get()) }
    factory { ValidateEmailUseCase() }
}