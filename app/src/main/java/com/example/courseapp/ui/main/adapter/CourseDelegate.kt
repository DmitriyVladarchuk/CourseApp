package com.example.courseapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.courseapp.R
import com.example.courseapp.databinding.ItemCourseBinding
import com.example.courseapp.domain.model.Course
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import java.text.SimpleDateFormat
import java.util.Locale

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
                customImageView.setImageResource(getCourseImageResource(bindingAdapterPosition))
                tvTitle.text = course.title
                tvText.text = course.text
                tvPrice.text = "${course.price} ₽"
                tvRate.text = course.rate
                tvStartDate.text = formatDate(course.startDate)

                imageView.setImageResource(
                    if (course.hasLike) R.drawable.ic_favorite_filled
                    else R.drawable.ic_favorite
                )

                imageView.setOnClickListener { onFavoriteClick(course) }
                btnMore.setOnClickListener { onMoreClick(course) }
            }
        }

        /**
         * Возвращает ресурс изображения для курса на основе позиции.
         *
         * Это хардкод, так как в файле нет информации какую картинку надо ставить для курса.
         *
         * Все что смог придумать)
         */
        private fun getCourseImageResource(position: Int): Int {
            return when (position % 3) {
                0 -> R.drawable.ic_course_1
                1 -> R.drawable.ic_course_2
                2 -> R.drawable.ic_course_3
                else -> R.drawable.ic_course_1
            }
        }

        private fun formatDate(date: String): String {
            val locale = Locale.getDefault()
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", locale).parse(date)
            val outputFormat = SimpleDateFormat("d MMMM yyyy", locale)

            return inputFormat?.let { outputFormat.format(it) } ?: date
        }
    }
}