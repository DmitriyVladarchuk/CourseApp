package com.example.courseapp.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.courseapp.domain.model.Course
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class CoursesAdapter(
    private val onFavoriteClick: (Course) -> Unit,
    private val onMoreClick: (Course) -> Unit
) : ListAdapter<Course, RecyclerView.ViewHolder>(CourseDiffCallback()) {

    private val delegatesManager = AdapterDelegatesManager<List<Any>>().apply {
        addDelegate(CourseDelegate(onFavoriteClick, onMoreClick))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(getItemAsList(), position, holder)
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(getItemAsList(), position)
    }

    private fun getItemAsList(): List<Any> = currentList.map { it as Any }
}