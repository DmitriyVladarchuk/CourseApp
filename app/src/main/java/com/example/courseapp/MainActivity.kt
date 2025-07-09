package com.example.courseapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.courseapp.domain.usecase.GetCoursesUseCase
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val getCoursesUseCase: GetCoursesUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Проверка работы GetCoursesUseCase
        lifecycleScope.launch {
            getCoursesUseCase().collect { courses ->
                Log.d("MainActivity", "Received ${courses.size} courses")
                courses.forEach { course ->
                    Log.d("MainActivity", "Course: ${course.title}, Price: ${course.price}")
                }
            }
        }
    }
}