package com.softwareit.sduhub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.softwareit.sduhub.R
import com.softwareit.sduhub.databinding.ActivityMainBinding
import com.softwareit.sduhub.ui.navigation.NavigationScreens
import com.softwareit.sduhub.ui.screens.MapFragment
import com.softwareit.sduhub.ui.screens.NewsFragment
import com.softwareit.sduhub.ui.screens.home_screen.HomeFragment
import com.softwareit.sduhub.ui.screens.profile_screen.ProfileFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val router: Router by inject()
    private val navHolder: NavigatorHolder by inject()
    private val navigator = AppNavigator(this, R.id.main_container)


    override fun onResumeFragments() {
        super.onResumeFragments()
        navHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navHolder.removeNavigator()
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        router.navigateTo(NavigationScreens.Home.home())
        setupBottomNavViewClicks()
        setupBottomNavViewVisibility()
    }

    private fun setupBottomNavViewVisibility() {
        supportFragmentManager.addOnBackStackChangedListener {
            when (supportFragmentManager.findFragmentById(R.id.main_container)) {
                is HomeFragment, is NewsFragment, is MapFragment, is ProfileFragment -> {
                    binding.bottomNavigationView.isVisible = true
                }

                else -> {
                    binding.bottomNavigationView.isGone = true
                }
            }
        }
    }

    private fun setupBottomNavViewClicks() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_screen -> {
                    router.navigateTo(NavigationScreens.Home.home())
                    true
                }

                R.id.resources_screen -> {
                    router.navigateTo(NavigationScreens.news())
                    true
                }

                R.id.map_screen -> {
                    router.navigateTo(NavigationScreens.map())
                    true
                }

                R.id.profile_screen -> {
                    router.navigateTo(NavigationScreens.profile())
                    true
                }

                else -> { false}
            }
        }
    }
}