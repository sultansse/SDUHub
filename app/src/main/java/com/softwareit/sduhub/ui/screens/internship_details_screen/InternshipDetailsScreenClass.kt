package com.softwareit.sduhub.ui.screens.internship_details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.NavigationContainer
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.StackState
import com.github.terrakok.modo.stack.back
import com.softwareit.sduhub.R
import com.softwareit.sduhub.common.data.network.getLocalMessage
import com.softwareit.sduhub.common.presentation.GenericLottieAnimationComponent
import com.softwareit.sduhub.common.presentation.LoadingLottieComponent
import com.softwareit.sduhub.data.local.datastore.DataStoreUtil
import com.softwareit.sduhub.ui.theme.colorSduBlue
import com.softwareit.sduhub.ui.theme.colorSduDarkGray
import com.softwareit.sduhub.ui.theme.colorSduLightBlue
import com.softwareit.sduhub.ui.theme.colorSduLightGray
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Parcelize
class InternshipDetailsScreenClass(
    private val internshipId: Int,
    override val screenKey: ScreenKey = generateScreenKey(),
) : Screen {

    @Composable
    override fun Content() {

        val parent = LocalContainerScreen.current

        Scaffold(
            topBar = { InternshipTopBar(parent as StackScreen) }
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                CurrentInternshipScreen()
            }
        }
    }

    @Composable
    private fun CurrentInternshipScreen() {

        val viewModel: InternshipDetailsViewModel = koinViewModel()

        LaunchedEffect(key1 = true) {
            viewModel.setEvent(InternshipDetailsContract.Event.OnFetchInternship(internshipId))
        }

        val uiState by viewModel.uiState.collectAsState()

        val dataStoreUtil: DataStoreUtil = koinInject()
        val isDarkThemeEnabled by dataStoreUtil.getTheme()
            .collectAsState(initial = isSystemInDarkTheme())

        val boxBackgroundColor by remember(isDarkThemeEnabled) {
            if (isDarkThemeEnabled) mutableStateOf(colorSduDarkGray)
            else mutableStateOf(colorSduLightGray)
        }


        when (val state = uiState.internshipState) {
            is InternshipDetailsContract.InternShipState.Success -> {
                val internship = state.internship
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = internship.title,
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.amiko_bold)),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = internship.salary
                                ?: "Salary: ${stringResource(R.string.not_provided)}",
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Text(
                        text = "Format: ${internship.timeFormat ?: stringResource(R.string.not_provided)}, ${
                            internship.placeFormat ?: stringResource(R.string.not_provided)}",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.amiko_regular)),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = "Duration: ${internship.duration ?: stringResource(R.string.not_provided)}",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.amiko_regular)),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = boxBackgroundColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Column {
                            Text(
                                text = "Company: ${internship.company}",
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.amiko_bold)),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = "Location: ${internship.location ?: stringResource(R.string.not_provided)}",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                                modifier = Modifier.fillMaxWidth()
                            )
                            Text(
                                text = "Apply Deadline: ${internship.applyDeadline ?: stringResource(R.string.not_provided)}",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Text(
                        text = internship.description,
                        fontFamily = FontFamily(Font(R.font.amiko_regular)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )

                    val contactsTextColor by remember(isDarkThemeEnabled) {
                        if (isDarkThemeEnabled) mutableStateOf(colorSduLightBlue)
                        else mutableStateOf(colorSduBlue)
                    }
                    val uriHandler = LocalUriHandler.current
                    Text(
                        text = internship.contacts,
                        color = contactsTextColor,
                        fontFamily = FontFamily(Font(R.font.amiko_semi_bold)),
                        modifier = Modifier
                            .clickable {
                                internship.contactsLink?.let { uriHandler.openUri(it) }
                            }

                    )

                }
            }

            is InternshipDetailsContract.InternShipState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 64.dp)
                ) {
                    Text(
                        text = "Loading...",
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.amiko_bold)),
                        modifier = Modifier.fillMaxWidth()
                    )
                    LoadingLottieComponent(R.raw.anim_cat_loading)
                }
            }

            is InternshipDetailsContract.InternShipState.Error -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 64.dp)
                ) {
                    Text(
                        text = state.exception.getLocalMessage(LocalContext.current),
                        fontFamily = FontFamily(Font(R.font.amiko_bold)),
                    )
                    GenericLottieAnimationComponent(R.raw.anim_error_occured)
                }
            }

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun InternshipTopBar(navigator: NavigationContainer<StackState>) {

        // Top bar
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.internship_vacancy))
            },
            navigationIcon = {
                IconButton(
                    onClick = { navigator.back() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = "Go back"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, RoundedCornerShape(0.dp)),
        )

    }
}