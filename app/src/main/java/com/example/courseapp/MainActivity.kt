package com.example.courseapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.courseapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null
    private var isNavigationSetup = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigationView.visibility = View.GONE

        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                    isNavigationSetup = false
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    if (!isNavigationSetup) {
                        setupBottomNavigation()
                        isNavigationSetup = true
                    }
                }
            }
        }
    }

    private fun setupBottomNavigation() {
        navController?.let { controller ->
            binding.bottomNavigationView.menu.clear()
            binding.bottomNavigationView.setOnItemSelectedListener(null)

            binding.bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu)
            binding.bottomNavigationView.setupWithNavController(controller)
        }
    }
}