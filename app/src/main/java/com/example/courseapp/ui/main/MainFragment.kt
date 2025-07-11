package com.example.courseapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.courseapp.databinding.FragmentMainBinding
import com.example.courseapp.ui.main.adapter.CoursesAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var adapter: CoursesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = CoursesAdapter(
            onFavoriteClick = { course ->
                viewModel.toggleFavorite(course)
            },
            onMoreClick = { course ->

            }
        )

        binding.rvCourse.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@MainFragment.adapter
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.coursesState.collect { courses ->
                    adapter.submitList(courses)
                }
            }
        }
    }
}