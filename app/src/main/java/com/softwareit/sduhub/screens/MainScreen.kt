package com.softwareit.sduhub.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
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
        rootDefaultAnimations = RootNavGraphDefaultAnimations(
            enterTransition = { fadeIn() + slideInVertically() + expandVertically()},
            exitTransition = { fadeOut() }
        )
    )
    val navController = engine.rememberNavController()

    Scaffold(
        topBar = {
            // TopAppBar(title = { Text(text = "SDUHub") })
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .shadow(8.dp)
            .background(Color(0xFF212121)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        bottomBarItems.forEach {
            val isSelected = backStackEntry?.destination?.route == it.route
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .shadow(4.dp, RoundedCornerShape(8.dp))
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF313131))
                        .clickable {
                            navController.navigate(it.route)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.label,
                        tint = Color.White
                    )
                }
                val spec = spring<IntSize>(
                    dampingRatio = 0.3f,
                    stiffness = Spring.StiffnessLow,
                )
                AnimatedVisibility(
                    visible = isSelected,
                    enter = fadeIn() + expandVertically(spec),
                    exit = fadeOut() + shrinkVertically(spec),
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(text = it.label, color = Color.White)
                    }
                }

            }
        }
    }
}