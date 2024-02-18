package com.softwareit.sduhub.screens_navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.softwareit.sduhub.presentation.screens.CategoryFragment
import com.softwareit.sduhub.presentation.screens.MapFragment
import com.softwareit.sduhub.presentation.screens.NewsFragment
import com.softwareit.sduhub.presentation.screens.ProfileFragment
import com.softwareit.sduhub.presentation.screens.home_screen.HomeFragment

object NavigationScreens {
    fun home() = FragmentScreen { HomeFragment() }
    fun category() = FragmentScreen { CategoryFragment() }
    fun profile() = FragmentScreen { ProfileFragment() }
    fun news() = FragmentScreen { NewsFragment() }
    fun map() = FragmentScreen { MapFragment() }
}