package com.softwareit.sduhub.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.softwareit.sduhub.R
import com.softwareit.sduhub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupBottomNavigationView()
        setupBottomNavViewVisibility()
        setupBottomNavViewClicks()
    }

    private fun setupBottomNavViewClicks() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_screen -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.favorites_screen -> {
                    Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show()
//                    navController.navigate(R.id.favoritesFragment)
                    true
                }

                R.id.new_post_screen -> {
                    Toast.makeText(this, "New Post", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.messages_screen -> {
                    Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.profile_screen -> {
                    Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }

    private fun setupBottomNavViewVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.bottomNavigationView.isVisible = true
                }

                else -> {
                    binding.bottomNavigationView.isVisible = false
                }
            }
        }
    }

    private fun setupBottomNavigationView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }

}