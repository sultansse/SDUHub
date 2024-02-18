package com.softwareit.sduhub.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.softwareit.sduhub.R
import com.softwareit.sduhub.databinding.ActivityMainBinding
import com.softwareit.sduhub.screens_navigation.NavigationScreens
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

        router.navigateTo(NavigationScreens.home())
        setupBottomNavViewClicks()
    }

    private fun setupBottomNavViewClicks() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_screen -> {
                    router.navigateTo(NavigationScreens.home())
                    true
                }

                R.id.news_screen -> {
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

                else -> false
            }
        }
    }
}