package com.softwareit.sduhub.ui.screens.profile_screen

import android.app.Activity
import android.view.WindowManager
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
import com.softwareit.sduhub.application.SlideTransition
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileHeaderComponent
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileHeaderErrorComponent
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileHeaderIdleComponent
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileHeaderLoadingComponent
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileIdCardDialog
import com.softwareit.sduhub.ui.screens.profile_screen.components.ProfileScreenListItemComponent
import com.softwareit.sduhub.ui.screens.profile_screen.components.ThemeSwitchComponent
import com.softwareit.sduhub.ui.screens.profile_screen.faq_screen.FaqDetailsScreenClass
import com.softwareit.sduhub.utils.common.openTelegramToUser
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
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.profile),
                            fontFamily = FontFamily(Font(R.font.amiko_bold)),
                        )
                    }
                )
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
        val viewModel: ProfileScreenViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val uiEffect by viewModel.effect.collectAsState(initial = ProfileScreenContract.Effect.Nothing)
        val context = LocalContext.current

        val profileItems = immutableListOf(
            ProfileScreenListItem(
                icon = R.drawable.ic_faq,
                title = stringResource(R.string.faq),
            ),
            ProfileScreenListItem(
                icon = R.drawable.ic_community,
                title = stringResource(R.string.community),
            ),
            ProfileScreenListItem(
                icon = R.drawable.ic_logout,
                title = stringResource(R.string.logout),
            ),
        )

        when (val effect = uiEffect) {
            is ProfileScreenContract.Effect.Nothing -> {
                // do nothing
            }

            is ProfileScreenContract.Effect.UnavailableFeature -> {
                Toast.makeText(
                    context,
                    stringResource(R.string.currently_this_feature_can_t_be_used),
                    Toast.LENGTH_LONG
                ).show()
            }

            is ProfileScreenContract.Effect.ShowStudentCardDialog -> {
                // todo fix unavailable to make screenshots
                val window = (context as Activity).window
                DisposableEffect(key1 = true) {
                    window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
                    onDispose {
                        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
                    }
                }
                AnimatedVisibility(visible = true) {
                    ProfileIdCardDialog(
                        student = effect.student,
                        onClose = {
                            viewModel.setEvent(
                                ProfileScreenContract.Event.OnStudentCardDialogClose
                            )
                        }
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                when (val state = uiState.profileState) {
                    is ProfileScreenContract.ProfileState.Idle -> {
                        ProfileHeaderIdleComponent(
                            onClick = {
                                viewModel.setEvent(
                                    ProfileScreenContract.Event.OnAuthUser
                                )
                            }
                        )
                    }

                    is ProfileScreenContract.ProfileState.Loading -> {
                        ProfileHeaderLoadingComponent()
                    }

                    is ProfileScreenContract.ProfileState.Error -> {
                        ProfileHeaderErrorComponent(
                            onClick = {
                                viewModel.setEvent(
                                    ProfileScreenContract.Event.OnAuthUser
                                )
                            }
                        )
                    }

                    is ProfileScreenContract.ProfileState.Success -> {
                        ProfileHeaderComponent(
                            apiStudent = state.student,
                            onClick = {
                                viewModel.setEvent(
                                    ProfileScreenContract.Event.OnStudentCardClick(state.student)
                                )
                            }
                        )
                    }

                }
            }

            item {
                ThemeSwitchComponent()
            }

            items(profileItems.size, key = { profileItems[it].title }) { index ->
                ProfileScreenListItemComponent(
                    title = profileItems[index].title,
                    icon = profileItems[index].icon,
                    onClick = {
                        when (index) {
                            0 -> {
                                navigator.forward(FaqDetailsScreenClass())
                            }

                            1 -> {
                                openTelegramToUser(context, "sduhub")
                            }

                            2 -> {
                                viewModel.setEvent(ProfileScreenContract.Event.OnLogoutClick)
                            }
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