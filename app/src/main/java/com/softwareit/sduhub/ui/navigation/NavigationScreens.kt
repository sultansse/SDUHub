package com.softwareit.sduhub.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.softwareit.sduhub.ui.screens.CategoryFragment
import com.softwareit.sduhub.ui.screens.MapFragment
import com.softwareit.sduhub.ui.screens.NewsFragment
import com.softwareit.sduhub.ui.screens.ProfileFragment
import com.softwareit.sduhub.ui.screens.home_screen.HomeFragment

object NavigationScreens {
    fun home() = FragmentScreen { HomeFragment() }
    fun category() = FragmentScreen { CategoryFragment() }
    fun profile() = FragmentScreen { ProfileFragment() }
    fun news() = FragmentScreen { NewsFragment() }
    fun map() = FragmentScreen { MapFragment() }
}