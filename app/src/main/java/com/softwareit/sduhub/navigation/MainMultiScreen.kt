package com.softwareit.sduhub.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.github.terrakok.modo.multiscreen.MultiScreen
import com.github.terrakok.modo.multiscreen.MultiScreenNavModel
import com.github.terrakok.modo.multiscreen.selectContainer
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.screens.home_screen.HomeScreenClass
import com.softwareit.sduhub.ui.screens.home_screen.HomeStackScreen
import com.softwareit.sduhub.ui.screens.map_screen.MapScreenClass
import com.softwareit.sduhub.ui.screens.map_screen.MapStackScreen
import com.softwareit.sduhub.ui.screens.profile_screen.ProfileScreenClass
import com.softwareit.sduhub.ui.screens.profile_screen.ProfileStackScreen
import com.softwareit.sduhub.ui.screens.resources_screen.ResourcesScreenClass
import com.softwareit.sduhub.ui.screens.resources_screen.ResourcesStackScreen
import kotlinx.parcelize.Parcelize
import okhttp3.internal.immutableListOf

/**
 * Main MultiScreen with bottom bar navigation.
 * It's responsible for navigation between Home, Resources, Map and Profile screens.
 * */
@Parcelize
class MainMultiScreen(
    private val navModel: MultiScreenNavModel = MultiScreenNavModel(
        containers = listOf(
            HomeStackScreen(HomeScreenClass()),
            ResourcesStackScreen(ResourcesScreenClass()),
            MapStackScreen(MapScreenClass()),
            ProfileStackScreen(ProfileScreenClass()),
        ),
        selected = 0
    )
) : MultiScreen(navModel) {

    /**
     * No UI here. Just providing containers for bottom bar.
     * The UI of each container is defined in the `Content` method of the corresponding screen
     * from arguments of current screen.
     * */
    @Composable
    override fun Content() {
        Scaffold(
            bottomBar = {
                val currentContainer = navigationState.containers[navigationState.selected]
                val rootScreenOfCurrentContainer = currentContainer.navigationState.getChildScreens().first()
                val currentScreenOfCurrentContainer = currentContainer.navigationState.getChildScreens().last()
                if (rootScreenOfCurrentContainer == currentScreenOfCurrentContainer) {
                    MainBottomBar()
                }
            },
            content = {
                for ((pos, container) in navigationState.containers.withIndex()) {
                    if (pos == navigationState.selected) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                        ) {
                            Content(container)
                        }
                    }
                }
            }
        )
    }

    @Composable
    private fun MainBottomBar() {
        val tabs = immutableListOf(
            TabData(R.drawable.ic_home, stringResource(R.string.home)),
            TabData(R.drawable.ic_resources, stringResource(R.string.resources)),
            TabData(R.drawable.ic_map, stringResource(R.string.map)),
            TabData(R.drawable.ic_profile, stringResource(R.string.profile)),
        )

        NavigationBar {
            repeat(navigationState.containers.size) { tabPos ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = rememberAsyncImagePainter(tabs[tabPos].icon),
                            contentDescription = tabs[tabPos].label
                        )
                    },
                    label = { Text(tabs[tabPos].label) },
                    alwaysShowLabel = false,
                    selected = navigationState.selected == tabPos,
                    onClick = { selectContainer(tabPos) },
                )
            }
        }
    }

}

data class TabData(
    val icon: Int,
    val label: String
)