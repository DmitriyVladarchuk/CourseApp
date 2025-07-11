package com.example.courseapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.courseapp.R
import com.example.courseapp.databinding.ItemCourseBinding
import com.example.courseapp.domain.model.Course
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class CourseDelegate(
    private val onFavoriteClick: (Course) -> Unit,
    private val onMoreClick: (Course) -> Unit
) : AdapterDelegate<List<Any>>() {

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Course
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding, onFavoriteClick, onMoreClick)
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as CourseViewHolder).bind(items[position] as Course)
    }

    class CourseViewHolder(
        private val binding: ItemCourseBinding,
        private val onFavoriteClick: (Course) -> Unit,
        private val onMoreClick: (Course) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) {
            with(binding) {
                tvTitle.text = course.title
                tvText.text = course.text
                tvPrice.text = "${course.price} ₽"
                tvRate.text = course.rate
                tvStartDate.text = course.startDate

                imageView.setImageResource(
                    if (course.hasLike) R.drawable.ic_favorite_filled
                    else R.drawable.ic_favorite
                )

                imageView.setOnClickListener { onFavoriteClick(course) }
                btnMore.setOnClickListener { onMoreClick(course) }
            }
        }
    }
}