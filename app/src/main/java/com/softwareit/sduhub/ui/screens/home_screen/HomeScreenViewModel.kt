package com.softwareit.sduhub.ui.screens.home_screen

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.softwareit.sduhub.ui.navigation.NavigationScreens

class HomeScreenViewModel(
    private val router: Router,
) : ViewModel() {

    fun onBackPressed() = router.exit()

    fun goToCategory() = router.navigateTo(NavigationScreens.category())
}