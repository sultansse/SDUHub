package com.softwareit.sduhub

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.softwareit.sduhub.screens.destinations.HomeScreenDestination
import com.softwareit.sduhub.screens.destinations.MapScreenDestination
import com.softwareit.sduhub.screens.destinations.NewsScreenDestination
import com.softwareit.sduhub.screens.destinations.SettingsScreenDestination

sealed class Navigation(
    val icon: ImageVector,
    val route: String,
    val label: String,
) {
    data object Home : Navigation(
        icon = Icons.Default.Home,
        route = HomeScreenDestination.route,
        label = "Home",
    )

    data object News : Navigation(
        icon = Icons.Default.Star,
        route = NewsScreenDestination.route,
        label = "News",
    )

    data object Map : Navigation(
        icon = Icons.Default.Place,
        route = MapScreenDestination.route,
        label = "Profile",
    )

    data object Settings : Navigation(
        icon = Icons.Default.Settings,
        route = SettingsScreenDestination.route,
        label = "Settings",
    )
}