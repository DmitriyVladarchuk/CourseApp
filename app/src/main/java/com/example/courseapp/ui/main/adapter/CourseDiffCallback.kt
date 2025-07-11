package com.example.courseapp.ui.main.adapter

import com.example.courseapp.domain.model.Course

class CourseDiffCallback : androidx.recyclerview.widget.DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }
}