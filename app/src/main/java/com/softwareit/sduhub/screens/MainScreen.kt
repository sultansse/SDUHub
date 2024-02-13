package com.softwareit.sduhub.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.softwareit.sduhub.Navigation

@OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    val engine = rememberAnimatedNavHostEngine(
        // each screen animation
        rootDefaultAnimations = RootNavGraphDefaultAnimations(
            enterTransition = { fadeIn() + slideInVertically() + expandVertically()},
            exitTransition = { fadeOut() }
        )
    )
    val navController = engine.rememberNavController()

    Scaffold(
        topBar = {
//            TopAppBar()
        },
        bottomBar = {
            BottomAppBar(navController = navController)
        }
    ) { paddings ->
        DestinationsNavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings),
            navGraph = NavGraphs.root,
            navController = navController,
            engine = engine
        )
    }

}

@Composable
fun BottomAppBar(navController: NavController) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    val bottomBarItems = listOf(
        Navigation.Home,
        Navigation.News,
        Navigation.Map,
        Navigation.Settings,
    )

    NavigationBar {
        bottomBarItems.forEachIndexed { index, item ->
            val isSelected = backStackEntry?.destination?.route == item.route

            NavigationBarItem(
                icon = {
                    val animateChoice by animateColorAsState(targetValue =  if (isSelected) Color.White else Color.Gray,
                        label = item.label
                    )
                    Icon(
                        imageVector = item.icon,
                        tint = animateChoice,
                        contentDescription = item.label
                    )
                },

                label = {
                    AnimatedVisibility(
                        visible = isSelected,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically(),
                    ) {
                        Text(
                            text = item.label,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                },
                selected = backStackEntry?.destination?.route == item.route,
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}