package com.softwareit.sduhub.ui.screens.profile_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.NavigationContainer
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.StackState
import com.github.terrakok.modo.stack.forward
import com.softwareit.sduhub.R
import com.softwareit.sduhub.ui.SlideTransition
import com.softwareit.sduhub.ui.screens.home_screen.components.openTelegramToUser
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileHeaderComponent
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileIdCardDialog
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileScreenListItemComponent
import com.softwareit.sduhub.ui.screens.profile_screen.components.ThemeSwitchComponent
import com.softwareit.sduhub.ui.screens.profile_screen.faq_screen.FaqScreenClass
import kotlinx.parcelize.Parcelize
import okhttp3.internal.immutableListOf
import org.koin.androidx.compose.koinViewModel

@Parcelize
class ProfileStackScreen(
    private val stackNavModel: StackNavModel
) : StackScreen(stackNavModel) {

    constructor(rootScreen: Screen) : this(StackNavModel(rootScreen))

    @Composable
    override fun Content() {
        Box(Modifier.windowInsetsPadding(WindowInsets.systemBars)) {
            TopScreenContent {
                SlideTransition()
            }
        }
    }
}


@Parcelize
class ProfileScreenClass(
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = stringResource(R.string.app_name)) })
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                val parent = LocalContainerScreen.current
                ProfileScreen(parent as StackScreen)
            }
        }
    }

    @Composable
    fun ProfileScreen(navigator: NavigationContainer<StackState>) {
        val viewModel: ProfileViewModel = koinViewModel()
        val context = LocalContext.current


        val items = immutableListOf<ProfileScreenListItem>(
            ProfileScreenListItem(
                icon = R.drawable.ic_faq,
                title = "FAQ",
            ),
            ProfileScreenListItem(
                icon = R.drawable.ic_community,
                title = stringResource(R.string.community),
            ),
            ProfileScreenListItem(
                icon = R.drawable.ic_lost_found,
                title = stringResource(R.string.lost_and_found),
            ),
            ProfileScreenListItem(
                icon = R.drawable.ic_logout,
                title = stringResource(R.string.logout),
            ),
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                var dialogOpen by remember { mutableStateOf(false) }
                val student by viewModel.student.collectAsState()

                ProfileHeaderComponent(
                    student,
                    onClick = {
                        dialogOpen = true
                    }
                )

                if (dialogOpen) {
                    ProfileIdCardDialog(
                        student = student,
                        onClose = { dialogOpen = false }
                    )
                }
            }

            item {
                ThemeSwitchComponent()
            }

            items(items.size) { index ->
                ProfileScreenListItemComponent(
                    title = items[index].title,
                    icon = items[index].icon,
                    onClick = {
                        when (index) {
                            0 -> { navigator.forward(FaqScreenClass()) }
                            1 -> { openTelegramToUser(context, "sduhub") }
                            2 -> { openTelegramToUser(context, "SDU_Lost_AND_Found") }
                            3 -> { Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show() }
                        }
                    }
                )
            }
        }
    }
}

data class ProfileScreenListItem(
    val icon: Int,
    val title: String,
)