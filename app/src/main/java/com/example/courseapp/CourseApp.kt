package com.example.courseapp

import android.app.Application
import com.example.courseapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CourseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CourseApp)
            modules(appModule)
        }
    }
}